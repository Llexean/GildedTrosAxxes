package com.gildedtros.gildedtros_spring_app.internal.processingStrategyFactory;

import com.gildedtros.gildedtros_spring_app.internal.model.ItemType;
import com.gildedtros.gildedtros_spring_app.internal.processingStrategy.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ItemProcessingStrategyFactory {
    private final Map<ItemType, ItemProcessingStrategy> updaters;

    public ItemProcessingStrategyFactory(
            CommonItemProcessingStrategy commonItemProcessingStrategy,
            SmellyItemProcessingStrategy smellyItemUpdater,
            GoodWineItemProcessingStrategy goodWineItemUpdater,
            BackstagePassesItemProcessingStrategy backstagePassesItemProcessingStrategy,
            LegendaryItemProcessingStrategy legendaryItemUpdater
    ) {
        this.updaters = Map.of(
                ItemType.COMMON, commonItemProcessingStrategy,
                ItemType.SMELLY, smellyItemUpdater,
                ItemType.GOOD_WINE, goodWineItemUpdater,
                ItemType.BACKSTAGE_PASS, backstagePassesItemProcessingStrategy,
                ItemType.LEGENDARY, legendaryItemUpdater
        );
    }

    public ItemProcessingStrategy get(ItemType itemType) {
        return this.updaters.get(itemType);
    }
}
