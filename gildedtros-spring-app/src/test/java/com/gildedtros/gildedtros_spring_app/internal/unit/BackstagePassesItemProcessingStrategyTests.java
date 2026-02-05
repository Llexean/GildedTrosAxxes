package com.gildedtros.gildedtros_spring_app.internal.unit;

import com.gildedtros.gildedtros_spring_app.internal.dto.ItemRequest;
import com.gildedtros.gildedtros_spring_app.internal.exception.InvalidItemNameException;
import com.gildedtros.gildedtros_spring_app.internal.model.Item;
import com.gildedtros.gildedtros_spring_app.internal.model.ItemType;
import com.gildedtros.gildedtros_spring_app.internal.processingStrategy.BackstagePassesItemProcessingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BackstagePassesItemProcessingStrategyTests {
    static final String VALID_BACKSTAGE_PASSES_NAME = "Backstage passes for Item";
    static final String INVALID_BACKSTAGE_PASSES_NAME = "Invalid Backstage Passes Name";

    private BackstagePassesItemProcessingStrategy backstagePassesItemProcessingStrategy;

    @BeforeEach
    public void setUp() {
        backstagePassesItemProcessingStrategy = new BackstagePassesItemProcessingStrategy();
    }

    private void validateBackstagePassesItemAfterTest(Item backstagePassesItem, int expectedSellIn, int expectedQuality) {
        assertEquals(VALID_BACKSTAGE_PASSES_NAME, backstagePassesItem.getName());
        assertEquals(expectedSellIn, backstagePassesItem.getSellIn());
        assertEquals(expectedQuality, backstagePassesItem.getQuality());
        assertEquals(ItemType.BACKSTAGE_PASS, backstagePassesItem.getItemType());
    }

    @Test
    public void validBackstagePassesItemIsCreated() {
        ItemRequest backstagePassesItemRequest = new ItemRequest(VALID_BACKSTAGE_PASSES_NAME, 5, 50, ItemType.BACKSTAGE_PASS);

        Item backstagePassesItem = backstagePassesItemProcessingStrategy.validateOnCreate(backstagePassesItemRequest);

        validateBackstagePassesItemAfterTest(backstagePassesItem, 5, 50);
    }

    @Test
    public void backstagePassesItemWithoutValidNamePrefixWillThrowError() throws InvalidItemNameException {
        ItemRequest backstagePassesItemRequest = new ItemRequest(INVALID_BACKSTAGE_PASSES_NAME, 5, 50, ItemType.BACKSTAGE_PASS);

        try {
            backstagePassesItemProcessingStrategy.validateOnCreate(backstagePassesItemRequest);
            fail();
        } catch (InvalidItemNameException e) {
            assertEquals("Could not find valid Backstage Passes Prefix in given Item Name", e.getMessage());
        }
    }

    @Test
    public void backstagePassesItemQualityIncreasesBy1IfSellInDateIsGreaterThan10() {
        Item backstagePassesItem = new Item(VALID_BACKSTAGE_PASSES_NAME, 11, 0, ItemType.BACKSTAGE_PASS);

        backstagePassesItemProcessingStrategy.updateQuality(backstagePassesItem);

        validateBackstagePassesItemAfterTest(backstagePassesItem, 10, 1);
    }

    @Test
    public void backstagePassesItemQualityIncreasesBy2IfSellInDateIsBetween5And10() {
        Item backstagePassesItem = new Item(VALID_BACKSTAGE_PASSES_NAME, 6, 0, ItemType.BACKSTAGE_PASS);

        backstagePassesItemProcessingStrategy.updateQuality(backstagePassesItem);

        validateBackstagePassesItemAfterTest(backstagePassesItem, 5, 2);
    }

    @Test
    public void backstagePassesItemQualityIncreasesBy3IfSellInDateIsSmallerThan5() {
        Item backstagePassesItem = new Item(VALID_BACKSTAGE_PASSES_NAME, 4, 0, ItemType.BACKSTAGE_PASS);

        backstagePassesItemProcessingStrategy.updateQuality(backstagePassesItem);

        validateBackstagePassesItemAfterTest(backstagePassesItem, 3, 3);
    }

    @Test
    public void backstagePassesItemQualityDropsTo0IfSellInDateIs0() {
        Item backstagePassesItem = new Item(VALID_BACKSTAGE_PASSES_NAME, 0, 50, ItemType.BACKSTAGE_PASS);

        backstagePassesItemProcessingStrategy.updateQuality(backstagePassesItem);

        validateBackstagePassesItemAfterTest(backstagePassesItem, -1, 0);    }

    @Test
    public void backstagePassesItemQualityIsCappedTo50() {
        Item backstagePassesItem = new Item(VALID_BACKSTAGE_PASSES_NAME, 5, 100, ItemType.BACKSTAGE_PASS);

        backstagePassesItemProcessingStrategy.clampQuality(backstagePassesItem);

        validateBackstagePassesItemAfterTest(backstagePassesItem, 5, 50);
    }

    @Test
    public void backstagePassesItemQualityIsFlooredTo0() {
        Item backstagePassesItem = new Item(VALID_BACKSTAGE_PASSES_NAME, 5, -100, ItemType.BACKSTAGE_PASS);

        backstagePassesItemProcessingStrategy.clampQuality(backstagePassesItem);

        validateBackstagePassesItemAfterTest(backstagePassesItem, 5, 0);
    }
}
