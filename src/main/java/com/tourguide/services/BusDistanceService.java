package com.tourguide.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.tourguide.entities.BusDistanceEntity;
import com.tourguide.interfaces.BusDistanceInterface;

public class BusDistanceService implements BusDistanceInterface {

	private EntityManager entityManager;

	public BusDistanceService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusDistanceEntity> getAll() {

		List<BusDistanceEntity> result = new ArrayList<>();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			result = entityManager.createQuery("SELECT b FROM BusDistanceEntity b").getResultList();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
		return result;
	}

}
