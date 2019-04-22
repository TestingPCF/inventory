package com.hcl.cloud.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.cloud.inventory.dto.InventoryItem;
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
			log.error("Error {} already exist.", existingItem);
			throw new ApiRuntimeException(404, 404, "Product does not Exists");
		}

		return existingItem;
	}

	public InventoryItem saveInventory(final InventoryItem item) {
		final Optional<InventoryItem> existingItem = repository.findBySkuCodeAndActiveStatus(item.getSkuCode(), true);
		if (existingItem.isPresent()) {
			log.error("Error {} already exist.", item.getSkuCode());
			throw new ApiRuntimeException(400, 400, "Product already Exists");
		}
		item.setActiveStatus(true);
		return repository.save(item);
	}

	public InventoryItem updateInventory(final InventoryItem item) {
		if (item.getQuantity() < 0) {
			log.error("Error {} Negative quantity {}", item.getSkuCode(), item.getQuantity());
			throw new ApiRuntimeException(400, 400, "Negative quantity");
		}
		return repository.save(item);
	}

}
