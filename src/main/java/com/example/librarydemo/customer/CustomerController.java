package com.example.librarydemo.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping()
	public ResponseEntity<List<Customer>> getAllCustomers(){
	    List<Customer> customers = customerService.getAllCustomers();
	    return new ResponseEntity<>(customers, HttpStatus.OK);
	}
	
	@GetMapping("/{userName}")
	public ResponseEntity<Customer> getCustomerByUserName(@PathVariable String userName){
	    Customer customer=  customerService.getCustomerByUserName(userName);
	    if(customer == null)
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
		customer = customerService.addCustomer(customer);
		if(customer == null)
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<>(customer, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{userName}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable String userName){
		 Customer customer = customerService.deleteCustomerByUsername(userName);
		 if(customer == null)
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}