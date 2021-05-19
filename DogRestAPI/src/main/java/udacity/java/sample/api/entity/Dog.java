package udacity.java.sample.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String name;
    private String breed;
    private String Origin;

    public Dog() {
    }

    public Dog(Long id, String name, String breed, String origin) {
        Id = id;
        this.name = name;
        this.breed = breed;
        Origin = origin;
    }

    public Dog(String name, String breed) {
        this.name = name;
        this.breed = breed;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }
}

