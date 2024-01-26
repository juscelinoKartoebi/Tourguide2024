package com.tourguide.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.tourguide.chain.Chain;
import com.tourguide.chain.LongRoute;
import com.tourguide.chain.MidRoute;
import com.tourguide.chain.MinimumRoute;
import com.tourguide.entities.RouteEntity;
import com.tourguide.interfaces.CrudInterface;

public class RouteService implements CrudInterface {

	private EntityManager entityManager;

	public RouteService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void insert(Object object) {
		if (object instanceof RouteEntity) {
			RouteEntity route = (RouteEntity) object;
			if (route != null) {
				EntityTransaction transaction = entityManager.getTransaction();
				try {
					transaction.begin();
					entityManager.persist(route);
					transaction.commit();
				} catch (Exception e) {
					transaction.rollback();
				}
			}
		}

	}

	public Object getById(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			RouteEntity route = (RouteEntity) entityManager.createQuery("SELECT r FROM RouteEntity r WHERE r.id = :id")
					.setParameter("id", id).getSingleResult();
			transaction.commit();
			return route;
		} catch (Exception e) {
			transaction.rollback();
		}
		return null;
	}

	@Override
	public void update(Object object) {
		if (object instanceof RouteEntity) {
			RouteEntity route = (RouteEntity) object;
			if (route != null) {
				EntityTransaction transaction = entityManager.getTransaction();
				try {
					transaction.begin();
					entityManager.merge(route);
					transaction.commit();
				} catch (Exception e) {
					transaction.rollback();
				}
			}
		}

	}

	@Override
	public void delete(Object object) {
		if (object instanceof RouteEntity) {
			RouteEntity route = (RouteEntity) object;
			if (route != null) {
				EntityTransaction transaction = entityManager.getTransaction();
				try {
					transaction.begin();
					entityManager.remove(route);
					transaction.commit();
				} catch (Exception e) {
					e.printStackTrace();
					transaction.rollback();
				}
			}
		}

	}

	public List<RouteEntity> getAllRoutes() {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			List<RouteEntity> list = entityManager
					.createQuery("SELECT r FROM RouteEntity r JOIN r.from AS dfrom JOIN r.to AS dto").getResultList();
			transaction.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return null;
	}

	public String runChain(Long routeId) {

		RouteEntity route = (RouteEntity) getById(routeId);
		if (route != null) {
			Chain chain1 = new MinimumRoute();
			Chain chain2 = new MidRoute();
			Chain chain3 = new LongRoute();

			chain1.setNextChain(chain2);
			chain2.setNextChain(chain3);

			System.out.println(chain1.execute(route));
			return chain1.execute(route);
		} else {
			return "Route not found";
		}
	}

}
