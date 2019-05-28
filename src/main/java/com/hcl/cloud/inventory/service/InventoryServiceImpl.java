
package com.hcl.cloud.inventory.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.hcl.cloud.inventory.controller.InventoryController;
import com.hcl.cloud.inventory.dto.InventoryItem;
import com.hcl.cloud.inventory.dto.InventoryItemRequest;
import com.hcl.cloud.inventory.exception.ApiRuntimeException;
import com.hcl.cloud.inventory.repository.InventoryRepository;

//import lombok.extern.slf4j.Slf4j;

@Service
//@Slf4j
@RefreshScope
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepository repository;
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);

	@Value("${inventory.product.unavailable.msg}")
	private String PRODUCT_UNAVAILABLE;
	
	@Value("${inventory.negative.quantity.msg}")
	private String negativeQuantity;
	
	@Value("${inventory.insufficient.msg}")
	private String insufficientInventory;
	
	

	public Optional<InventoryItem> getInventoryItem(final String productCode) {

		Optional<InventoryItem> existingItem = null;

		try {
			existingItem = repository.findBySkuCodeAndActiveStatus(productCode, true);
		} catch (Exception ext) {
			logger.error("Seems Inventory database service unavaible.");
			throw new ApiRuntimeException(500, 500, "Inventory Data base service down.");
		}
		if (!existingItem.isPresent()) {
			logger.error("Error {} Product does not exist.", existingItem);
			throw new ApiRuntimeException(404, 404, PRODUCT_UNAVAILABLE);
		}
		logger.info("Getting inventory item by skuCode");
		return existingItem;

	}

	public InventoryItem saveOrUpdateInventory(final InventoryItem item) {

		Optional<InventoryItem> existingItem = null;
		try {
			existingItem = repository.findBySkuCodeAndActiveStatus(item.getSkuCode(), true);
		} catch (Exception ext) {
			logger.error("Seems Inventory database service unavaible.");
			throw new ApiRuntimeException(500, 500, "Inventory Data base service down.");
		}
		if (existingItem.isPresent()) {
			logger.info("Item already present, updating its quantity");
			InventoryItem currentItem = existingItem.get();
			currentItem.setQuantity(item.getQuantity());
			if (currentItem.getQuantity() > 0l)
				currentItem.setActiveStatus(true);
			try {
				return repository.save(currentItem);
			} catch (Exception ext) {
				logger.error("Seems Inventory database service unavaible.");
				throw new ApiRuntimeException(500, 500, "Inventory Data base service down.");
			}
		} else {
			logger.info("Adding/Creating item in inventory");
			item.setActiveStatus(true);
			try {
				return repository.save(item);
			} catch (Exception ext) {
				logger.error("Seems Inventory database service unavaible.");
				throw new ApiRuntimeException(500, 500, "Inventory Data base service down.");
			}
		}
	}

	public InventoryItem updateInventory(final InventoryItemRequest item) {
		if (item.getQuantity() < 0) {
			logger.error("Error {} Negative quantity {}", item.getSkuCode(), item.getQuantity());
			throw new ApiRuntimeException(400, 400, negativeQuantity);
		}
		Optional<InventoryItem> existingItem = getInventoryItem(item.getSkuCode());

		if (existingItem.isPresent()) {
			InventoryItem currentItem = existingItem.get();

			long quantity = currentItem.getQuantity();
			if (quantity < item.getQuantity()) {
				logger.error("Error {} Insufficient Inventory.", existingItem);
				throw new ApiRuntimeException(400, 400, insufficientInventory);
			}
			currentItem.setQuantity(quantity - item.getQuantity());
			logger.info("Item is updated in Inventory");
			try {
				return repository.save(currentItem);
			} catch (Exception ext) {
				logger.error("Seems Inventory database service unavaible.");
				throw new ApiRuntimeException(500, 500, "Inventory Data base service down.");
			}
		} else {
			return null;
		}

	}

	public List<InventoryItem> findAllInventory() {
		try {
			return repository.findAllByActiveStatus(true);
		} catch (Exception ext) {
			logger.error("Seems Inventory database service unavaible.");
			throw new ApiRuntimeException(500, 500, "Inventory Data base service down.");
		}
	}

}
