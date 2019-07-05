package com.tricon.bean;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;

@Document
@GraphQLProperty(name = "customer", arguments = {
		@GraphQLArgument(name = "id", type="String")
})
public class Order {

	@Id
	private String id;
	private String custId;
	private List<String> productIds;
	private Customer customer;
	private List<Product> products;

	public Order(String id, String custId, List<String> productIds) {
		super();
		this.id = id;
		this.custId = custId;
		this.productIds = productIds;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public List<String> getProductIds() {
		return productIds;
	}
	public void setProductId(List<String> productIds) {
		this.productIds = productIds;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProduct(List<Product> products) {
		this.products = products;
	}
}
