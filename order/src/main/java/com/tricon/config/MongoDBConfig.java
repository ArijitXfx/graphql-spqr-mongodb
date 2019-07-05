package com.tricon.config;

import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.tricon.bean.Customer;
import com.tricon.bean.Order;
import com.tricon.bean.Product;
import com.tricon.repository.OrderRepository;
import com.tricon.repository.ProductRepository;
import com.tricon.repository.CustomerRepository;

@EnableMongoRepositories(basePackages = {"com.tricon.repository"})
@Configuration
public class MongoDBConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository, ProductRepository productRepository, OrderRepository orderRepository) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				// TODO Auto-generated method stub
				customerRepository.save(new Customer("1","arijit.basu@gmail.com","arijit.password"));
				customerRepository.save(new Customer("2","soura.nandi@gmail.com","soura.password"));
				customerRepository.save(new Customer("3","sarthak.agarwal@gmail.com","sarthak.password"));
				
				productRepository.save(new Product("1","Product1",100));
				productRepository.save(new Product("2","Product2",200));
				productRepository.save(new Product("3","Product3",300));
				
				List<String> list = new LinkedList<String>();
				list.add("1");list.add("2"); list.add("3");
				orderRepository.save(new Order("1", "1", list));
				list.remove(0);
				orderRepository.save(new Order("2","2",list));
				list.remove(0);
				orderRepository.save(new Order("3", "3", list));
			}
		};
	}
}

