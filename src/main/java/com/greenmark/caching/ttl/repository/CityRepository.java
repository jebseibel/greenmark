package com.greenmark.caching.ttl.repository;

import com.greenmark.caching.ttl.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {}