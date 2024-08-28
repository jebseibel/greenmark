package com.greenmark.caching.redis;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, String> {
}