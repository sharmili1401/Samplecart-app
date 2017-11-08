package com.foodworld.pojos;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class BasketItem {
	private Integer basketItemId;
	private Integer quantity;
	private Product product;
	private Basket basket;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getBasketItemId() {
		return basketItemId;
	}
	public void setBasketItemId(Integer basketItemId) {
		this.basketItemId = basketItemId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@OneToOne
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@ManyToOne
	@JoinColumn(name="basketId")
	public Basket getBasket() {
		return basket;
	}
	public void setBasket(Basket basket) {
		this.basket = basket;
	}
	
}
