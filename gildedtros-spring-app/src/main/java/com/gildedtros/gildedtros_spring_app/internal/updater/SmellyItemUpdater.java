package com.gildedtros.gildedtros_spring_app.internal.updater;

import com.gildedtros.gildedtros_spring_app.internal.model.Item;
import org.springframework.stereotype.Component;

@Component
public class SmellyItemUpdater extends CommonItemUpdater {
    @Override
    public void updateQuality(Item item) {
        item.decreaseQuality(item.getSellIn() <= 0 ? 4 : 2);
        clampQuality(item);
    }
}
