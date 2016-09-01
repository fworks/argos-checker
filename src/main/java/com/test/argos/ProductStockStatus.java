package com.test.argos;

import com.google.api.client.json.JsonString;
import com.google.api.client.util.Key;

public class ProductStockStatus {

	@Key("productId")
	public String productId;
	@Key("storeId")
	public String storeId;
	@Key("stockQuantity")
	public Integer stockQuantity;
	@Key("isStocked")
	public Boolean isStocked;
	@Key("isOrderable")
	public Boolean isOrderable;
	@Key("hasOutOfStockMessage")
	public Boolean hasOutOfStockMessage;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public Boolean getIsStocked() {
		return isStocked;
	}

	public void setIsStocked(Boolean isStocked) {
		this.isStocked = isStocked;
	}

	public Boolean getIsOrderable() {
		return isOrderable;
	}

	public void setIsOrderable(Boolean isOrderable) {
		this.isOrderable = isOrderable;
	}

	public Boolean getHasOutOfStockMessage() {
		return hasOutOfStockMessage;
	}

	public void setHasOutOfStockMessage(Boolean hasOutOfStockMessage) {
		this.hasOutOfStockMessage = hasOutOfStockMessage;
	}

}