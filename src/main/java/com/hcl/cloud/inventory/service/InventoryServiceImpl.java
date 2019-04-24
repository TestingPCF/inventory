package com.hcl.cloud.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.cloud.inventory.dto.InventoryItem;
import com.hcl.cloud.inventory.dto.InventoryItemRequest;
import com.hcl.cloud.inventory.exception.ApiRuntimeException;
import com.hcl.cloud.inventory.repository.InventoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepository repository;

	public List<InventoryItem> findAllInventory() {
		return repository.findAllByActiveStatus(true);
	}

	public Optional<InventoryItem> getInventoryItem(final String productCode) {

		final Optional<InventoryItem> existingItem = repository.findBySkuCodeAndActiveStatus(productCode, true);
		if (!existingItem.isPresent()) {
			log.error("Error {} Product does not exist.", existingItem);
			throw new ApiRuntimeException(404, 404, "Product does not Exists");
		}

		return existingItem;
	}

	public InventoryItem saveOrUpdateInventory(final InventoryItem item) {
		final Optional<InventoryItem> existingItem = repository.findBySkuCode(item.getSkuCode());
		if (existingItem.isPresent()) {
			log.info("Item already present updating quantity");
			InventoryItem currentItem=existingItem.get();
			currentItem.setQuantity(item.getQuantity());
			if(currentItem.getQuantity()>0l)currentItem.setActiveStatus(true);
			return repository.save(currentItem);
		}
		else {
			log.info("Adding item to inventory");
		item.setActiveStatus(true);
		return repository.save(item);
		}
	}

	public InventoryItem updateInventory(final InventoryItemRequest item) {
		if (item.getQuantity() < 0) {
			log.error("Error {} Negative quantity {}", item.getSkuCode(), item.getQuantity());
			throw new ApiRuntimeException(400, 400, "Negative quantity");
		}
		Optional<InventoryItem> existingItem=getInventoryItem(item.getSkuCode());
		if (!existingItem.isPresent()) {
			log.error("Error {} doesn't exist.", existingItem);
			throw new ApiRuntimeException(404, 404, "Product does not Exists");
		}
		InventoryItem currentItem= existingItem.get();
		
		long quantity=currentItem.getQuantity();
		if(quantity<item.getQuantity()) {
			log.error("Error {} Insufficient Inventory.", existingItem);
			throw new ApiRuntimeException(404, 404, " Insufficient Inventory.");
		}
		currentItem.setQuantity(quantity-item.getQuantity());
		if(currentItem.getQuantity()==0l) {
			currentItem.setActiveStatus(false);
		}
		return repository.save(currentItem);
	}

	

}
