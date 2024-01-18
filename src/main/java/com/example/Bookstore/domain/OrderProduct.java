package com.example.Bookstore.domain;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderProduct {
	
	@EmbeddedId
	@JsonIgnore
	private OrderProductPK pk;
	
	@Column(nullable = false)
	private Integer quantity;
	
	public OrderProduct() {
		
	}
	public OrderProduct(Order order, Beer product, Integer quantity) {
		pk = new OrderProductPK();
		pk.setOrder(order);
		pk.setProduct(product);
		this.quantity = quantity;
	}
	
	@Transient
	public Beer getProduct() {
		return this.pk.getProduct();
	}
	
	@Transient
	public Double getTotalPrice() {
		return getProduct().getHinta() * getQuantity();
	}
	public OrderProductPK getPk() {
		return pk;
	}
	public void setPk(OrderProductPK pk) {
		this.pk = pk;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
