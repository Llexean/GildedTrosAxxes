package com.gildedtros;

public class LegendaryItem extends CommonItem {
    static final int LEGENDARY_ITEM_QUALITY = 80;
    static final int LEGENDARY_ITEM_SELLINDATE = 0;

    static final String[] LEGENDARY_ITEM_NAME_LIST = {"B-DAWG Keychain"};

    public LegendaryItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateQuality() {}

    @Override
    protected void clampQuality() {
        this.quality = LEGENDARY_ITEM_QUALITY;
        this.sellIn = LEGENDARY_ITEM_SELLINDATE;
    }
}
