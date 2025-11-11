package CRUD.csc340.axolotl;

import java.util.List;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/axolotls")
public class AxolotlController {

    private final AxolotlService service;

    public AxolotlController(final AxolotlService service) {
        this.service = service;
    }

    @GetMapping
    public List<Axolotl> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Axolotl> getById(@PathVariable Long id) {
        Axolotl a = service.getById(id);
        return (a == null) ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                           : ResponseEntity.ok(a);
    }

    @GetMapping("/search")
    public List<Axolotl> searchByName(@RequestParam(name = "name") String name) {
        return service.searchByName(name);
    }

    @GetMapping("/category/{category}")
    public List<Axolotl> getByCategory(@PathVariable String category) {
        return service.getByCategory(category);
    }

    @GetMapping("/sex/{sex}")
    public List<Axolotl> getBySex(@PathVariable String sex) {
        return service.getBySex(sex);
    }

    @PostMapping
    public ResponseEntity<Axolotl> create(@RequestBody Axolotl a) {
        Axolotl saved = service.create(a);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Axolotl> update(@PathVariable Long id, @RequestBody Axolotl a) {
        Axolotl updated = service.update(id, a);
        return (updated == null) ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                                 : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removed = service.delete(id);
        return removed ? ResponseEntity.noContent().build()
                       : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
