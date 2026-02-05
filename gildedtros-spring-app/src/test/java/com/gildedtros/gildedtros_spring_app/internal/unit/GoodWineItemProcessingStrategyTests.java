package com.gildedtros.gildedtros_spring_app.internal.unit;

import com.gildedtros.gildedtros_spring_app.internal.dto.ItemRequest;
import com.gildedtros.gildedtros_spring_app.internal.exception.InvalidItemNameException;
import com.gildedtros.gildedtros_spring_app.internal.model.Item;
import com.gildedtros.gildedtros_spring_app.internal.model.ItemType;
import com.gildedtros.gildedtros_spring_app.internal.processingStrategy.GoodWineItemProcessingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class GoodWineItemProcessingStrategyTests {
    static final String[] GOOD_WINE_NAME_LIST = {"Good Wine"};
    static final String INVALID_GOOD_WINE_NAME = "Invalid Good Item Name";

    private GoodWineItemProcessingStrategy goodWineItemProcessingStrategy;

    @BeforeEach
    public void setUp() {
        goodWineItemProcessingStrategy = new GoodWineItemProcessingStrategy();
    }

    private void validateGoodWineItemAfterTest(Item goodWineItem, int expectedSellIn, int expectedQuality) {
        assertEquals(GOOD_WINE_NAME_LIST[0], goodWineItem.getName());
        assertEquals(expectedSellIn, goodWineItem.getSellIn());
        assertEquals(expectedQuality, goodWineItem.getQuality());
        assertEquals(ItemType.GOOD_WINE, goodWineItem.getItemType());
    }

    @Test
    public void validGoodWineItemIsCreated() {
        ItemRequest goodWineItemRequest = new ItemRequest(GOOD_WINE_NAME_LIST[0], 5, 50, ItemType.GOOD_WINE);

        Item goodWineItem = goodWineItemProcessingStrategy.validateOnCreate(goodWineItemRequest);

        validateGoodWineItemAfterTest(goodWineItem, 5, 50);
    }

    @Test
    public void goodWineItemWithoutValidNameWillThrowError() throws InvalidItemNameException {
        ItemRequest goodWineItemRequest = new ItemRequest(INVALID_GOOD_WINE_NAME, 5, 50, ItemType.GOOD_WINE);

        try {
            goodWineItemProcessingStrategy.validateOnCreate(goodWineItemRequest);
            fail();
        } catch (InvalidItemNameException e) {
            assertEquals("Invalid Good Wine Item Name", e.getMessage());
        }
    }

    @Test
    public void goodWineItemQualityIncrease() {
        Item goodWineItem = new Item(GOOD_WINE_NAME_LIST[0], 5, 0, ItemType.GOOD_WINE);

        goodWineItemProcessingStrategy.updateQuality(goodWineItem);

        validateGoodWineItemAfterTest(goodWineItem, 4, 1);
    }

    @Test
    public void goodWineItemIncreasesEvenIfSellDatePassed() {
        Item goodWineItem = new Item(GOOD_WINE_NAME_LIST[0], 0, 0, ItemType.GOOD_WINE);

        goodWineItemProcessingStrategy.updateQuality(goodWineItem);

        validateGoodWineItemAfterTest(goodWineItem, -1, 1);
    }

    @Test
    public void goodWineItemQualityIsCappedTo50() {
        Item goodWineItem = new Item(GOOD_WINE_NAME_LIST[0], 5, 100, ItemType.GOOD_WINE);

        goodWineItemProcessingStrategy.clampQuality(goodWineItem);

        validateGoodWineItemAfterTest(goodWineItem, 5, 50);
    }

    @Test
    public void goodWineItemQualityIsFlooredTo0() {
        Item goodWineItem = new Item(GOOD_WINE_NAME_LIST[0], 0, -100, ItemType.GOOD_WINE);

        goodWineItemProcessingStrategy.clampQuality(goodWineItem);

        validateGoodWineItemAfterTest(goodWineItem, 0, 0);
    }
}
