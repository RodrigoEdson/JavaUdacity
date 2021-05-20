package udacity.java.sample.graphql.repository;

import org.springframework.data.repository.CrudRepository;
import udacity.java.sample.graphql.entity.Location;

public interface LocationRepository extends CrudRepository<Location, Long> {
}
