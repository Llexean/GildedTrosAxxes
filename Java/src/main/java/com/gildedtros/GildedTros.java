package com.gildedtros;

import java.util.Arrays;

class GildedTros {
    Item[] items;

    private static final String[] LEGENDARY_NAME_LIST = {"B-DAWG Keychain"};
    private static final String[] SMELLY_NAME_LIST = {"Duplicate Code", "Long Methods", "Ugly Variable Names"};

    private static final int COMMON_ITEMS_MAX_QUALITY = 50;
    private static final int COMMON_ITEMS_MIN_QUALITY = 0;

    private static final int LEGENDARY_ITEMS_QUALITY = 80;

    public GildedTros(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (Arrays.asList(LEGENDARY_NAME_LIST).contains(item.name)) {
                updateLegendaryItem(item);
                continue;
            }

            if (item.name.contains("Backstage passes")) {
                updateBackstagePassesItem(item);
            } else {
                updateCommonItem(item);
            }

            item.sellIn--;
        }
    }

    private void updateLegendaryItem(Item item) {
        item.quality = LEGENDARY_ITEMS_QUALITY;
        item.sellIn = 0;
    }

    private void updateBackstagePassesItem(Item item) {
        if (item.sellIn == 0) {
            item.quality = COMMON_ITEMS_MIN_QUALITY;
        } else if (item.sellIn <= 5) {
            increaseQuality(item, 3);
        } else if (item.sellIn <= 10) {
            increaseQuality(item, 2);
        } else {
            increaseQuality(item, 1);
        }
    }

    private void updateCommonItem(Item item) {
        if (item.name.equals("Good Wine")) {
            increaseQuality(item, 1);
        } else if (Arrays.asList(SMELLY_NAME_LIST).contains(item.name)) {
            decreaseQuality(item, 2);
        } else {
            decreaseQuality(item, 1);
        }
    }

    private void increaseQuality(Item item, int amount) {
        item.quality += amount;
        clampQuality(item);
    }

    private void decreaseQuality(Item item, int amount) {
        final int sellDateMultiplier = item.sellIn <= 0 ? 2 : 1;
        item.quality -= amount * sellDateMultiplier;
        clampQuality(item);
    }

    private void clampQuality(Item item) {
        if (item.quality > COMMON_ITEMS_MAX_QUALITY) {
            item.quality = COMMON_ITEMS_MAX_QUALITY;
        } else if (item.quality < COMMON_ITEMS_MIN_QUALITY) {
            item.quality = COMMON_ITEMS_MIN_QUALITY;
        }
    }
}