package com.gildedtros.gildedtros_spring_app.internal.unit;

import com.gildedtros.gildedtros_spring_app.internal.dto.ItemRequest;
import com.gildedtros.gildedtros_spring_app.internal.model.Item;
import com.gildedtros.gildedtros_spring_app.internal.model.ItemType;
import com.gildedtros.gildedtros_spring_app.internal.processingStrategy.CommonItemProcessingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CommonItemProcessingStrategyTests {

    static final String COMMON_ITEM_NAME = "Common Item";
    static final String RESERVED_NAME = "Duplicate Code";
    static final String BACKSTAGE_PASS_NAME_PREFIX = "Backstage passes for ";

    private CommonItemProcessingStrategy commonItemProcessingStrategy;

    @BeforeEach
    public void setUp() {
        commonItemProcessingStrategy = new CommonItemProcessingStrategy();
    }

    private void validateCommonItemAfterTest(Item commonItem, int expectedSellIn, int expectedQuality) {
        assertEquals(COMMON_ITEM_NAME, commonItem.getName());
        assertEquals(expectedSellIn, commonItem.getSellIn());
        assertEquals(expectedQuality, commonItem.getQuality());
        assertEquals(ItemType.COMMON, commonItem.getItemType());
    }

    @Test
    public void validCommonItemIsCreated() {
        ItemRequest commonItemRequest = new ItemRequest(COMMON_ITEM_NAME, 5, 50, ItemType.COMMON);

        Item commonItem = commonItemProcessingStrategy.validateOnCreate(commonItemRequest);

        validateCommonItemAfterTest(commonItem, 5, 50);
    }

    @Test
    public void commonItemWithReservedNameWillThrowError() throws IllegalArgumentException {
        ItemRequest commonItemRequest = new ItemRequest(RESERVED_NAME, 5, 50, ItemType.COMMON);

        try {
            commonItemProcessingStrategy.validateOnCreate(commonItemRequest);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Reserved names cannot be used for common item", e.getMessage());
        }
    }

    @Test
    public void commonItemWithBackstagePassPrefixNameWillThrowError() throws IllegalArgumentException {
        ItemRequest commonItemRequest = new ItemRequest(BACKSTAGE_PASS_NAME_PREFIX + COMMON_ITEM_NAME, 5, 50, ItemType.COMMON);

        try {
            commonItemProcessingStrategy.validateOnCreate(commonItemRequest);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Common name cannot be prefixed with valid backstage passes prefix", e.getMessage());
        }
    }

    @Test
    public void commonItemQualityDecrease() {
        Item commonItem = new Item(COMMON_ITEM_NAME, 5, 50, ItemType.COMMON);

        commonItemProcessingStrategy.updateQuality(commonItem);

        validateCommonItemAfterTest(commonItem, 4, 49);
    }

    @Test
    public void commonItemQualityDecreaseTwiceIfSellDatePassed() {
        Item commonItem = new Item(COMMON_ITEM_NAME, 0, 50, ItemType.COMMON);

        commonItemProcessingStrategy.updateQuality(commonItem);

        validateCommonItemAfterTest(commonItem, -1, 48);
    }

    @Test
    public void commonItemQualityIsCappedTo50() {
        Item commonItem = new Item(COMMON_ITEM_NAME, 5, 100, ItemType.COMMON);

        commonItemProcessingStrategy.clampQuality(commonItem);

        validateCommonItemAfterTest(commonItem, 5, 50);
    }

    @Test
    public void commonItemQualityIsFlooredTo0() {
        Item commonItem = new Item(COMMON_ITEM_NAME, 0, -100, ItemType.COMMON);

        commonItemProcessingStrategy.clampQuality(commonItem);

        validateCommonItemAfterTest(commonItem, 0, 0);
    }
}
