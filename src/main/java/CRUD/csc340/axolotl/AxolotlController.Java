package CRUD.csc340.axolotl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; // used for parity with demo helpers
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AxolotlController {

  @Autowired
  private AxolotlService axolotlService;


  @GetMapping("/axolotls")
  public Object getAllAxolotls() {
    return axolotlService.getAllAxolotls();
  }

  @GetMapping("/axolotls/{id}")
  public Axolotl getAxolotlById(@PathVariable long id) {
    return axolotlService.getAxolotlById(id);
  }

  @PostMapping("/axolotls")
  public Object addAxolotl(@RequestBody Axolotl axolotl) {
    return axolotlService.addAxolotl(axolotl);
  }

  @PutMapping("/axolotls/{id}")
  public Axolotl updateAxolotl(@PathVariable Long id, @RequestBody Axolotl axolotl) {
    axolotlService.updateAxolotl(id, axolotl);
    return axolotlService.getAxolotlById(id);
  }

  @DeleteMapping("/axolotls/{id}")
  public Object deleteAxolotl(@PathVariable Long id) {
    axolotlService.deleteAxolotl(id);
    return axolotlService.getAllAxolotls();
  }


  @GetMapping("/axolotls/name")
  public Object getAxolotlsByName(@RequestParam(required = false) String key) {
    if (key != null && !key.isBlank()) {
      return axolotlService.getAxolotlsByName(key);
    } else {
      return axolotlService.getAllAxolotls();
    }
  }

  @GetMapping("/axolotls/morph/{morph}")
  public Object getAxolotlsByMorph(@PathVariable String morph) {
    return axolotlService.getAxolotlsByCategory(morph);
  }

  @GetMapping("/axolotls/sex/{sex}")
  public Object getAxolotlsBySex(@PathVariable String sex) {
    return axolotlService.getAxolotlsBySex(sex);
  }

  @GetMapping("/animals/search")
  public Object searchAnimalsByName(@RequestParam(name = "name") String name) {
    return axolotlService.getAxolotlsByName(name);
  }
