package com.gildedtros.gildedtros_spring_app.internal.updaterfactory;

import com.gildedtros.gildedtros_spring_app.internal.model.ItemType;
import com.gildedtros.gildedtros_spring_app.internal.updater.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ItemUpdaterFactory {
    private final Map<ItemType, ItemUpdater> updaters;

    public ItemUpdaterFactory(
            CommonItemUpdater commonItemUpdater,
            SmellyItemUpdater smellyItemUpdater,
            GoodWineItemUpdater goodWineItemUpdater,
            BackstagePassItemUpdater backstagePassItemUpdater,
            LegendaryItemUpdater legendaryItemUpdater
    ) {
        this.updaters = Map.of(
                ItemType.COMMON, commonItemUpdater,
                ItemType.SMELLY, smellyItemUpdater,
                ItemType.GOOD_WINE, goodWineItemUpdater,
                ItemType.BACKSTAGE_PASS, backstagePassItemUpdater,
                ItemType.LEGENDARY, legendaryItemUpdater
        );
    }

    public ItemUpdater get(ItemType itemType) {
        return this.updaters.get(itemType);
    }
}
