package com.tricon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricon.bean.Customer;
import com.tricon.repository.CustomerRepository;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class CustomerService {

	@Autowired
	private CustomerRepository customerReposiroty;
	
	@GraphQLQuery(name = "customers")
	public List<Customer> getCustomers(){
		return customerReposiroty.findAll();
	}
	
	@GraphQLQuery(name = "customer")
	public Customer getCustomerById(@GraphQLArgument(name="id") String id) {
		return customerReposiroty.findById(id).orElseThrow(()->new RuntimeException("Customer Not Found!"));
	}
}
