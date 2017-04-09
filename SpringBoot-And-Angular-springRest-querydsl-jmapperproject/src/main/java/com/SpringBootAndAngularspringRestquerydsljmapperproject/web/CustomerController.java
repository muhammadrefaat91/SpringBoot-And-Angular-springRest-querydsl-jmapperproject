package com.elsewedy.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elsewedy.common.RestResponse;
import com.elsewedy.common.SearchCriteria;
import com.elsewedy.exception.RestResponseError;
import com.elsewedy.exception.RestResponseError.ErrorCode;
import com.elsewedy.exception.RestResponseException;
import com.elsewedy.exception.RuntimeSystemException;
import com.elsewedy.service.CustomerService;
import com.elsewedy.service.dto.CustomerDto;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<RestResponse> addCustomer(@RequestBody CustomerDto customerDto) {
		try {
			customerService.addCustomer(customerDto);
			RestResponse response = new RestResponse("Customer Added successfully");
			return new ResponseEntity(response, HttpStatus.CREATED);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	private RestResponseException handleException(Exception e) {
		if (e instanceof RuntimeSystemException) {
			RuntimeSystemException runtimeException = (RuntimeSystemException) e;
			RestResponseError error = (RestResponseError) runtimeException.getRestResponseError();
			if (error.getErrorCode() == ErrorCode.NOT_UNIQUE) {
				return new RestResponseException(HttpStatus.CONFLICT,
						new RestResponseError(error.getMessage(), error.getErrorCode()));
			} else if (error.getErrorCode() == ErrorCode.NULL) {
				return new RestResponseException(HttpStatus.UNPROCESSABLE_ENTITY,
						new RestResponseError(error.getMessage(), error.getErrorCode()));
			} else if (error.getErrorCode() == ErrorCode.NOT_FOUND) {
				return new RestResponseException(HttpStatus.NOT_FOUND,
						new RestResponseError(error.getMessage(), error.getErrorCode()));
			}
		} else {
			return new RestResponseException(HttpStatus.INTERNAL_SERVER_ERROR,
					new RestResponseError("Some thing went wrong", ErrorCode.UNEXPECTED_ERROR));
		}
		return null;
	}

	@RequestMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteCustomer(@RequestParam(value = "id") int id) {
		try {
			customerService.deleteCustomer(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	@RequestMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<CustomerDto> findCustomer(@RequestParam(value = "id") int id) {
		try {
			CustomerDto customerDto = customerService.findCustomer(id);
			return new ResponseEntity<CustomerDto>(customerDto, HttpStatus.OK);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponse> editCustomer(@RequestBody CustomerDto customerDto) {
		try {
			customerService.editCustomer(customerDto);
			RestResponse response = new RestResponse("Customer updated successfully");
			return new ResponseEntity<RestResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerDto>> searhAllCustomers(
			@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "mobileNumber", required = false) String mobileNumber) {
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setEmail(email);
		searchCriteria.setFirstName(firstName);
		searchCriteria.setLastName(lastName);
		searchCriteria.setMobileNumber(mobileNumber);
		List<CustomerDto> customerDtosList = customerService.searhAllCustomers(searchCriteria);
		return new ResponseEntity<List<CustomerDto>>(customerDtosList, HttpStatus.OK);
	}
}
