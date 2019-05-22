package com.hcl.cloud.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hcl.cloud.inventory.dto.InventoryItem;
import com.hcl.cloud.inventory.dto.InventoryItemRequest;

@Service
@Component
public interface InventoryService {
	
	public List<InventoryItem> findAllInventory();

    public Optional<InventoryItem> getInventoryItem(final String productCode);

    public InventoryItem saveOrUpdateInventory(final InventoryItem item);
    
    public InventoryItem updateInventory(final InventoryItemRequest item);
    
    
    

}