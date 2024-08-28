package com.greenmark.caching.ttl.repository;

import com.greenmark.caching.ttl.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    default List<Hotel> getAllHotels() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return findAll();
    }
}