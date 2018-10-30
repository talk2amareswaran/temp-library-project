package com.example.librarydemo.customer;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, String > {
	Customer findByUserName(String userName);
}