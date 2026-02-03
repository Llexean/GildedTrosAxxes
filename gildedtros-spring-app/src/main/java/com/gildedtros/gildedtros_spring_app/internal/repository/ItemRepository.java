package com.gildedtros.gildedtros_spring_app.internal.repository;

import com.gildedtros.gildedtros_spring_app.internal.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}