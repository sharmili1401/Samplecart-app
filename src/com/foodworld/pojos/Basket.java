package com.foodworld.pojos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Basket {
	private Integer basketId;
	private Set<BasketItem> basketItems = new HashSet<BasketItem>();
	private User user;
	
	@Id
	public Integer getBasketId() {
		return basketId;
	}
	public void setBasketId(Integer basketId) {
		this.basketId = basketId;
	}
	
	@OneToMany(mappedBy="basket")
	public Set<BasketItem> getBasketItems() {
		return basketItems;
	}
	public void setBasketItems(Set<BasketItem> basketItems) {
		this.basketItems = basketItems;
	}
	@OneToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
