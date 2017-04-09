package com.elsewedy.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.SpringBootAndAngularspringRestquerydsljmapperproject.repository.entities.QCustomer;
import com.elsewedy.common.SearchCriteria;
import com.elsewedy.repository.entities.Customer;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQuery;
 
@Repository
public class DefaultCustomerRepository extends RepositoryConfig<Customer> implements CustomerRepository{

	@PersistenceContext(unitName = "entityManagerFactory")
	@Override
	public void setEntityManager(EntityManager entityManager){
		super.setEntityManager(entityManager);
	}
	
	
	
	@Override
	public EntityPath<Customer> getDefaultEntityPath() {
		return QCustomer.customer;
	}
 
	@Override
	public Customer addCustomer(Customer customer) {
		 getEntityManager().persist(customer);
		 return customer;
	}

	@Override
	public boolean deleteCustomer(Customer customer) {
		if(!getEntityManager().contains(customer)) {
			return false;
		}
		getEntityManager().remove(customer);
		return true;
	}

	@Override
	public Customer editCustomer(Customer customer) {
		return getEntityManager().merge(customer);
	}
	
	@Override
	public Customer findCustomerById(int id) {
		return getEntityManager().find(getDefaultEntityPath().getType(), id);
	}
	
	@Override
	public Customer findCustomerByEmail(String email) {
		return getJpaQueryFactory().selectFrom(QCustomer.customer)
				.where(QCustomer.customer.email.eq(email))
				.fetchOne();
	}
	

	@Override
	public List<Customer> searhAllCustomers(SearchCriteria searchCriteria) {
		QCustomer qCustomer = QCustomer.customer;
		BooleanBuilder whereBuilder = new BooleanBuilder();
		JPAQuery<Customer> query = getJpaQueryFactory().selectFrom(qCustomer);
		
		if (searchCriteria != null) {
			if (searchCriteria.getFirstName() != null) {
				whereBuilder.and(qCustomer.firstName.containsIgnoreCase(searchCriteria.getFirstName()));
			}
			
			if (searchCriteria.getLastName() != null) {
				whereBuilder.and(qCustomer.LastName.containsIgnoreCase(searchCriteria.getLastName()));
			}
			
			if (searchCriteria.getEmail() != null) {
				whereBuilder.and(qCustomer.email.eq(searchCriteria.getEmail()));
			}
			
			if (searchCriteria.getMobileNumber() != null) {
				whereBuilder.and(qCustomer.mobileNumber.eq(searchCriteria.getMobileNumber()));
			}
		}
		
		query.where(whereBuilder);
		return query.fetch();
	}


}
