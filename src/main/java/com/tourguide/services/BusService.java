package com.tourguide.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.tourguide.entities.BusEntity;
import com.tourguide.interfaces.CrudInterface;

public class BusService implements CrudInterface {

	private EntityManager entityManager;

	public BusService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void insert(Object object) {
		if (object instanceof BusEntity) {
			BusEntity bus = (BusEntity) object;
			if (bus != null) {
				EntityTransaction transaction = entityManager.getTransaction();
				try {
					transaction.begin();
					entityManager.persist(bus);
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
			BusEntity bus = (BusEntity) entityManager.createQuery("SELECT b FROM BusEntity b WHERE b.id = :id")
					.setParameter("id", id).getSingleResult();
			transaction.commit();
			return bus;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}

	@Override
	public void update(Object object) {
		if (object instanceof BusEntity) {
			BusEntity bus = (BusEntity) object;
			if (bus != null) {
				EntityTransaction transaction = entityManager.getTransaction();
				try {
					transaction.begin();
					entityManager.merge(bus);
					transaction.commit();
				} catch (Exception e) {
					transaction.rollback();
				}
			}
		}

	}

	@Override
	public void delete(Object object) {
		if (object instanceof BusEntity) {
			BusEntity bus = (BusEntity) object;
			if (bus != null) {
				EntityTransaction transaction = entityManager.getTransaction();
				try {
					transaction.begin();
					entityManager.remove(bus);
					transaction.commit();
				} catch (Exception e) {
					transaction.rollback();
				}
			}
		}
	}
	
	public List<BusEntity> getAll() {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			List<BusEntity> list = entityManager
					.createQuery("SELECT r FROM BusEntity r").getResultList();
			transaction.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

}
