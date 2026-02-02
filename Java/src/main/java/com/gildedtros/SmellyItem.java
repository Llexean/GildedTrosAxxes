package com.gildedtros;

public class SmellyItem extends CommonItem {
    static final String[] SMELLY_NAME_LIST = {"Duplicate Code", "Long Methods", "Ugly Variable Names"};


    public SmellyItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
        this.clampQuality();
    }

    @Override
    public void updateQuality() {
        this.quality -= this.sellIn-- <= 0 ? 4 : 2;
        this.clampQuality();
    }
}
