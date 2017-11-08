package com.foodworld.pojos;

import java.math.BigDecimal;

public class BasketItemVO {

	private Integer productId;
	private String productName;
	private Integer quantity;
	private BigDecimal productPrice;
	private Long totalPrice;
	
	public Long getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getProductName() {
		return productName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	
}
