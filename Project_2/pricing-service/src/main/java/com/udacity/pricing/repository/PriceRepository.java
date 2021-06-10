package com.udacity.pricing.repository;

import com.udacity.pricing.domain.Price;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface PriceRepository extends CrudRepository<Price, Long> {

}
