package udacity.java.sample.api.repository;

import org.springframework.data.repository.CrudRepository;
import udacity.java.sample.api.entity.Location;

public interface LocationRepository extends CrudRepository<Location, Long> {
}
