package com.gildedtros.gildedtros_spring_app.internal.updater;

import com.gildedtros.gildedtros_spring_app.internal.dto.ItemRequest;
import com.gildedtros.gildedtros_spring_app.internal.model.Item;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommonItemUpdater implements ItemUpdater {
    static final int COMMON_ITEM_MAX_QUALITY = 50;
    static final int COMMON_ITEM_MIN_QUALITY = 0;

    @Override
    public void updateQuality(Item item) {
        item.decreaseQuality(item.getSellIn() <= 0 ? 2 : 1);
        clampQuality(item);
    }

    @Override
    public Item validateOnCreate(ItemRequest itemRequest) {
        List<String> reservedNames = new ArrayList<>();
        reservedNames.addAll(List.of(GoodWineItemUpdater.GOOD_WINE_NAME_LIST));
        reservedNames.addAll(List.of(SmellyItemUpdater.SMELLY_ITEM_NAME_LIST));
        reservedNames.addAll(List.of(LegendaryItemUpdater.LEGENDARY_NAMES_LIST));

        if (reservedNames.contains(itemRequest.name())) {
            throw new IllegalArgumentException("Reserved names cannot be used for common item");
        } else if (itemRequest.name().contains(BackstagePassItemUpdater.BACKSTAGE_PASSES_NAME_PREFIX)) {
            throw new IllegalArgumentException("Common name cannot be prefixed with valid backstage passes prefix");
        }

        Item item = new Item(itemRequest.name(), itemRequest.sellIn(), itemRequest.quality(), itemRequest.itemType());
        clampQuality(item);
        return item;
    }

    public void clampQuality(Item item) {
        if (item.getQuality() > COMMON_ITEM_MAX_QUALITY) {
            item.setQuality(COMMON_ITEM_MAX_QUALITY);
        } else if (item.getQuality() < COMMON_ITEM_MIN_QUALITY) {
            item.setQuality(COMMON_ITEM_MIN_QUALITY);
        }

        item.decreaseSellIn();
    }
}
