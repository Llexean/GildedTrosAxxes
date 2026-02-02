package com.gildedtros;

public class GoodWineItem extends CommonItem {
    static final String GOOD_WINE_ITEM_NAME = "Good Wine";

    public GoodWineItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
        this.clampQuality();
    }

    @Override
    public void updateQuality() {
        this.quality++;
        this.sellIn--;
        this.clampQuality();
    }
}
