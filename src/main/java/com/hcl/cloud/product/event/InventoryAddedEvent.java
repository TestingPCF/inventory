package com.hcl.cloud.product.event;
public class InventoryAddedEvent {

	private String skuCode = null;   
    private long quantity = 0;
    private boolean status = true;
    
	public String getSkuCode() {
		return skuCode;
	}
	public long getQuantity() {
		return quantity;
	}
	public boolean getStatus() {
		return status;
	}
	public InventoryAddedEvent() {
    }
	public InventoryAddedEvent(String skuCode, long quantity, boolean status) {
		super();
		this.skuCode = skuCode;
		this.quantity = quantity;
		this.status = status;
	}
	
}
