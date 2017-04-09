package com.elsewedy.service;

import java.util.List;

import com.elsewedy.common.SearchCriteria;
import com.elsewedy.service.dto.CustomerDto;

/**
 * @author muhammadrefaat
 *
 */
public interface CustomerService {

	/**
	 * add new customer
	 * 
	 * @param customerDto
	 */
	void addCustomer(CustomerDto customerDto);
	
	/** delete customer by id
	 * 
	 * @param id
	 */
	void deleteCustomer(int id);
	
	/**
	 * edit customer info by id
	 * 
	 * @param id 
	 */
	void editCustomer(CustomerDto customerDto);
	
	/** search for customers by some criteria 
	 * 
	 * @param searchCriteria
	 * @return 
	 */
	List<CustomerDto> searhAllCustomers(SearchCriteria searchCriteria);

	CustomerDto findCustomer(int id);


}
