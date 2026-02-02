package com.gildedtros;

public class BackstagePassItem extends CommonItem{
    static final String BACKSTAGE_PASSES_ITEM_PREFIX = "Backstage passes for ";

    public BackstagePassItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        if (this.sellIn <= 0) {
            this.quality = COMMON_ITEM_MIN_QUALITY;
        } else if (this.sellIn <= 5) {
            this.quality += 3;
        } else if (this .sellIn <= 10) {
            this.quality += 2;
        } else {
            this.quality++;
        }

        sellIn--;
        clampQuality();
    }
}
