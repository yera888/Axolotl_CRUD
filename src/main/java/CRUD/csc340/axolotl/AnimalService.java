package CRUD.csc340.axolotl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class AxolotlService {

    private final AxolotlRepository repo;

    public AxolotlService(final AxolotlRepository repo) {
        this.repo = repo;
    }

    // Reads
    public List<Axolotl> getAll() {
        return repo.findAll();
    }

    public Axolotl getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Axolotl> searchByName(String substring) {
        return repo.findByNameContainingIgnoreCase(substring);
    }

    public List<Axolotl> getByCategory(String category) {
        // category := morph
        return repo.findByMorphIgnoreCase(category);
    }

    public List<Axolotl> getBySex(String sex) {
        return repo.findBySexIgnoreCase(sex);
    }

    public Axolotl create(Axolotl a) {
        a.setId(null); // ignore client-supplied ID
        return repo.save(a);
    }

    public Axolotl update(Long id, Axolotl patch) {
        Optional<Axolotl> maybe = repo.findById(id);
        if (maybe.isEmpty()) return null;

        Axolotl cur = maybe.get();
        // Full replace for PUT (if you want PATCH behavior, add null checks)
        cur.setName(patch.getName());
        cur.setMorph(patch.getMorph());
        cur.setSex(patch.getSex());
        cur.setDescription(patch.getDescription());
        return repo.save(cur);
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
