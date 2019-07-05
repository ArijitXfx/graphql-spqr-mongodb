package com.tricon.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tricon.bean.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

}
