package com.gildedtros.gildedtros_spring_app.internal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "T_ITEM")
public class Item {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private String name;

    @Column(name = "SELL_IN")
    private int sellIn;

    private int quality;

    @Column(name = "ITEM_TYPE")
    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    public Item() {}

    public Item(String name, int sellIn, int quality, ItemType itemType) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.itemType = itemType;
    }

    public Long getItemId() { return itemId; }
    public String getName() {
        return name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public void setSellIn(int sellIn) {
        this.sellIn = sellIn;
    }

    public void decreaseSellIn() {
        this.sellIn--;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public void increaseQuality(int amount) {
        this.quality += amount;
    }

    public void decreaseQuality(int amount) {
        this.quality -= amount;
    }

    public ItemType getItemType() {
        return this.itemType;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}
