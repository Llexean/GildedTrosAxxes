package com.gildedtros;

public class TexttestFixture {
    public static void main(String[] args) {
        System.out.println("AXXES CODE KATA - GILDED TROS");

        CommonItem[] items = new CommonItem[] {
                new CommonItem("Ring of Cleansening Code", 10, 20),
                new GoodWineItem("Good Wine", 2, 0),
                new CommonItem("Elixir of the SOLID", 5, 7),
                new LegendaryItem("B-DAWG Keychain", 0, 80),
                new LegendaryItem("B-DAWG Keychain", -1, 80),
                new BackstagePassItem("Backstage passes for Re:Factor", 15, 20),
                new BackstagePassItem("Backstage passes for Re:Factor", 10, 49),
                new BackstagePassItem("Backstage passes for HAXX", 5, 49),
                // these smelly items do not work properly yet
                new SmellyItem("Duplicate Code", 3, 6),
                new SmellyItem("Long Methods", 3, 6),
                new SmellyItem("Ugly Variable Names", 3, 6) };

        GildedTros app = new GildedTros(items);

        int days = 2;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }

        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println();
            app.updateQuality();
        }
    }

}
