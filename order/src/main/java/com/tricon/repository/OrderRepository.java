package com.tricon.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tricon.bean.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>{

}
