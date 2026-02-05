package com.gildedtros.gildedtros_spring_app.internal.integration;

import com.gildedtros.gildedtros_spring_app.internal.dto.ItemRequest;
import com.gildedtros.gildedtros_spring_app.internal.exception.InvalidItemNameException;
import com.gildedtros.gildedtros_spring_app.internal.model.Item;
import com.gildedtros.gildedtros_spring_app.internal.model.ItemType;
import com.gildedtros.gildedtros_spring_app.internal.repository.ItemRepository;
import com.gildedtros.gildedtros_spring_app.internal.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class ItemServiceIntegrationTests {
    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void testValidCreationOfItem() {
        ItemRequest request = new ItemRequest("Valid Item", 5, 50, ItemType.COMMON);

        Item response = itemService.createItem(request);

        assertEquals(request.name(), response.getName());
        assertEquals(request.sellIn(), response.getSellIn());
        assertEquals(request.quality(), response.getQuality());
        assertEquals(request.itemType(), response.getItemType());
    }

    @Test
    public void testInvalidNameThrowsErrorWhenCreating() {
        ItemRequest request = new ItemRequest("Invalid Item Name", 0, 80, ItemType.LEGENDARY);

        assertThrows(InvalidItemNameException.class, () -> itemService.createItem(request));
    }
}
