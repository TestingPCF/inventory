package com.hcl.cloud.inventory.tests;


import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.hcl.cloud.inventory.dto.InventoryItem;
import com.hcl.cloud.inventory.exception.ApiRuntimeException;
import com.hcl.cloud.inventory.service.InventoryService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(PowerMockRunner.class)
public class InventoryControllerTest {

	//InventoryController inventoryController = new InventoryController();
      @Autowired
      private MockMvc mockMvc;

      @MockBean
      private InventoryService service;

	/*
	 * @Test
	 * 
	 * @DisplayName("Get  /api/inventory/I001 - Found") public void
	 * testGetInventoryByProductCodeFound() throws Exception { InventoryItem
	 * mockItem = new InventoryItem("arqw42343", "I001", 12, true);
	 * doReturn(Optional.of(mockItem)) .when(service) .getInventoryItem("I001");
	 * 
	 * mockMvc.perform(get("/api/inventory/{productCode}", "I001"))
	 * .andExpect(status().isOk())
	 * .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	 * .andExpect(jsonPath("$.productCode",is("I001")))
	 * .andExpect(jsonPath("$.quantity",is(12)))
	 * .andExpect(jsonPath("$.activeStatus",is(true)))
	 * .andExpect(jsonPath("$.inStock",is(true)));
	 * 
	 * }
	 * 
	 * @Test
	 * 
	 * @DisplayName("Get /api/inventory/I002 - Not Found") public void
	 * testGetInventoryByProductCodeNotFound() throws Exception{
	 * 
	 * ApiRuntimeException mockItem = new ApiRuntimeException(404, 404,
	 * "Product does not Exists"); doThrow(mockItem) .when(service)
	 * .getInventoryItem("I002");
	 * 
	 * mockMvc.perform(get("/api/inventory/{productCode}", "I002"))
	 * .andExpect(status().isNotFound())
	 * .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	 * .andExpect(jsonPath("$.code",is(404)))
	 * .andExpect(jsonPath("$.message",is("Product does not Exists")));
	 * 
	 * 
	 * }
	 */    
	/*
	 * @Test public void createInventoryServiceTest() { InventoryItem item=new
	 * InventoryItem(); item.setSkuCode("IASDSA"); item.setActiveStatus(true);
	 * item.setQuantity(10); service.saveInventory(item); Optional<InventoryItem>
	 * item1=service.getInventoryItem("IASDSA"); assertEquals(item.getQuantity(),
	 * item1.get().getQuantity()); }
	 */
    
	
//	@Test
//	public void testcreateInventory() {
//		
//		InventoryService invetoryService = PowerMockito.mock(InventoryServiceImpl.class);
//		//when(invetoryService.getInventoryItem("I101")).thenReturn
//		InventoryItem item= new InventoryItem();
//		item.set_Id("P_101");
//		item.setSkuCode("ABC");
//		item.setActiveStatus(true);
//		item.setQuantity(7);
//		PowerMockito.when(invetoryService.saveInventory(item)).thenReturn(item);
//		InventoryItemRequest request = new InventoryItemRequest();
//		request.setProductCode("P_101");
//		inventoryController.setInvetoryService(invetoryService);
//		 ResponseEntity<InventoryItemResponse> response =  inventoryController.createInventory(request);
//		//assertEquals(expected, actual);
//		
//	}
	
    
    
    

}
