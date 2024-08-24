package Repository;

import Domain.Entity;

import java.util.Collection;

public interface IRepository<T extends Entity> extends Iterable<T> {
    void add(T entity);
    void remove(int id);
    void update(int pos, T entity);
    T findById(int id);
    Collection<T> getAll();
}
