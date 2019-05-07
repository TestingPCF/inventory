package com.hcl.cloud.inventory.controller;

import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.cloud.inventory.config.RabbitmqConfig;
import com.hcl.cloud.inventory.dto.CustomMessageBean;
import com.hcl.cloud.inventory.dto.InventoryItem;
import com.hcl.cloud.inventory.dto.InventoryItemRequest;
import com.hcl.cloud.inventory.dto.InventoryItemResponse;
import com.hcl.cloud.inventory.exception.ApiRuntimeException;
import com.hcl.cloud.inventory.service.InventoryService;


import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
//@RequestMapping(value = "/")
public class InventoryController {

	
	
	@Autowired
	private InventoryService invetoryService;
	

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<InventoryItemResponse> createInventory(@RequestBody InventoryItemRequest item)
			throws ApiRuntimeException {
		return new ResponseEntity<InventoryItemResponse>(
				InventoryItemResponse.from(invetoryService.saveOrUpdateInventory(InventoryItemRequest.from(item))),
				HttpStatus.CREATED);

	}

	@RequestMapping(value = "/{productCode}", method = RequestMethod.GET)
	public ResponseEntity<InventoryItemResponse> getInventoryItem(@PathVariable("productCode") String productCode) {
		log.info("Get Inventory api called..{}", productCode);
		final Optional<InventoryItem> existingItem = invetoryService.getInventoryItem(productCode);
		return new ResponseEntity<InventoryItemResponse>(InventoryItemResponse.from(existingItem.get()), HttpStatus.OK);
	}

	@RequestMapping(value = "/{productCode}/{quantity}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> isProductQuantityAvailable(@PathVariable("productCode") String productCode,
			@PathVariable("quantity") String quantity) {
		log.info("Get Inventory api called..{}", productCode);
		final Optional<InventoryItem> existingItem = invetoryService.getInventoryItem(productCode);
		InventoryItem inventry = existingItem.get();
		Long quanityLong = new Long(quantity);
		if(inventry.isActiveStatus() && quanityLong.longValue()<= inventry.getQuantity() ) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<InventoryItemResponse> updateInventory(@RequestBody InventoryItemRequest item)
			throws ApiRuntimeException {
		log.info("Update Inventory api called.");

//		final Optional<InventoryItem> existingItem = invetoryService.getInventoryItem(item.getSkuCode());
//		log.info("Item {} already exist.", item.getSkuCode());

		//existingItem.get().setQuantity(item.getQuantity());
		return new ResponseEntity<InventoryItemResponse>(
				InventoryItemResponse.from(invetoryService.updateInventory(item)), HttpStatus.ACCEPTED);

	}
	@Autowired
    Jackson2JsonMessageConverter jackson2JsonMessageConverter;
    @RabbitListener(queues = RabbitmqConfig.QUEUE_SPECIFIC_NAME)
    public void receiveMessage(CustomMessageBean customMessage) {
        //CustomMessageBean customMessageBean=(CustomMessageBean) jackson2JsonMessageConverter.fromMessage(customMessage);
        System.out.println("Received message from Listener from Controller {} "+ customMessage.toString());
        InventoryItemRequest req=new InventoryItemRequest();
        req.setQuantity(customMessage.getQuantity());
        req.setSkuCode(customMessage.getSkuCode());
        createInventory(req);
    }
}
