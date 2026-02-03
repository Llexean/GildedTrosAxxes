package com.gildedtros.gildedtros_spring_app.internal.updater;

import com.gildedtros.gildedtros_spring_app.internal.model.Item;
import org.springframework.stereotype.Component;

@Component
public class LegendaryItemUpdater extends CommonItemUpdater {
    static final int LEGENDARY_ITEM_FIXED_QUALITY = 80;
    static final int LEGENDARY_ITEM_FIXED_SELLIN = 0;

    @Override
    public void clampQuality(Item item) {
        item.setQuality(LEGENDARY_ITEM_FIXED_QUALITY);
        item.setSellIn(LEGENDARY_ITEM_FIXED_SELLIN);
    }
}
