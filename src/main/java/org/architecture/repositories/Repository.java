package org.architecture.repositories;

public interface Repository<T, ID> {
    void save(T entity);

    Iterable<T> findAll();

    void update(T entity);

    void delete(ID id);

    T find(ID id);
}
