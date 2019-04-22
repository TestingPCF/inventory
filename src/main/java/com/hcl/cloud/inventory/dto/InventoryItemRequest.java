package com.hcl.cloud.inventory.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class InventoryItemRequest {
    private String productCode;
    private long quantity;

    public static InventoryItem from(final InventoryItemRequest request) {
        return InventoryItem.builder()
                .skuCode(request.getProductCode())
                .quantity(request.getQuantity())
                .build();
    }
}
