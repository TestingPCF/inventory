package com.hcl.cloud.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hcl.cloud.inventory.dto.InventoryItem;

@Service
@Component
public interface InventoryService {
	
	public List<InventoryItem> findAllInventory();

    public Optional<InventoryItem> getInventoryItem(final String productCode);

    public InventoryItem saveInventory(final InventoryItem item);
    
    public InventoryItem updateInventory(final InventoryItem item);

}