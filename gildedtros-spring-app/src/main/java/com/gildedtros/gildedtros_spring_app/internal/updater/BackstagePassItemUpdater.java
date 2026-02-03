package com.gildedtros.gildedtros_spring_app.internal.updater;

import com.gildedtros.gildedtros_spring_app.internal.model.Item;
import org.springframework.stereotype.Component;

@Component
public class BackstagePassItemUpdater extends CommonItemUpdater {
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
}
