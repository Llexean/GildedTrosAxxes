package com.gildedtros;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedTrosTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void commonItemQualityDecrease() {
        Item[] items = new Item[] { new Item("Common Item Name", 5, 50) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(49, app.items[0].quality);
    }


    @Test
    void commonItemQualityDecreaseTwiceIfSellDatePassed() {
        Item[] items = new Item[] { new Item("Common Item Name", 0, 50) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(48, app.items[0].quality);
    }

    @Test
    void qualityIsNeverNegative() {
        Item[] items = new Item[] { new Item("Item", 0, 0) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void commonItemQualityNeverOver50() {
        Item[] items = new Item[] { new Item("Common Item Name", 0, 80) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void goodWineQualityIncreasesTheOlderItGets() {
        Item[] items = new Item[] { new Item("Good Wine", 5, 0) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
    }

    @Test
    void legendaryItemNeverHasToBeSoldOrDecreasesInQuality() {
        Item[] items = new Item[] { new Item("B-DAWG Keychain", 0, 80) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(0, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);
    }

    @Test
    void backStagePassesQualityIncreaseBy2WhenSellInDateIs10OrLess() {
        Item[] items = new Item[] { new Item("Backstage passes for Re:Factor", 10, 0) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(2, app.items[0].quality);
    }

    @Test
    void backStagePassesQualityIncreasesBy3WhenSellDateIs5OrLess() {
        Item[] items = new Item[] { new Item("Backstage passes for Re:Factor", 5, 0) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(3, app.items[0].quality);
    }

    @Test
    void backStagePassesQualityResetsTo0WhenSellDateIsEqualToOrLessThan0() {
        Item[] items = new Item[] { new Item("Backstage passes for Re:Factor", 0, 50) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void smellyItemQualityDecrease() {
        Item[] items = new Item[] { new Item("Duplicate Code", 5, 50) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(48, app.items[0].quality);
    }

    @Test
    void smellyItemQualityDecreaseTwiceIfSellDatePassed() {
        Item[] items = new Item[] { new Item("Duplicate Code", 0, 50) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(46, app.items[0].quality);
    }
}
