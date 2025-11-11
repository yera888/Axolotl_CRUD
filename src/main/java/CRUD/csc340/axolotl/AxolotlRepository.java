package CRUD.csc340.axolotl;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AxolotlRepository extends JpaRepository<Axolotl, Long> {

    List<Axolotl> findByNameContainingIgnoreCase(String name);

    List<Axolotl> findByMorphIgnoreCase(String morph);

    List<Axolotl> findBySexIgnoreCase(String sex);
}
