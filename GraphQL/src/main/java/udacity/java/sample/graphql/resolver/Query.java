package udacity.java.sample.graphql.resolver;


import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import udacity.java.sample.graphql.entity.Dog;
import udacity.java.sample.graphql.entity.Location;
import udacity.java.sample.graphql.exception.DogNotFoundException;
import udacity.java.sample.graphql.repository.DogRepository;
import udacity.java.sample.graphql.repository.LocationRepository;

import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {
    private LocationRepository locationRepository;
    private DogRepository dogRepository;

    public Query(LocationRepository locationRepository, DogRepository dogRepository) {
        this.locationRepository = locationRepository;
        this.dogRepository = dogRepository;
    }

    public Iterable<Location> findAllLocations() {
        return locationRepository.findAll();
    }

    public Iterable<Dog> findAllDogs() {
        return dogRepository.findAll();
    }

    public Dog findDogById(Long id) {
        Optional<Dog> optionalDog = dogRepository.findById(id);
        if (optionalDog.isPresent()) {
            return optionalDog.get();
        } else {
            throw new DogNotFoundException("Dog Not Found", id);
        }
    }
}
