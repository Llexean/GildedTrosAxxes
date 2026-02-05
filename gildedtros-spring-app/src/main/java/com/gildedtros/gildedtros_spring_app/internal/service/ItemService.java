package com.gildedtros.gildedtros_spring_app.internal.service;

import com.gildedtros.gildedtros_spring_app.internal.dto.ItemRequest;
import com.gildedtros.gildedtros_spring_app.internal.model.Item;
import com.gildedtros.gildedtros_spring_app.internal.repository.ItemRepository;
import com.gildedtros.gildedtros_spring_app.internal.processingStrategyFactory.ItemProcessingStrategyFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemProcessingStrategyFactory itemProcessingStrategyFactory;

    public ItemService(ItemRepository itemRepository, ItemProcessingStrategyFactory itemProcessingStrategyFactory) {
        this.itemRepository = itemRepository;
        this.itemProcessingStrategyFactory = itemProcessingStrategyFactory;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item createItem(ItemRequest itemRequest) throws IllegalArgumentException {
        try {
            return itemRepository.save(itemProcessingStrategyFactory.get(itemRequest.itemType()).validateOnCreate(itemRequest));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void updateAllItems() {
        List<Item> items = getAllItems();
        items.forEach((item) -> itemProcessingStrategyFactory.get(item.getItemType()).updateQuality(item));
        itemRepository.saveAll(items);
    }
}
