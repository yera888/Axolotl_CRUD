package CRUD.csc340.axolotl;

import jakarta.persistence.*;

@Entity
@Table(name = "axolotls")
public class Axolotl {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String morph; 
  private String sex;     

  @Column(columnDefinition = "TEXT", nullable = false)
  private String description;

  public Axolotl() {}

  public Axolotl(Long id, String name, String morph, String sex, String description) {
    this.id = id;
    this.name = name;
    this.morph = morph;
    this.sex = sex;
    this.description = description;
  }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getMorph() { return morph; }
  public void setMorph(String morph) { this.morph = morph; }

  public String getSex() { return sex; }
  public void setSex(String sex) { this.sex = sex; }

  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
}
