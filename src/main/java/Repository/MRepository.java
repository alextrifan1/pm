package Repository;

import Domain.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MRepository<T extends Entity> implements IRepository<T> {
    protected List<T> entities;

    public MRepository() {
        this.entities = new ArrayList<>();
    }

    @Override
    public void add(T entity) {
        if (findById((entity.getId())) == null)
            entities.add(entity);
    }

    @Override
    public void remove(int id) {
        for(T e : entities)
            if(e.getId() == id) {
                entities.remove(e);
                return;
            }
    }

    @Override
    public void update(int poz, T entity){

        entities.set(poz, entity);
    }

    @Override
    public Collection getAll() {

        return new ArrayList<T>(entities);
    }

    @Override
    public T findById(int id) {
        for(T e : entities)
            if(e.getId() == id)
                return e;
        return null;
    }

    @Override
    public Iterator iterator() {

        return new ArrayList<T>(entities).iterator();
    }
}
