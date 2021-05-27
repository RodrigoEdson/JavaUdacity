package udacity.java.sample.graphql.repository;

import org.springframework.data.repository.CrudRepository;
import udacity.java.sample.graphql.entity.Dog;

public interface DogRepository extends CrudRepository<Dog, Long> {
}
