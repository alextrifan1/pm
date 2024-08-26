package Service;

import Domain.Entity;
import Domain.Piesa;
import Repository.ExceptionRepository;
import Repository.IRepository;

import java.util.Collection;

public class Service<T extends Entity> {

    IRepository<Piesa> repo;

    public Service(IRepository<Piesa> repo) {

        this.repo = repo;
    }
    public void add(Piesa a) throws ExceptionRepository {
        repo.add(a);
    }
    public void delete(int id) {

        repo.remove(id);
    }

    public void update(int pos, Piesa a) {

        repo.update(pos, a);
    }

    public Collection<Piesa> getAll() {

        return repo.getAll();
    }
}