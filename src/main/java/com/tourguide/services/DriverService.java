package com.tourguide.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.tourguide.entities.DriverEntity;
import com.tourguide.interfaces.CrudInterface;

public class DriverService implements CrudInterface {

	private EntityManager entityManager;

	public DriverService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void insert(Object object) {
		if (object instanceof DriverEntity) {
			DriverEntity driver = (DriverEntity) object;
			if (driver != null) {
				EntityTransaction transaction = entityManager.getTransaction();
				try {
					transaction.begin();
					entityManager.persist(driver);
					transaction.commit();
				} catch (Exception e) {
					transaction.rollback();
				}
			}
		}

	}

	@Override
	public Object getById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			DriverEntity driver = (DriverEntity) entityManager
					.createQuery("SELECT d FROM DriverEntity d WHERE d.id = :id").setParameter("id", id)
					.getSingleResult();
			transaction.commit();
			return driver;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return null;
		}
	}

	@Override
	public void update(Object object) {
		if (object instanceof DriverEntity) {
			DriverEntity driver = (DriverEntity) object;
			if (driver != null) {
				EntityTransaction transaction = entityManager.getTransaction();
				try {
					transaction.begin();
					entityManager.merge(driver);
					transaction.commit();
				} catch (Exception e) {
					transaction.rollback();
				}
			}
		}

	}

	@Override
	public void delete(Object object) {
		if (object instanceof DriverEntity) {
			DriverEntity driver = (DriverEntity) object;
			if (driver != null) {
				EntityTransaction transaction = entityManager.getTransaction();
				try {
					transaction.begin();
					entityManager.remove(driver);
					transaction.commit();
				} catch (Exception e) {
					e.printStackTrace();
					transaction.rollback();
				}
			}
		}
	}

	public List<DriverEntity> getAll() {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			List<DriverEntity> drivers = entityManager.createQuery("SELECT d FROM DriverEntity d").getResultList();
			transaction.commit();
			return drivers;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return null;
		}
	}

}
