package com.gildedtros.gildedtros_spring_app.internal.updater;

import com.gildedtros.gildedtros_spring_app.internal.model.Item;
import org.springframework.stereotype.Component;

@Component
public class GoodWineItemUpdater extends CommonItemUpdater {
    @Override
    public void updateQuality(Item item) {
        item.increaseQuality(1);
        clampQuality(item);
    }
}
