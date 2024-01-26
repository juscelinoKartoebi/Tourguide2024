package com.tourguide.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.tourguide.entities.DestinationEntity;
import com.tourguide.interfaces.CrudInterface;

public class DestinationService implements CrudInterface {

	private EntityManager entityManager;

	public DestinationService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void insert(Object object) {
		if (object instanceof DestinationEntity) {
			DestinationEntity destination = (DestinationEntity) object;
			if (destination != null) {
				EntityTransaction transaction = entityManager.getTransaction();
				try {
					transaction.begin();
					entityManager.persist(destination);
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
			DestinationEntity destination = (DestinationEntity) entityManager
					.createQuery("SELECT d FROM DestinationEntity d WHERE t.id = :id").setParameter("id", id)
					.getSingleResult();
			transaction.commit();
			return destination;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}

	@Override
	public void update(Object object) {
		if (object instanceof DestinationEntity) {
			DestinationEntity destination = (DestinationEntity) object;
			if (destination != null) {
				EntityTransaction transaction = entityManager.getTransaction();
				try {
					transaction.begin();
					entityManager.merge(destination);
					transaction.commit();
				} catch (Exception e) {
					transaction.rollback();
				}
			}
		}

	}

	@Override
	public void delete(Object object) {
		if (object instanceof DestinationEntity) {
			DestinationEntity destination = (DestinationEntity) object;
			if (destination != null) {
				EntityTransaction transaction = entityManager.getTransaction();
				try {
					transaction.begin();
					entityManager.remove(destination);
					transaction.commit();
				} catch (Exception e) {
					e.printStackTrace();
					transaction.rollback();
				}
			}
		}
	}

	public List<DestinationEntity> getAll() {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			List<DestinationEntity> destinations = entityManager.createQuery("SELECT d FROM DestinationEntity d")
					.getResultList();
			transaction.commit();
			return destinations;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}

}
