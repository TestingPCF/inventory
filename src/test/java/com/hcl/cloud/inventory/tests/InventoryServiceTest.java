package com.hcl.cloud.inventory.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hcl.cloud.inventory.dto.InventoryItem;
import com.hcl.cloud.inventory.dto.InventoryItemRequest;
import com.hcl.cloud.inventory.exception.ApiRuntimeException;
import com.hcl.cloud.inventory.repository.InventoryRepository;
import com.hcl.cloud.inventory.service.InventoryService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class InventoryServiceTest {

	/*
	 * @Autowired private InventoryService service;
	 * 
	 * @MockBean private InventoryRepository repository;
	 * 
	 * 
	 * 
	 * @Test public void testGetInventoryItem() throws Exception { InventoryItem
	 * mockItem = new InventoryItem("arqw42343", "I001", 12, true);
	 * doReturn(Optional.of(mockItem)).when(repository).findBySkuCodeAndActiveStatus
	 * ("I001", true); Optional<InventoryItem> inv =
	 * service.getInventoryItem("I001"); assertTrue(inv.isPresent());
	 * assertEquals(inv.get().get_Id(), "arqw42343");
	 * assertEquals(inv.get().getSkuCode(), "I001");
	 * assertEquals(inv.get().getQuantity(), 12);
	 * assertEquals(inv.get().isActiveStatus(), true);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * @Test public void testGetInventoryItemNotFound() throws Exception {
	 * //ApiRuntimeException itemNotFound = new ApiRuntimeException(404, 404,
	 * "Product does not Exists");
	 * //doThrow(itemNotFound).when(repository).findBySkuCodeAndActiveStatus("I001",
	 * true); Assertions.assertThrows(ApiRuntimeException.class,
	 * ()->service.getInventoryItem("I001")); }
	 * 
	 * 
	 * @Test public void testUpdateInventory() throws Exception { InventoryItem
	 * mockItem = new InventoryItem("arqw42343", "I001", 12, true);
	 * doReturn(Optional.of(mockItem)).when(repository).findBySkuCode("I001");
	 * doReturn(mockItem).when(repository).save(mockItem); InventoryItem inv =
	 * service.saveOrUpdateInventory(mockItem); assertEquals(inv.get_Id(),
	 * "arqw42343"); assertEquals(inv.getSkuCode(), "I001");
	 * assertEquals(inv.getQuantity(), 12); assertEquals(inv.isActiveStatus(),
	 * true); }
	 * 
	 * @Test public void testSaveInventory() throws Exception { InventoryItem
	 * mockItem = new InventoryItem("HSIAJB", "I01123", 10, true);
	 * doReturn(mockItem).when(repository).save(mockItem); InventoryItem inv =
	 * service.saveOrUpdateInventory(mockItem); assertEquals(inv.get_Id(),
	 * "HSIAJB"); assertEquals(inv.getSkuCode(), "I01123");
	 * assertEquals(inv.getQuantity(), 10); assertEquals(inv.isActiveStatus(),
	 * true);
	 * 
	 * }
	 * 
	 * @Test public void testConsumeInventoryWithNegativeQuantity() throws
	 * Exception{ InventoryItemRequest mockItem = new InventoryItemRequest("HSIAJB",
	 * -1l); Assertions.assertThrows(ApiRuntimeException.class,
	 * ()->service.updateInventory(mockItem)); }
	 * 
	 * 
	 * @Test public void testConsumeInventoryWithProductNotAvailable() throws
	 * Exception{
	 * 
	 * //InventoryItem mockItem = new InventoryItem("arqw42343", "I002", 12, true);
	 * //doReturn(Optional.of(mockItem)).when(repository).
	 * findBySkuCodeAndActiveStatus("I001", true);
	 * //Assertions.assertThrows(ApiRuntimeException.class,
	 * ()->service.getInventoryItem("HSIAJB"));
	 * 
	 * 
	 * InventoryItem mockItem = new InventoryItem("arqw42343", "I001", 12, true);
	 * doReturn(Optional.of(mockItem)).when(repository).findBySkuCodeAndActiveStatus
	 * ("I001", true); InventoryItemRequest mockItem1 = new
	 * InventoryItemRequest("HSIAJB", -10l);
	 * Assertions.assertThrows(ApiRuntimeException.class,
	 * ()->service.updateInventory(mockItem1));
	 * 
	 * 
	 * // InventoryItemRequest mockItem1 = new InventoryItemRequest("I01123", 12);
	 * // Assertions.assertThrows(ApiRuntimeException.class,
	 * ()->service.updateInventory(mockItem1)); // // InventoryItem mockItem = new
	 * InventoryItem("HSIAJB", "I01123", 15, true); //
	 * doReturn(mockItem).when(repository).save(mockItem);
	 * 
	 * }
	 * 
	 * // @Test // public void testConsumeInventory() throws Exception{ //
	 * InventoryItemRequest mockItemRequest = new InventoryItemRequest("HSIAJB",
	 * 10l); // //InventoryItem mockItem = new InventoryItem("HSIAJB", "I01123", 10,
	 * true); // //doReturn(mockItem).when(repository).save(mockItem); //
	 * //service.getInventoryItem(mockItem.getSkuCode()); // Optional<InventoryItem>
	 * existingItem=service.getInventoryItem(mockItemRequest.getSkuCode()); //
	 * Assertions.assertThrows(ApiRuntimeException.class,
	 * ()->service.updateInventory(mockItemRequest)); // }
	 * 
	 * 
	 */
}
