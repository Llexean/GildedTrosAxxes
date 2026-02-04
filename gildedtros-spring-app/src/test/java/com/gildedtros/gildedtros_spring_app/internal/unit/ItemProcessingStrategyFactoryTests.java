package com.gildedtros.gildedtros_spring_app.internal.unit;

import com.gildedtros.gildedtros_spring_app.internal.model.ItemType;
import com.gildedtros.gildedtros_spring_app.internal.processingStrategy.ItemProcessingStrategy;
import com.gildedtros.gildedtros_spring_app.internal.processingStrategy.LegendaryItemProcessingStrategy;
import com.gildedtros.gildedtros_spring_app.internal.processingStrategyFactory.ItemProcessingStrategyFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ItemProcessingStrategyFactoryTests {

    @Autowired
    private ItemProcessingStrategyFactory itemProcessingStrategyFactory;

    @Test
    public void itemProcessingStrategyFactoryResolvesCorrectStrategyFactory() {
        ItemProcessingStrategy itemProcessingStrategy = itemProcessingStrategyFactory.get(ItemType.LEGENDARY);

        assertNotNull(itemProcessingStrategy);
        assertInstanceOf(LegendaryItemProcessingStrategy.class, itemProcessingStrategy);
    }

    @Test
    public void ensureThatAllItemTypesHaveStrategyFactories() {
        for (ItemType itemType : ItemType.values()) {
            assertNotNull(itemProcessingStrategyFactory.get(itemType),
                    "Missing Policy for: " + itemType);
        }
    }
}
