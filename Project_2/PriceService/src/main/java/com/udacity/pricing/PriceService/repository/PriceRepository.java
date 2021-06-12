package com.udacity.pricing.PriceService.repository;

import com.udacity.pricing.PriceService.domain.Price;
import org.springframework.data.repository.CrudRepository;

public interface PriceRepository extends CrudRepository<Price, Long> {

}
