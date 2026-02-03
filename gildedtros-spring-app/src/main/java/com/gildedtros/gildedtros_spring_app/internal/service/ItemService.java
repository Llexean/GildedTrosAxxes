package com.gildedtros.gildedtros_spring_app.internal.service;

import com.gildedtros.gildedtros_spring_app.internal.dto.ItemRequest;
import com.gildedtros.gildedtros_spring_app.internal.model.Item;
import com.gildedtros.gildedtros_spring_app.internal.model.ItemType;
import com.gildedtros.gildedtros_spring_app.internal.repository.ItemRepository;
import com.gildedtros.gildedtros_spring_app.internal.updaterfactory.ItemUpdaterFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemUpdaterFactory itemUpdaterFactory;

    public ItemService(ItemRepository itemRepository, ItemUpdaterFactory itemUpdaterFactory) {
        this.itemRepository = itemRepository;
        this.itemUpdaterFactory = itemUpdaterFactory;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item createItem(ItemRequest itemRequest) {
        Item item = new Item(itemRequest.name(), itemRequest.sellIn(), itemRequest.quality(), itemRequest.itemType());
        return itemRepository.save(item);
    }

    public void updateAllItems() {
        List<Item> items = getAllItems();
        items.forEach((item) -> itemUpdaterFactory.get(item.getItemType()).updateQuality(item));
        itemRepository.saveAll(items);
    }
}
