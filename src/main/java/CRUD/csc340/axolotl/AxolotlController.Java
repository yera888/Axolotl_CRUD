package CRUD.csc340.axolotl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/axolotls")
public class AnimalController {

    @Autowired
    private AxolotlService axolotlService;

    // GET /animals
    @GetMapping
    public List<Axolotl> getAllAnimals() {
        return axolotlService.getAllAnimals();
    }

    // GET /animals/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Axolotl> getAnimalsById(@PathVariable long id) {
        axolotl a = axolotlService.getAnimalsById(id);
        if (a == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(a);
    }

    // GET /animals/name?key=substring
    @GetMapping("/name")
    public List<Animal> getAnimalsByName(@RequestParam(name = "key", required = false) String key) {
        if (key == null || key.isBlank()) {
            return animalService.getAllAnimals();
        }
        return animalService.getAnimalsByName(key);
    }

    // GET /animals/gender/{gender}
    @GetMapping("/gender/{gender}")
    public List<Animal> getAnimalsByGender(@PathVariable String gender) {
        return animalService.getAnimalsByGender(gender);
    }

    // GET /animals/weight?key=12.5
    @GetMapping("/weight")
    public List<Animal> getAnimalsByWeight(@RequestParam(name = "key") double key) {
        return animalService.getAnimalsByWeight(key);
    }

    // GET /animals/age?age=100  (default 100 if not provided)
    @GetMapping("/age")
    public ResponseEntity<List<Animal>> getAnimalsByAge(
            @RequestParam(name = "age", defaultValue = "100") int age) {
        return ResponseEntity.ok(animalService.getAnimalsByAge(age));
    }

    // POST /animals
    @PostMapping
    public ResponseEntity<Animal> addAnimal(@RequestBody Animal animal) {
        Animal saved = animalService.addAnimal(animal);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT /animals/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Axolotls> updateAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        Animal updated = animalService.updateAnimal(id, animal);
        if (updated == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(updated);
    }

    // DELETE /animals/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        boolean removed = animalService.deleteAnimal(id);
        if (!removed) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.noContent().build();
    }
}
