package com.elsewedy;
import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.elsewedy.repository.CustomerRepository;
import com.elsewedy.repository.entities.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElsewedyProjectApplicationTests {

private Customer customer;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Before
	public void contextLoads() {
		customer = createCustomer();
	}
	
	@Test
	@Transactional
	public void addCustomer() {
		customer = customerRepository.addCustomer(customer);
		assertThat(customer).isNotNull();
		assertThat(customer.getId()).isNotNull();
		assertThat(customer.getAddress()).isNotNull();
		assertThat(customer.getEmail()).isNotNull();
		assertThat(customer.getFirstName()).isNotNull();
		assertThat(customer.getLastName()).isNotNull();
		assertThat(customer.getMobileNumber()).isNotNull();
 	}
	
	@Test
	@Transactional
	public void editCustomer() {
		Customer newcustomer = customerRepository.addCustomer(customer);
		
		Customer existedCustomer = customerRepository.findCustomerById(customer.getId());
		
		assertThat(newcustomer).isNotNull();
		assertThat(newcustomer.getId()).isNotNull();
		
		existedCustomer.setFirstName("Muhammad");
		existedCustomer.setLastName("Refaat Ali");
		existedCustomer = customerRepository.editCustomer(existedCustomer);
		assertThat(existedCustomer.getFirstName()).isNotEqualToIgnoringCase("mohamed");
		assertThat(existedCustomer.getLastName()).isNotEqualToIgnoringCase("Refaat");
	}
	
	private Customer createCustomer() {
		Customer customer  = new  Customer();
		customer.setAddress("Almohamdseen");
 		customer.setEmail("mohamedrefaat@gmail.com");
		customer.setFirstName("mohamed");
		customer.setLastName("Refaat");
		customer.setMobileNumber("01255861405");
		return customer;
	}

}
