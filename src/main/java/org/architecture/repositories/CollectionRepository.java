package org.architecture.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@org.springframework.stereotype.Repository
public abstract class CollectionRepository<T, ID> implements Repository<T, ID> {
    private final Map<ID, T> dataStore = new HashMap<>();
    protected final AtomicLong idCounter = new AtomicLong(0);

    @Override
    public void save(T entity) {
        ID id = generateId();
        setId(entity, id);
        dataStore.put(id, entity);
    }

    @Override
    public Iterable<T> findAll() {
        return dataStore.values();
    }

    @Override
    public void update(T entity) {
        ID id = getId(entity);
        if (dataStore.containsKey(id)) {
            dataStore.put(id, entity);
        } else {
            throw new IllegalArgumentException("Entité non trouvée");
        }
    }

    @Override
    public void delete(ID id) {
        dataStore.remove(id);
    }

    @Override
    public T find(ID id) {
        return dataStore.get(id);
    }

    protected abstract ID generateId();

    protected abstract ID getId(T entity);

    protected abstract void setId(T entity, ID id);
}
