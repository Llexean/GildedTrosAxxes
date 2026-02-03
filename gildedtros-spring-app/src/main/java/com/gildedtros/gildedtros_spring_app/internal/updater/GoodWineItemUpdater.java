package com.gildedtros.gildedtros_spring_app.internal.updater;

import com.gildedtros.gildedtros_spring_app.internal.dto.ItemRequest;
import com.gildedtros.gildedtros_spring_app.internal.model.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoodWineItemUpdater extends CommonItemUpdater {
    static final String[] GOOD_WINE_NAME_LIST = {"Good Wine"};

    @Override
    public void updateQuality(Item item) {
        item.increaseQuality(1);
        clampQuality(item);
    }

    @Override
    public Item validateOnCreate(ItemRequest itemRequest) {
        if (!List.of(GOOD_WINE_NAME_LIST).contains(itemRequest.name())) {
            throw new IllegalArgumentException("Invalid Good Wine Item Name");
        }

        Item item = new Item(itemRequest.name(), itemRequest.sellIn(), itemRequest.quality(), itemRequest.itemType());
        clampQuality(item);
        return item;
    }
}
