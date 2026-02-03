package com.gildedtros.gildedtros_spring_app.internal.dto;

import com.gildedtros.gildedtros_spring_app.internal.model.ItemType;

public record ItemRequest(String name, int sellIn, int quality, ItemType itemType) {
}
