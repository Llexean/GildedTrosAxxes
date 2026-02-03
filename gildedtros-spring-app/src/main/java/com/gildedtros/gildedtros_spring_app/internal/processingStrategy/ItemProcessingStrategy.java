package com.gildedtros.gildedtros_spring_app.internal.processingStrategy;

import com.gildedtros.gildedtros_spring_app.internal.dto.ItemRequest;
import com.gildedtros.gildedtros_spring_app.internal.model.Item;

public interface ItemProcessingStrategy {
    void updateQuality(Item item);

    Item validateOnCreate(ItemRequest itemRequest);
}
