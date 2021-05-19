package udacity.java.sample.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import udacity.java.sample.api.entity.Location;
import udacity.java.sample.api.repository.LocationRepository;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService{

    private LocationRepository locationRepository;

    @Autowired
    public void setLocationRepository(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> retrieveLocations() {
        return (List<Location>) locationRepository.findAll();
    }

}
