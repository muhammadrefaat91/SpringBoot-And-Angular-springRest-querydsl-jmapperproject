package com.elsewedy.repository;

import java.util.List;

import com.elsewedy.common.SearchCriteria;
import com.elsewedy.repository.entities.Customer;

public interface CustomerRepository {

	/**
	 * add new customer
	 * 
	 * @param customer
	 * @return 
	 */
	Customer addCustomer(Customer customerDto);
	
	/** delete customer by id
	 * 
	 * @param id
	 */
	boolean deleteCustomer(Customer customer);
	
	/**
	 * edit customer info by id
	 * 
	 * @param id 
	 */
	Customer editCustomer(Customer customer);
	
	/** search for customers by some criteria 
	 * 
	 * @param searchCriteria
	 * @return 
	 */
	List<Customer> searhAllCustomers(SearchCriteria searchCriteria);

	Customer findCustomerById(int id);

	Customer findCustomerByEmail(String email);

}
