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
                items[i].quality = LEGENDARY_ITEMS_QUALITY;
                items[i].sellIn = 0;
                continue;
            }

            if (items[i].name.contains("Backstage passes")) {
                if (items[i].sellIn == 0) {
                    items[i].quality = COMMON_ITEMS_MIN_QUALITY;
                } else if (items[i].sellIn <= 5) {
                    increaseQuality(i, 3);
                } else if (items[i].sellIn <= 10) {
                    increaseQuality(i, 2);
                } else {
                    increaseQuality(i, 1);
                }
            } else {
                if (items[i].name.equals("Good Wine")) {
                    increaseQuality(i, 1);
                } else if (Arrays.asList(SMELLY_NAME_LIST).contains(items[i].name)) {
                    decreaseQuality(i, 2);
                } else {
                    decreaseQuality(i, 1);
                }
            }

            items[i].sellIn--;
        }
    }

    private void increaseQuality(int itemIdx, int amount) {
        items[itemIdx].quality += amount;

        if (items[itemIdx].quality > COMMON_ITEMS_MAX_QUALITY) {
            items[itemIdx].quality = COMMON_ITEMS_MAX_QUALITY;
        }
    }

    private void decreaseQuality(int itemIdx, int amount) {
        items[itemIdx].quality -= amount * (items[itemIdx].sellIn <= 0 ? 2 : 1);

        if (items[itemIdx].quality < COMMON_ITEMS_MIN_QUALITY) {
            items[itemIdx].quality = COMMON_ITEMS_MIN_QUALITY;
        }
    }
}