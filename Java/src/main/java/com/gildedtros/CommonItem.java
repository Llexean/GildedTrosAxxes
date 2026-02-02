package com.gildedtros;

public class CommonItem extends Item {
    static final int COMMON_ITEM_MAX_QUALITY = 50;
    static final int COMMON_ITEM_MIN_QUALITY = 0;

    public CommonItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
        this.clampQuality();
    }

    public void updateQuality() {
        this.quality -= this.sellIn-- <= 0 ? 2 : 1;
        this.clampQuality();
    }

    protected void clampQuality() {
        if (this.quality > COMMON_ITEM_MAX_QUALITY) {
            this.quality = COMMON_ITEM_MAX_QUALITY;
        } else if (this.quality < COMMON_ITEM_MIN_QUALITY) {
            this.quality = COMMON_ITEM_MIN_QUALITY;
        }
    }
}
