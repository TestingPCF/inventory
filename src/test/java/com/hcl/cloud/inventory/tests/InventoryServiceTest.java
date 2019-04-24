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
import com.hcl.cloud.inventory.exception.ApiRuntimeException;
import com.hcl.cloud.inventory.repository.InventoryRepository;
import com.hcl.cloud.inventory.service.InventoryService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class InventoryServiceTest {


	@Autowired
	private InventoryService service;
	
	@MockBean
	private InventoryRepository repository;
	
	

	@Test
	@DisplayName("Testing Get Inventory item method")
	public void testGetInventoryItem() throws Exception {
		InventoryItem mockItem = new InventoryItem("arqw42343", "I001", 12, true);
		doReturn(Optional.of(mockItem)).when(repository).findBySkuCodeAndActiveStatus("I001", true);
		Optional<InventoryItem> inv = service.getInventoryItem("I001");
		assertTrue(inv.isPresent());
	    assertEquals(inv.get().get_Id(), "arqw42343");
	    assertEquals(inv.get().getSkuCode(), "I001");
	    assertEquals(inv.get().getQuantity(), 12);
	    assertEquals(inv.get().isActiveStatus(), true);
	    


	}

	
	@Test
	@DisplayName("Testing Get Inventory item method")
	public void testGetInventoryItemNotFound() throws Exception {
		//ApiRuntimeException itemNotFound = new ApiRuntimeException(404, 404, "Product does not Exists");
		//doThrow(itemNotFound).when(repository).findBySkuCodeAndActiveStatus("I001", true);
		Assertions.assertThrows(ApiRuntimeException.class, ()->service.getInventoryItem("I001"));
	}

}

	
	
