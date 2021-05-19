package udacity.java.sample.graphql.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import udacity.java.sample.graphql.entity.Dog;
import udacity.java.sample.graphql.entity.Location;
import udacity.java.sample.graphql.exception.BreedNotFoundException;
import udacity.java.sample.graphql.exception.DogNotFoundException;
import udacity.java.sample.graphql.exception.LocationNotFoundException;
import udacity.java.sample.graphql.repository.DogRepository;
import udacity.java.sample.graphql.repository.LocationRepository;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
    private LocationRepository locationRepository;
    private DogRepository dogRepository;

    public Mutation(LocationRepository locationRepository, DogRepository dogRepository) {
        this.dogRepository = dogRepository;
        this.locationRepository = locationRepository;
    }

    //**************** Location **********************
    public Location newLocation(String name, String address) {
        Location location = new Location(name, address);
        locationRepository.save(location);
        return location;
    }

    public boolean deleteLocation(Long id) {
        locationRepository.deleteById(id);
        return true;
    }

    public Location updateLocationName(String newName, Long id) {
        Optional<Location> optionalLocation =
                locationRepository.findById(id);

        if(optionalLocation.isPresent()) {
            Location location = optionalLocation.get();
            location.setName(newName);
            locationRepository.save(location);
            return location;
        } else {
            throw new LocationNotFoundException("Location Not Found", id);
        }
    }

    //**************** Dog **********************
    public boolean deleteDogBreed(String breed) {
        boolean deleted = false;
        Iterable<Dog> allDogs = dogRepository.findAll();
        // Loop through all dogs to check their breed
        for (Dog d:allDogs) {
            if (d.getBreed().equals(breed)) {
                // Delete if the breed is found
                dogRepository.delete(d);
                deleted = true;
            }
        }
        // Throw an exception if the breed doesn't exist
        if (!deleted) {
            throw new BreedNotFoundException("Breed Not Found", breed);
        }
        return deleted;
    }

    public Dog updateDogName(String newName, Long id) {
        Optional<Dog> optionalDog = dogRepository.findById(id);

        if (optionalDog.isPresent()) {
            Dog dog = optionalDog.get();
            // Set the new name and save the updated dog
            dog.setName(newName);
            dogRepository.save(dog);
            return dog;
        } else {
            throw new DogNotFoundException("Dog Not Found", id);
        }
    }
}