package CRUD.csc340.axolotl;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String name;

    // age in years (integer, can be 0)
    @Min(0)
    private Integer age;

    // weight in kilograms (or your preferred unit)
    @DecimalMin(value = "0.0", inclusive = true)
    private Double weight;

    // gender/sex string
    private String gender;

    // longer text / details about the animal
    @Column(columnDefinition = "TEXT")
    private String summary;

    // --- Constructors ---
    public Animal() {}

    public Animal(Long id, String name, Integer age, Double weight, String gender, String summary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
        this.summary = summary;
    }

    public Animal(String name, Integer age, Double weight, String gender, String summary) {
        this(null, name, age, weight, gender, summary);
    }

    // --- Getters / Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
}
