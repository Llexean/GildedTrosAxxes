package com.gildedtros.gildedtros_spring_app.internal.processingStrategy;

import com.gildedtros.gildedtros_spring_app.internal.dto.ItemRequest;
import com.gildedtros.gildedtros_spring_app.internal.model.Item;
import org.springframework.stereotype.Component;

@Component
public class BackstagePassesItemProcessingStrategy extends CommonItemProcessingStrategy {
    static final String BACKSTAGE_PASSES_NAME_PREFIX = "Backstage passes for ";

    @Override
    public void updateQuality(Item item) {
        if (item.getSellIn() <= 0) {
            item.setQuality(COMMON_ITEM_MIN_QUALITY);
        } else if (item.getSellIn() <= 5) {
            item.increaseQuality(3);
        } else if (item.getSellIn() <= 10) {
            item.increaseQuality(2);
        } else {
            item.increaseQuality(1);
        }
        clampQuality(item);
    }

    @Override
    public Item validateOnCreate(ItemRequest itemRequest) {
        if(!itemRequest.name().contains(BACKSTAGE_PASSES_NAME_PREFIX)) {
            throw new IllegalArgumentException("Couldn't find valid Backstage Passes Prefix in given Item Name");
        }

        Item item = new Item(itemRequest.name(), itemRequest.sellIn(), itemRequest.quality(), itemRequest.itemType());
        clampQuality(item);
        return item;
    }
}
