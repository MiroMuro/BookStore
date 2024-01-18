package com.example.Bookstore.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Embeddable
public class OrderProductPK implements Serializable {
	
	@JsonBackReference
	@ManyToOne(optional=false, fetch = FetchType.LAZY)
	@JoinColumn(name="order_id")
	private Order order;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="beerid")
	private Beer product;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Beer getProduct() {
		return product;
	}

	public void setProduct(Beer product) {
		this.product = product;
	}
}
