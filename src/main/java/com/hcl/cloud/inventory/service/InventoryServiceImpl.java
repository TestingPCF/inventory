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

	public Optional<InventoryItem> getInventoryItem(final String productCode) {

		final Optional<InventoryItem> existingItem = repository.findBySkuCodeAndActiveStatus(productCode, true);
		if (!existingItem.isPresent()) {
			log.error("Error {} Product does not exist.", existingItem);
			throw new ApiRuntimeException(404, 404, "Product does not Exists");
		}

		return existingItem;
	}

	public InventoryItem saveOrUpdateInventory(final InventoryItem item) {
		final Optional<InventoryItem> existingItem = repository.findBySkuCodeAndActiveStatus(item.getSkuCode(), true);
		if (existingItem.isPresent()) {
			log.info("Item already present updating quantity");
			InventoryItem currentItem = existingItem.get();
			currentItem.setQuantity(item.getQuantity());
			if (currentItem.getQuantity() > 0l)
				currentItem.setActiveStatus(true);
			return repository.save(currentItem);
		} else {
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
		Optional<InventoryItem> existingItem = getInventoryItem(item.getSkuCode());

		InventoryItem currentItem = existingItem.get();

		long quantity = currentItem.getQuantity();
		if (quantity < item.getQuantity()) {
			log.error("Error {} Insufficient Inventory.", existingItem);
			throw new ApiRuntimeException(400, 400, " Insufficient Inventory.");
		}
		currentItem.setQuantity(quantity - item.getQuantity());
		return repository.save(currentItem);
	}

	/*
	 * public InventoryItem deleteInventory(final String productCode) {
	 * 
	 * log.info("Delete Inventory api called.");
	 * 
	 * final Optional<InventoryItem> existingItem = getInventoryItem(productCode);
	 * log.info("Item {} already exist.", existingItem.get());
	 * 
	 * if (!existingItem.isPresent()) { log.error("Error {} Item doesn't exist.",
	 * existingItem.get()); throw new ApiRuntimeException(2222, 400,
	 * "Product doesn't exists"); }
	 * 
	 * if (!existingItem.get().isActiveStatus()) {
	 * log.error("Error {} already inactive", existingItem.get()); throw new
	 * ApiRuntimeException(2224, 400, "Inactive product"); }
	 * 
	 * existingItem.get().setActiveStatus(false); return
	 * repository.save(existingItem.get()); }
	 */

	public List<InventoryItem> findAllInventory() {
		return repository.findAllByActiveStatus(true);
	}

	/*
	 * public List<InventoryItem> findAllInventory() { return repository.findAll();
	 * }
	 */
}
