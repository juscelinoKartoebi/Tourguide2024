package com.tourguide.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.tourguide.entities.TourEntity;
import com.tourguide.interfaces.CrudInterface;
import com.tourguide.interfaces.TourInterface;

public class TourService implements CrudInterface, TourInterface {

	private EntityManager entityManager;

	public TourService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void insert(Object object) {
		if (object instanceof TourEntity) {
			TourEntity tour = (TourEntity) object;
			if (tour != null) {
				EntityTransaction transaction = entityManager.getTransaction();
				try {
					transaction.begin();
					entityManager.persist(tour);
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
			TourEntity tour = (TourEntity) entityManager.createQuery("SELECT t FROM TourEntity t WHERE t.id = :id")
					.setParameter("id", id).getSingleResult();
			transaction.commit();
			return tour;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}

	@Override
	public void update(Object object) {
		if (object instanceof TourEntity) {
			TourEntity tour = (TourEntity) object;
			if (tour != null) {
				EntityTransaction transaction = entityManager.getTransaction();
				try {
					transaction.begin();
					entityManager.merge(tour);
					transaction.commit();
				} catch (Exception e) {
					transaction.rollback();
				}
			}
		}

	}

	@Override
	public void delete(Object object) {
		if (object instanceof TourEntity) {
			TourEntity tour = (TourEntity) object;
			if (tour != null) {
				EntityTransaction transaction = entityManager.getTransaction();
				try {
					transaction.begin();
					entityManager.remove(tour);
					transaction.commit();
				} catch (Exception e) {
					e.printStackTrace();
					transaction.rollback();
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TourEntity> getTourByDate(Date date) {
		List<TourEntity> result = new ArrayList<>();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			result = entityManager.createQuery("SELECT t FROM TourEntity t WHERE t.tourDate = :date")
					.setParameter("date", date).getResultList();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TourEntity> getTourByDriver(Long driverId) {
		List<TourEntity> result = new ArrayList<>();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			result = entityManager.createQuery("SELECT t FROM TourEntity t WHERE t.driver = :driverId")
					.setParameter("driverId", driverId).getResultList();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
		return result;
	}

	@Override
	public List<TourEntity> getTourByName(String tourName) {
		List<TourEntity> result = new ArrayList<>();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			result = entityManager
					.createQuery("SELECT t FROM TourEntity t WHERE LOWER(t.tourName) LIKE LOWER(:tourName)")
					.setParameter("tourName", "%" + tourName + "%").getResultList();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return result;
	}

	@Override
	public List<TourEntity> getTourByDriver(String name) {
		List<TourEntity> result = new ArrayList<TourEntity>();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			result = entityManager
					.createQuery("SELECT t FROM TourEntity t JOIN t.driver AS d WHERE LOWER(d.name) LIKE LOWER(:name)")
					.setParameter("name", name).getResultList();

			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
		return result;
	}

	public List<TourEntity> getAll() {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			List<TourEntity> result = entityManager.createQuery("SELECT t FROM TourEntity t", TourEntity.class).getResultList();
			transaction.commit();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return null;
		}
	}

	public List<TourEntity> getByMonth(Integer month) {
		List<TourEntity> result = new ArrayList<TourEntity>();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			result = entityManager
					.createQuery("SELECT t FROM TourEntity t WHERE MONTH(t.tourDate) = :month", TourEntity.class)
					.setParameter("month", month).getResultList();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
		return result;
	}

	public List<TourEntity> getByYear(Integer year) {
		List<TourEntity> result = new ArrayList<TourEntity>();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			result = entityManager
					.createQuery("SELECT t FROM TourEntity t WHERE YEAR(t.tourDate) = :year", TourEntity.class)
					.setParameter("year", year).getResultList();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
		return result;
	}

}
