package hw01.task1.database.dao;

import hw01.task1.database.entities.Entity;

import java.util.List;

public interface BaseDAO<K, T extends Entity> {
    
    void insert(T t);
    int update(T t);
    void delete(T t);
    void delete(K id);
    List<T> selectAll();
    T selectOne(K id);
}
