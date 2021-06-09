package com.udacity.pricing.service;

import com.udacity.pricing.domain.price.Price;
import com.udacity.pricing.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implements the pricing service to get prices for each vehicle.
 */
@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    /**
     * If a valid vehicle ID, gets the price of the vehicle from the database.
     * @param vehicleId ID number of the vehicle the price is requested for.
     * @return price of the requested vehicle
     * @throws PriceException vehicleID was not found
     */
    public Price getPrice(Long vehicleId) throws PriceException {

        Price p = priceRepository.findPriceByVehicleId(vehicleId);

        if (p.getVehicleId() == null) {
            throw new PriceException("Cannot find price for Vehicle " + vehicleId);
        }

        return p;
    }


}
