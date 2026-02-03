package com.gildedtros.gildedtros_spring_app.internal.processingStrategy;

import com.gildedtros.gildedtros_spring_app.internal.dto.ItemRequest;
import com.gildedtros.gildedtros_spring_app.internal.model.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SmellyItemProcessingStrategy extends CommonItemProcessingStrategy {
    static final String[] SMELLY_ITEM_NAME_LIST = {"Duplicate Code", "Long Methods", "Ugly Variable Names"};

    @Override
    public void updateQuality(Item item) {
        item.decreaseQuality(item.getSellIn() <= 0 ? 4 : 2);
        clampQuality(item);
        item.decreaseSellIn();
    }

    @Override
    public Item validateOnCreate(ItemRequest itemRequest) {
        if (!List.of(SMELLY_ITEM_NAME_LIST).contains(itemRequest.name())) {
            throw new IllegalArgumentException("Invalid Smelly Item Name");
        }

        Item item = new Item(itemRequest.name(), itemRequest.sellIn(), itemRequest.quality(), itemRequest.itemType());
        clampQuality(item);
        return item;
    }
}
