package dao;

import java.util.ArrayList;
import java.util.Collection;

import beans.Identifiable;

public abstract class AbstractDao<T extends Identifiable> {
	ArrayList<T> entities = new ArrayList<>();
	public Collection<T> getAll() {
		return entities;
	}
	public T create(T entity) {
		entities.add(entity);
		return entity;
	}
	public void delete(String param) {
		for(T e : entities) {
			if(e.id().equals(param)) {
				entities.remove(e);
				return;
			}
		}
	}
	public T update(T entity) {
		for(T e : entities) {
			if(e.id().equals(entity.id())) {
				e = entity;
				return entity;
			}
		}
		return null;
	}
	public T find(String param) {
		for(T e : entities) {
			if(e.id().equals(param)) {
				return e;
			}
		}
		return null;
	}
}
