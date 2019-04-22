package com.hcl.cloud.inventory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.cloud.inventory.dto.InventoryItem;

public interface InventoryRepository extends JpaRepository<InventoryItem, String> {

    Optional<InventoryItem> findBySkuCodeAndActiveStatus(String skuCode, boolean activeStatus);
    
    List<InventoryItem> findAllByActiveStatus(Boolean activeStatus);
}
