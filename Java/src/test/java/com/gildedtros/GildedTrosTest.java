package com.gildedtros;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedTrosTest {

    static final String COMMON_ITEM_NAME = "Common Item";
    static final String BACKSTAGE_PASS_ITEM_NAME = "Backstage Pass Item";

    @Test
    void commonItemQualityDecrease() {
        CommonItem[] items = new CommonItem[] { new CommonItem(COMMON_ITEM_NAME, 5, 50) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(COMMON_ITEM_NAME, app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(49, app.items[0].quality);
    }


    @Test
    void commonItemQualityDecreaseTwiceIfSellDatePassed() {
        CommonItem[] items = new CommonItem[] { new CommonItem(COMMON_ITEM_NAME, 0, 50) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(COMMON_ITEM_NAME, app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(48, app.items[0].quality);
    }

    @Test
    void commonItemQualityIsNeverOver50() {
        CommonItem[] items = new CommonItem[] { new CommonItem(COMMON_ITEM_NAME, 5, 100) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(COMMON_ITEM_NAME, app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(49, app.items[0].quality);
    }

    @Test
    void commonItemQualityIsNeverNegative() {
        CommonItem[] items = new CommonItem[] { new CommonItem(COMMON_ITEM_NAME, 0, -100) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(COMMON_ITEM_NAME, app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(CommonItem.COMMON_ITEM_MIN_QUALITY, app.items[0].quality);
    }

    @Test
    void goodWineQualityIncreasesTheOlderItGets() {
        GoodWineItem[] items = new GoodWineItem[] { new GoodWineItem(GoodWineItem.GOOD_WINE_ITEM_NAME, 5, 0) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(GoodWineItem.GOOD_WINE_ITEM_NAME, items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
    }

    @Test
    void goodWineItemQualityIsNeverOver50() {
        CommonItem[] items = new CommonItem[] { new GoodWineItem(GoodWineItem.GOOD_WINE_ITEM_NAME, 5, 100) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(GoodWineItem.GOOD_WINE_ITEM_NAME, app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(CommonItem.COMMON_ITEM_MAX_QUALITY, app.items[0].quality);
    }

    @Test
    void goodWineItemQualityIsNeverNegative() {
        CommonItem[] items = new CommonItem[] { new GoodWineItem(GoodWineItem.GOOD_WINE_ITEM_NAME, 5, -100) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(GoodWineItem.GOOD_WINE_ITEM_NAME, app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
    }

    @Test
    void legendaryItemSellInIsAlways0() {
        CommonItem[] items = new CommonItem[] { new LegendaryItem(LegendaryItem.LEGENDARY_ITEM_NAME_LIST[0], 5, LegendaryItem.LEGENDARY_ITEM_QUALITY) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(LegendaryItem.LEGENDARY_ITEM_NAME_LIST[0], app.items[0].name);
        assertEquals(LegendaryItem.LEGENDARY_ITEM_SELLINDATE, app.items[0].sellIn);
        assertEquals(LegendaryItem.LEGENDARY_ITEM_QUALITY, app.items[0].quality);
    }

    @Test
    void legendaryItemQualityIsAlways80() {
        CommonItem[] items = new CommonItem[] { new LegendaryItem(LegendaryItem.LEGENDARY_ITEM_NAME_LIST[0], LegendaryItem.LEGENDARY_ITEM_SELLINDATE, 100) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(LegendaryItem.LEGENDARY_ITEM_NAME_LIST[0], app.items[0].name);
        assertEquals(LegendaryItem.LEGENDARY_ITEM_SELLINDATE, app.items[0].sellIn);
        assertEquals(LegendaryItem.LEGENDARY_ITEM_QUALITY, app.items[0].quality);
    }

    @Test
    void backStagePassesQualityIncreaseBy2WhenSellInDateIs10OrLess() {
        CommonItem[] items = new CommonItem[] { new BackstagePassItem(BackstagePassItem.BACKSTAGE_PASSES_ITEM_PREFIX + BACKSTAGE_PASS_ITEM_NAME, 10, CommonItem.COMMON_ITEM_MIN_QUALITY) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(BackstagePassItem.BACKSTAGE_PASSES_ITEM_PREFIX + BACKSTAGE_PASS_ITEM_NAME, app.items[0].name);
        assertEquals(9, app.items[0].sellIn);
        assertEquals(2, app.items[0].quality);
    }

    @Test
    void backStagePassesQualityIncreasesBy3WhenSellDateIs5OrLess() {
        CommonItem[] items = new CommonItem[] { new BackstagePassItem(BackstagePassItem.BACKSTAGE_PASSES_ITEM_PREFIX + BACKSTAGE_PASS_ITEM_NAME, 5, CommonItem.COMMON_ITEM_MIN_QUALITY) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(BackstagePassItem.BACKSTAGE_PASSES_ITEM_PREFIX + BACKSTAGE_PASS_ITEM_NAME, app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(3, app.items[0].quality);
    }

    @Test
    void backStagePassesQualityResetsTo0WhenSellDateIsEqualToOrLessThan0() {
        CommonItem[] items = new CommonItem[] { new BackstagePassItem(BackstagePassItem.BACKSTAGE_PASSES_ITEM_PREFIX + BACKSTAGE_PASS_ITEM_NAME, 0, 100) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(BackstagePassItem.BACKSTAGE_PASSES_ITEM_PREFIX + BACKSTAGE_PASS_ITEM_NAME, app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(CommonItem.COMMON_ITEM_MIN_QUALITY, app.items[0].quality);
    }

    @Test
    void backStagePassesQualityIsNeverOver50() {
        CommonItem[] items = new CommonItem[] { new BackstagePassItem(BackstagePassItem.BACKSTAGE_PASSES_ITEM_PREFIX + BACKSTAGE_PASS_ITEM_NAME, 5, 100) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(BackstagePassItem.BACKSTAGE_PASSES_ITEM_PREFIX + BACKSTAGE_PASS_ITEM_NAME, app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(CommonItem.COMMON_ITEM_MAX_QUALITY, app.items[0].quality);
    }

    @Test
    void backStagePassesQualityIsNeverNegative() {
        CommonItem[] items = new CommonItem[] { new BackstagePassItem(BackstagePassItem.BACKSTAGE_PASSES_ITEM_PREFIX + BACKSTAGE_PASS_ITEM_NAME, 5, -100) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(BackstagePassItem.BACKSTAGE_PASSES_ITEM_PREFIX + BACKSTAGE_PASS_ITEM_NAME, app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(3, app.items[0].quality);
    }

    @Test
    void smellyItemQualityDecrease() {
        CommonItem[] items = new CommonItem[] { new SmellyItem(SmellyItem.SMELLY_NAME_LIST[0], 5, 50) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(SmellyItem.SMELLY_NAME_LIST[0], app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(48, app.items[0].quality);
    }

    @Test
    void smellyItemQualityDecreaseTwiceIfSellDatePassed() {
        CommonItem[] items = new CommonItem[] { new SmellyItem(SmellyItem.SMELLY_NAME_LIST[0], 0, 50) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(SmellyItem.SMELLY_NAME_LIST[0], app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(46, app.items[0].quality);
    }

    @Test
    void smellyItemQualityIsNeverOver50() {
        CommonItem[] items = new CommonItem[] { new SmellyItem(SmellyItem.SMELLY_NAME_LIST[0], 5, 100) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(SmellyItem.SMELLY_NAME_LIST[0], app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(48, app.items[0].quality);
    }

    @Test
    void smellyItemQualityIsNeverNegative() {
        CommonItem[] items = new CommonItem[] { new SmellyItem(SmellyItem.SMELLY_NAME_LIST[0], 0, -100) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(SmellyItem.SMELLY_NAME_LIST[0], app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(CommonItem.COMMON_ITEM_MIN_QUALITY, app.items[0].quality);
    }
}
