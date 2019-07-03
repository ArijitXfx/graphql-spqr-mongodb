package com.tricon.service;

import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricon.bean.Customer;
import com.tricon.bean.Order;
import com.tricon.bean.Product;
import com.tricon.repository.OrderRepository;

import io.aexp.nodes.graphql.Argument;
import io.aexp.nodes.graphql.Arguments;
import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLResponseEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@GraphQLQuery(name = "orders")
	public List<Order> getOrders(){
		return orderRepository.findAll();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object getRequest(String uri, Arguments arguments, Class clazz) {
		GraphQLTemplate graphQLTemplate = new GraphQLTemplate();

		GraphQLRequestEntity requestEntiry = null;
		try {
			requestEntiry = GraphQLRequestEntity.Builder()
					.url(uri)
					.arguments(arguments)
					.request(clazz)
					.build();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		System.out.println("Request Entity: "+requestEntiry.getRequest());
		GraphQLResponseEntity<Customer> responseEntity = graphQLTemplate.query(requestEntiry, clazz);
		System.out.println("Response Entity:"+ responseEntity.getResponse());
		return responseEntity.getResponse();
	}

	@GraphQLQuery(name = "order")
	public Order getOrderById(@GraphQLArgument(name = "id") String id) throws IllegalStateException, MalformedURLException {
		Order order = orderRepository.findById(id).orElseThrow(()->new RuntimeException("Order Not Found!"));
		Arguments arguments = new Arguments("customer", new Argument<String>("id", order.getCustId()));
		order.setCustomer((Customer) getRequest("http://localhost:8081/graphql", arguments, Customer.class));
		arguments = new Arguments("product", new Argument<String>("id", order.getProductId()));
		order.setProduct((Product) getRequest("http://localhost:8082/graphql", arguments, Product.class));
		return order;
	}
}
