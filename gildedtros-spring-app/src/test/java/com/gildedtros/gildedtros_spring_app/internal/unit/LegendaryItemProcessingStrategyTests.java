package com.gildedtros.gildedtros_spring_app.internal.unit;

import com.gildedtros.gildedtros_spring_app.internal.dto.ItemRequest;
import com.gildedtros.gildedtros_spring_app.internal.model.Item;
import com.gildedtros.gildedtros_spring_app.internal.model.ItemType;
import com.gildedtros.gildedtros_spring_app.internal.processingStrategy.LegendaryItemProcessingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class LegendaryItemProcessingStrategyTests {
    static final String[] LEGENDARY_NAMES_LIST = {"B-DAWG Keychain"};
    static final String INVALID_LEGENDARY_ITEM_NAME = "Invalid Legendary Item Name";

    LegendaryItemProcessingStrategy legendaryItemProcessingStrategy;

    @BeforeEach
    public void setUp() {
        legendaryItemProcessingStrategy = new LegendaryItemProcessingStrategy();
    }

    private void validateLegendaryItemAfterTest(Item legendaryItem) {
        assertEquals(LEGENDARY_NAMES_LIST[0], legendaryItem.getName());
        assertEquals(0, legendaryItem.getSellIn());
        assertEquals(80, legendaryItem.getQuality());
        assertEquals(ItemType.LEGENDARY, legendaryItem.getItemType());
    }

    @Test
    public void validLegendaryItemIsCreated() {
        ItemRequest legendaryItemRequest = new ItemRequest(LEGENDARY_NAMES_LIST[0], 0, 80, ItemType.LEGENDARY);

        Item legendaryItem = legendaryItemProcessingStrategy.validateOnCreate(legendaryItemRequest);

        validateLegendaryItemAfterTest(legendaryItem);
    }

    @Test
    public void legendaryItemWithoutValidNameWillThrowError() throws IllegalArgumentException {
        ItemRequest legendaryItemRequest = new ItemRequest(INVALID_LEGENDARY_ITEM_NAME, 0, 80, ItemType.LEGENDARY);

        try {
            legendaryItemProcessingStrategy.validateOnCreate(legendaryItemRequest);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid Legendary Item Name", e.getMessage());
        }
    }

    @Test
    public void legendaryItemQualityIsFixedAt80() {
        Item legendaryItem = new Item(LEGENDARY_NAMES_LIST[0], 0, 100, ItemType.LEGENDARY);

        legendaryItemProcessingStrategy.clampQuality(legendaryItem);

        validateLegendaryItemAfterTest(legendaryItem);
    }

    @Test
    public void legendaryItemSellInIsFixedAt0() {
        Item legendaryItem = new Item(LEGENDARY_NAMES_LIST[0], -50, 80, ItemType.LEGENDARY);

        legendaryItemProcessingStrategy.clampQuality(legendaryItem);

        validateLegendaryItemAfterTest(legendaryItem);
    }
}
