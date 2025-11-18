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
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals("Good Wine")
                    && !items[i].name.equals("Backstage passes for Re:Factor")
                    && !items[i].name.equals("Backstage passes for HAXX"))
            {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals("B-DAWG Keychain")) {
                        items[i].quality = items[i].quality - 1;
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals("Backstage passes for Re:Factor") || items[i].name.equals("Backstage passes for HAXX") ) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("B-DAWG Keychain")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Good Wine")) {
                    if (!items[i].name.equals("Backstage passes for Re:Factor") && !items[i].name.equals("Backstage passes for HAXX")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("B-DAWG Keychain")) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }

    public void updateQualityV2() {
        for (int i = 0; i < items.length; i++) {
            if(Arrays.asList(LEGENDARY_NAME_LIST).contains(items[i].name)) {
                updateLegendaryItem(i);
                continue;
            }

            if (items[i].name.contains("Backstage passes")) {
                updateBackstagePassesItem(i);
            } else {
                updateCommonItem(i);
            }

            items[i].sellIn--;
        }
    }

    private void updateLegendaryItem(int itemIdx) {
        items[itemIdx].quality = LEGENDARY_ITEMS_QUALITY;
        items[itemIdx].sellIn = 0;
    }

    private void updateBackstagePassesItem(int itemIdx) {
        if (items[itemIdx].sellIn == 0) {
            items[itemIdx].quality = COMMON_ITEMS_MIN_QUALITY;
        } else if (items[itemIdx].sellIn <= 5) {
            increaseQuality(itemIdx, 3);
        } else if (items[itemIdx].sellIn <= 10) {
            increaseQuality(itemIdx, 2);
        } else {
            increaseQuality(itemIdx, 1);
        }
    }

    private void updateCommonItem(int itemIdx) {
        if (items[itemIdx].name.equals("Good Wine")) {
            increaseQuality(itemIdx, 1);
        } else if (Arrays.asList(SMELLY_NAME_LIST).contains(items[itemIdx].name)) {
            decreaseQuality(itemIdx, 2);
        } else {
            decreaseQuality(itemIdx, 1);
        }
    }

    private void increaseQuality(int itemIdx, int amount) {
        items[itemIdx].quality += amount;
        clampMaxQuality(itemIdx);
    }

    private void decreaseQuality(int itemIdx, int amount) {
        final int sellDateMultiplier = items[itemIdx].sellIn <= 0 ? 2 : 1;
        items[itemIdx].quality -= amount * sellDateMultiplier;
        clampMinQuality(itemIdx);
    }

    private void clampMaxQuality(int itemIdx) {
        if (items[itemIdx].quality > COMMON_ITEMS_MAX_QUALITY) {
            items[itemIdx].quality = COMMON_ITEMS_MAX_QUALITY;
        }
    }

    private void clampMinQuality(int itemIdx) {
        if (items[itemIdx].quality < COMMON_ITEMS_MIN_QUALITY) {
            items[itemIdx].quality = COMMON_ITEMS_MIN_QUALITY;
        }
    }
}