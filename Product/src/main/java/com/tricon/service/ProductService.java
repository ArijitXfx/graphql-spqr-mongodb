package com.tricon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricon.bean.Product;
import com.tricon.repository.ProductRepository;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	@GraphQLQuery(name = "products")
	public List<Product> getProducts(){
		return productRepository.findAll();
	}
	
	@GraphQLQuery(name = "product")
	public Product getProductById(@GraphQLArgument(name = "id") String id) {
		return productRepository.findById(id).orElseThrow(()->new RuntimeException("Product Not Found!"));
	}
}
