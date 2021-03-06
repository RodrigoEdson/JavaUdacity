package com.udacity.pricing.PriceService.repository;

import com.udacity.pricing.PriceService.domain.Price;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PriceRepository extends CrudRepository<Price, Long> {

    @Query("select p from Price p where p.vehicleId=:vehicleId")
    Optional<Price> findPriceByVehicleId(Long vehicleId);

}
