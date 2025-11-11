package CRUD.csc340.axolotl;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    // substring name search (case-insensitive)
    List<Animal> findByNameContainingIgnoreCase(String name);

    // age >= threshold
    List<Animal> findByAgeGreaterThanEqual(int age);

    // derived queries for weight and gender
    List<Animal> findByWeight(double weight);
    List<Animal> findByGenderIgnoreCase(String gender);

    // If you prefer native SQL for name:
    // @Query(value = "select * from animals a where a.name like %?1% ", nativeQuery = true)
    // List<Animal> getAnimalsByName(String name);
}
