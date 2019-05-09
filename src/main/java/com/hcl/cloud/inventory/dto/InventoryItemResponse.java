package com.hcl.cloud.inventory.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItemResponse {

	private String skuCode;
	private long quantity;
	private boolean activeStatus;
	private boolean inStock;

	public static InventoryItemResponse from(InventoryItem item) {
		return InventoryItemResponse.builder().skuCode(item.getSkuCode()).quantity(item.getQuantity())
				.activeStatus(item.isActiveStatus()).inStock(item.getQuantity() > 0 ? true : false).build();
	}

	public static List<InventoryItemResponse> from(List<InventoryItem> request) {
		final List<InventoryItemResponse> inventoryItemResponseList = new ArrayList<>();
		request.parallelStream().forEach(item -> inventoryItemResponseList.add(from(item)));
		return inventoryItemResponseList;
	}

}
