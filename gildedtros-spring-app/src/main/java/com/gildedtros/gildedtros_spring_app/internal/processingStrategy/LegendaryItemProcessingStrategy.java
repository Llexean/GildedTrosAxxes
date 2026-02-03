package com.gildedtros.gildedtros_spring_app.internal.processingStrategy;

import com.gildedtros.gildedtros_spring_app.internal.dto.ItemRequest;
import com.gildedtros.gildedtros_spring_app.internal.model.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LegendaryItemProcessingStrategy extends CommonItemProcessingStrategy {
    static final int LEGENDARY_ITEM_FIXED_QUALITY = 80;
    static final int LEGENDARY_ITEM_FIXED_SELLIN = 0;

    static final String[] LEGENDARY_NAMES_LIST = {"B-DAWG Keychain"};

    @Override
    public void updateQuality(Item item) {
        // nothing...
    }

    @Override
    public Item validateOnCreate(ItemRequest itemRequest) {
        if (!List.of(LEGENDARY_NAMES_LIST).contains(itemRequest.name())) {
            throw new IllegalArgumentException("Invalid Legendary Item Name");
        }

        Item item = new Item(itemRequest.name(), itemRequest.sellIn(), itemRequest.quality(), itemRequest.itemType());
        clampQuality(item);
        return item;
    }

    @Override
    public void clampQuality(Item item) {
        item.setQuality(LEGENDARY_ITEM_FIXED_QUALITY);
        item.setSellIn(LEGENDARY_ITEM_FIXED_SELLIN);
    }
}
