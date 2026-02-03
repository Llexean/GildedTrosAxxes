package com.gildedtros.gildedtros_spring_app.internal.updater;

import com.gildedtros.gildedtros_spring_app.internal.model.Item;
import org.springframework.stereotype.Component;

@Component
public class CommonItemUpdater implements ItemUpdater {
    static final int COMMON_ITEM_MAX_QUALITY = 50;
    static final int COMMON_ITEM_MIN_QUALITY = 0;

    @Override
    public void updateQuality(Item item) {
        item.decreaseQuality(item.getSellIn() <= 0 ? 2 : 1);
        clampQuality(item);
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
