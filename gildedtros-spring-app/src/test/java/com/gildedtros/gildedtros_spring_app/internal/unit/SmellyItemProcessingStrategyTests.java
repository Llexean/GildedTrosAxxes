package com.gildedtros.gildedtros_spring_app.internal.unit;

import com.gildedtros.gildedtros_spring_app.internal.dto.ItemRequest;
import com.gildedtros.gildedtros_spring_app.internal.exception.InvalidItemNameException;
import com.gildedtros.gildedtros_spring_app.internal.model.Item;
import com.gildedtros.gildedtros_spring_app.internal.model.ItemType;
import com.gildedtros.gildedtros_spring_app.internal.processingStrategy.SmellyItemProcessingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SmellyItemProcessingStrategyTests {
    static final String[] SMELLY_ITEMS_NAME_LIST = {"Duplicate Code", "Long Methods", "Ugly Variable Names"};
    static final String INVALID_SMELLY_ITEM_NAME = "Invalid Smelly Item Name";

    private SmellyItemProcessingStrategy smellyItemProcessingStrategy;

    @BeforeEach
    public void setUp() {
        smellyItemProcessingStrategy = new SmellyItemProcessingStrategy();
    }

    private void validateSmellyItemAfterTest(Item smellyItem, int expectedSellIn, int expectedQuality) {
        assertEquals(SMELLY_ITEMS_NAME_LIST[0], smellyItem.getName());
        assertEquals(expectedSellIn, smellyItem.getSellIn());
        assertEquals(expectedQuality, smellyItem.getQuality());
        assertEquals(ItemType.SMELLY, smellyItem.getItemType());
    }

    @Test
    public void validSmellyItemIsCreated() {
        ItemRequest smellyItemRequest = new ItemRequest(SMELLY_ITEMS_NAME_LIST[0], 5, 50, ItemType.SMELLY);

        Item smellyItem = smellyItemProcessingStrategy.validateOnCreate(smellyItemRequest);

        validateSmellyItemAfterTest(smellyItem, 5, 50);
    }

    @Test
    public void smellyItemWithoutValidNameWillThrowError() throws InvalidItemNameException {
        ItemRequest smellyItemRequest = new ItemRequest(INVALID_SMELLY_ITEM_NAME, 5, 50, ItemType.SMELLY);

        try {
            smellyItemProcessingStrategy.validateOnCreate(smellyItemRequest);
            fail();
        } catch (InvalidItemNameException e) {
            assertEquals("Invalid Smelly Item Name", e.getMessage());
        }
    }

    @Test
    public void smellyItemQualityDecrease() {
        Item smellyItem = new Item(SMELLY_ITEMS_NAME_LIST[0], 5, 50, ItemType.SMELLY);

        smellyItemProcessingStrategy.updateQuality(smellyItem);

        validateSmellyItemAfterTest(smellyItem, 4, 48);
    }

    @Test
    public void smellyItemQualityDecreaseTwiceIfSellDatePassed() {
        Item smellyItem = new Item(SMELLY_ITEMS_NAME_LIST[0], 0, 50, ItemType.SMELLY);

        smellyItemProcessingStrategy.updateQuality(smellyItem);

        validateSmellyItemAfterTest(smellyItem, -1, 46);
    }

    @Test
    public void smellyItemQualityIsCappedTo50() {
        Item smellyItem = new Item(SMELLY_ITEMS_NAME_LIST[0], 5, 100, ItemType.SMELLY);

        smellyItemProcessingStrategy.clampQuality(smellyItem);

        validateSmellyItemAfterTest(smellyItem, 5, 50);
    }

    @Test
    public void smellyItemQualityIsFlooredTo0() {
        Item smellyItem = new Item(SMELLY_ITEMS_NAME_LIST[0], 0, -100, ItemType.SMELLY);

        smellyItemProcessingStrategy.clampQuality(smellyItem);

        validateSmellyItemAfterTest(smellyItem, 0, 0);
    }
}
