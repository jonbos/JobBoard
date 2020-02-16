package controller;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
	T get(int id);
	List<T> getAll();
	void insert(T t);
	void update(T t);
	void delete(T t);
}
