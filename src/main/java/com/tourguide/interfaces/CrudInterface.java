package com.tourguide.interfaces;

import java.util.List;

public interface CrudInterface {

	public void insert(Object object);

	public Object getById(Long id);

	public void update(Object object);

	public void delete(Object object);

}
