package com.elsewedy.repository;

import javax.persistence.EntityManager;

import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQueryFactory;

public abstract class RepositoryConfig<T> {

	private JPAQueryFactory jpaQueryFactory;
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		jpaQueryFactory = new JPAQueryFactory(entityManager);
	}

	public abstract EntityPath<T> getDefaultEntityPath();

	public JPAQueryFactory getJpaQueryFactory() {
		return jpaQueryFactory;
	}

	public void setJpaQueryFactory(JPAQueryFactory jpaQueryFactory) {
		this.jpaQueryFactory = jpaQueryFactory;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

}
