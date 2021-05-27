package udacity.java.sample.microservice.repository;

import org.springframework.data.repository.CrudRepository;
import udacity.java.sample.microservice.entity.Dog;

public interface DogRepository extends CrudRepository<Dog,Long> {
}
