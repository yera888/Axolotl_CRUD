package CRUD.csc340.axolotl;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AxolotlService {

  @Autowired
  private AxolotlRepository axolotlRepository;

  public Object getAllAxolotls() {
    return axolotlRepository.findAll();
  }

  public Axolotl getAxolotlById(@PathVariable long axolotlId) {
    return axolotlRepository.findById(axolotlId).orElse(null);
  }

  public Object getAxolotlsByName(String name) {
    return axolotlRepository.findByNameContainingIgnoreCase(name);
  }

  public Object getAxolotlsByCategory(String category) {
    return axolotlRepository.findByMorphIgnoreCase(category);
  }

  public Object getAxolotlsBySex(String sex) {
    return axolotlRepository.findBySexIgnoreCase(sex);
  }

  public Axolotl addAxolotl(Axolotl axolotl) {
    axolotl.setId(null); // ensure DB generates the ID
    return axolotlRepository.save(axolotl);
  }

  public Axolotl updateAxolotl(Long axolotlId, Axolotl axolotl) {
    axolotl.setId(axolotlId);
    return axolotlRepository.save(axolotl);
  }

  public void deleteAxolotl(Long axolotlId) {
    axolotlRepository.deleteById(axolotlId);
  }

  public String writeJson(Axolotl axolotl) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      objectMapper.writeValue(new File("axolotls.json"), axolotl);
      return "Axolotl written to JSON file successfully";
    } catch (IOException e) {
      e.printStackTrace();
      return "Error writing axolotl to JSON file";
    }
  }

  public Object readJson() {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(new File("axolotls.json"), Axolotl.class);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
