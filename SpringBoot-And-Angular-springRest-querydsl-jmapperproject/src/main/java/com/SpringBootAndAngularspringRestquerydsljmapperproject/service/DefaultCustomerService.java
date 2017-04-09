package com.elsewedy.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elsewedy.common.SearchCriteria;
import com.elsewedy.exception.RestResponseError;
import com.elsewedy.exception.RestResponseError.ErrorCode;
import com.elsewedy.exception.RuntimeSystemException;
import com.elsewedy.repository.CustomerRepository;
import com.elsewedy.repository.entities.Customer;
import com.elsewedy.service.dto.CustomerDto;
import com.googlecode.jmapper.JMapper;

@Service
public class DefaultCustomerService implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	@Transactional
	public void addCustomer(CustomerDto customerDto) {
		if (customerDto == null || (customerDto.getAddress() == null || customerDto.getEmail() == null
				|| customerDto.getFirstName() == null || customerDto.getLastName()== null 
				|| customerDto.getMobileNumber() == null)) {
			RestResponseError error = new RestResponseError("All fields are required!", ErrorCode.NULL);
			throw new RuntimeSystemException(error);
		}
		
		Customer existedCustomer = customerRepository.findCustomerByEmail(customerDto.getEmail());
		if (existedCustomer != null) {
			RestResponseError error = new RestResponseError("A Customer with email " + existedCustomer.getEmail() + " already exist", ErrorCode.NOT_UNIQUE);
			throw new RuntimeSystemException(error);
		} 
		Customer customer = transformFromDestinationToSoure(Customer.class, customerDto, CustomerDto.class);
 		customer =  customerRepository.addCustomer(customer);
		
	}

	public <D, S> D transformFromDestinationToSoure(Class<D> destination, S sourceObj, Class<S> source) {
		JMapper<D , S> mapper = new JMapper<>(destination, source);
		return mapper.getDestination(sourceObj);
	}


	public <D, S> List<D> transformList(Class<D> destination, List<S> sourceObjList, Class<S> source) {
		JMapper<D , S> mapper = new JMapper<>(destination, source);
		List<D> results = sourceObjList.stream().map((s)-> mapper.getDestination(s)).collect(Collectors.toList());
		return results;
	}

	@Override
	@Transactional
	public void deleteCustomer(int id) {
		Customer customer = customerRepository.findCustomerById(id);
		if (customer == null) {
			RestResponseError error = new RestResponseError("Customer doesn't exist", ErrorCode.NOT_FOUND);
			throw new RuntimeSystemException(error);
		}
		customerRepository.deleteCustomer(customer);
	}
	
	@Override
	@Transactional
	public CustomerDto findCustomer(int id) {
		Customer customer = customerRepository.findCustomerById(id);
		if (customer == null) {
			RestResponseError error = new RestResponseError("Customer doesn't exist", ErrorCode.NOT_FOUND);
			throw new RuntimeSystemException(error);
		}
		CustomerDto customerDto = transformFromDestinationToSoure(CustomerDto.class, customer, Customer.class);
		return customerDto;
	}

	@Override
	@Transactional
	public void editCustomer(CustomerDto customerDto) {
		if (customerDto == null || (customerDto.getAddress() == null || customerDto.getEmail() == null
				|| customerDto.getFirstName() == null || customerDto.getLastName()== null 
				|| customerDto.getMobileNumber() == null)) {
			RestResponseError error = new RestResponseError("All fields are required!", ErrorCode.NULL);
			throw new RuntimeSystemException(error);
		}
		Customer existedCustomer = customerRepository.findCustomerByEmail(customerDto.getEmail());
		if (existedCustomer == null) {
			RestResponseError error = new RestResponseError("Customer doesn't exist", ErrorCode.NOT_UNIQUE);
			throw new RuntimeSystemException(error);
		} 
		Customer customer = transformFromDestinationToSoure(Customer.class, customerDto, CustomerDto.class);
		customerRepository.editCustomer(customer);
	}

	@Override
	public List<CustomerDto> searhAllCustomers(SearchCriteria searchCriteria) {
		List<Customer> customersList =  customerRepository.searhAllCustomers(searchCriteria);
		return transformList(CustomerDto.class, customersList, Customer.class);
	}

}
