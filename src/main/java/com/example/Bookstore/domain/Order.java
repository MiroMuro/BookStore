package com.example.Bookstore.domain;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonFormat(pattern="dd/mm/yyyy")
	private LocalDate dateCreated;
	
	@JsonManagedReference
	@OneToMany(mappedBy ="pk.order")
	@Valid
	private List<OrderProduct> orderProducts = new ArrayList<>();
	
	@Transient
	public Double getTotalOrderPrice() {
		double sum = 0D;
		List<OrderProduct> orderProducts = getOrderProducts();
		for(OrderProduct op : orderProducts) {
			sum += op.getTotalPrice();
		}
		return sum;
	}
	@Transient
	public int getNumberOfProducts() {
		return this.orderProducts.size();
	}
	public void setDateCreated(LocalDate date) {
		this.dateCreated = date;
	}
	public LocalDate getDateCreated() {
		return dateCreated;
	}
	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}
	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}
	
}
