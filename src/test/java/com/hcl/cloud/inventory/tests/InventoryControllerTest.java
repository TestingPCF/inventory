package com.hcl.cloud.inventory.tests;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.cloud.inventory.dto.InventoryItem;
import com.hcl.cloud.inventory.dto.InventoryItemRequest;
import com.hcl.cloud.inventory.exception.ApiRuntimeException;
import com.hcl.cloud.inventory.service.InventoryService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InventoryControllerTest {

	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private InventoryService service;

	@Test
	@DisplayName("Get  /api/inventory/I001 - Found")
	public void testGetInventoryByProductCodeFound() throws Exception {
		InventoryItem mockItem = new InventoryItem("arqw42343", "I001", 12, true);
		doReturn(Optional.of(mockItem)).when(service).getInventoryItem("I001");

		mockMvc.perform(get("/inventory/{productCode}", "I001"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.skuCode", is("I001")))
				.andExpect(jsonPath("$.quantity", is(12)))
				.andExpect(jsonPath("$.activeStatus", is(true)))
				.andExpect(jsonPath("$.inStock", is(true)));

	}

	@Test
	@DisplayName("Get /api/inventory/I002 - Not Found")
	public void testGetInventoryByProductCodeNotFound() throws Exception {

		ApiRuntimeException mockItem = new ApiRuntimeException(404, 404, "Product does not Exists");

		doThrow(mockItem).when(service).getInventoryItem("I002");

		mockMvc.perform(get("/inventory/{productCode}", "I002"))
				.andExpect(status().isNotFound())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.code", is(404)))
				.andExpect(jsonPath("$.message", is("Product does not Exists")));

	}
	
	
	@Test
    @DisplayName("Post /api/inventory -- Create Inventory")
    public void testCreateInventory() throws Exception{

        InventoryItemRequest inventoryRequest = new InventoryItemRequest("I003",10);
        InventoryItem mockItem =
                new InventoryItem("product1231", "I003", 10, true);

        doReturn(mockItem)
                .when(service).saveInventory(any());

        mockMvc.perform(post("/inventory")
               .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(inventoryRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.skuCode",is("I003")))
                .andExpect(jsonPath("$.quantity",is(10)))
                .andExpect(jsonPath("$.activeStatus",is(true)))
                .andExpect(jsonPath("$.inStock",is(true)));


    }

    @Test
    @DisplayName("Put /api/inventory -- Update Inventory")
    public void testUpateInventory() throws Exception{

        InventoryItemRequest inventoryRequest = new InventoryItemRequest("I003",12);
        InventoryItem mockItem =
                new InventoryItem("product1231", "I003", 12, true);

        doReturn(Optional.of(mockItem))
                .when(service)
                .getInventoryItem("I003");

        doReturn(mockItem)
                .when(service).updateInventory(any());

        mockMvc.perform(put("/inventory")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(inventoryRequest)))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.skuCode",is("I003")))
                .andExpect(jsonPath("$.quantity",is(12)))
                .andExpect(jsonPath("$.activeStatus",is(true)))
                .andExpect(jsonPath("$.inStock",is(true)));


    }

    

    static String asJsonString(final Object obj){
        try{
            return  new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}

	
	
