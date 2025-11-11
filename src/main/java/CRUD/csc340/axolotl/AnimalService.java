package CRUD.csc340.axolotl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AnimalService {
    
    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public Animal getAnimalsById(long animalId) {
        return animalRepository.findById(animalId).orElse(null);
    }

    public List<Animal> getAnimalsByName(String name) {
        return animalRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Animal> getAnimalsByGender(String gender) {
        return animalRepository.findByGenderIgnoreCase(gender);
    }

    public List<Animal> getAnimalsByWeight(double weight){
        return animalRepository.findByWeight(weight);
    }

    public List<Animal> getAnimalsByAge(int age) {
        return animalRepository.findByAgeGreaterThanEqual(age);
    }

    public Animal addAnimal(Animal animal) {
        // Ensure we don't accidentally persist an ID supplied by client
        animal.setId(null);
        return animalRepository.save(animal);
    }

    public Animal updateAnimal(Long animalId, Animal animal) {
        Optional<Animal> existing = animalRepository.findById(animalId);
        if (existing.isEmpty()) return null;
        // set the id to ensure update rather than insert
        animal.setId(animalId);
        return animalRepository.save(animal);
    }

    public boolean deleteAnimal(Long animalId) {
        if (!animalRepository.existsById(animalId)) return false;
        animalRepository.deleteById(animalId);
        return true;
    }

    // JSON helpers (optional)
    public String writeJson(Animal animal) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("animals.json"), animal);
            return "Animal written to JSON file successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error writing animal to JSON file";
        }
    }

    public Animal readJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("animals.json"), Animal.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
