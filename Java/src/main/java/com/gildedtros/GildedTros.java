package com.gildedtros;

class GildedTros {
    CommonItem[] items;

    public GildedTros(CommonItem[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (CommonItem item : items) {
            item.updateQuality();
        }
    }
}