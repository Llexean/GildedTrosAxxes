package com.gildedtros.gildedtros_spring_app.internal.controller;

import com.gildedtros.gildedtros_spring_app.internal.dto.ItemRequest;
import com.gildedtros.gildedtros_spring_app.internal.model.Item;
import com.gildedtros.gildedtros_spring_app.internal.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(name = "/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody ItemRequest itemRequest) {
        try {
            return new ResponseEntity<>(itemService.createItem(itemRequest), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect name for given Item Type");
        }
    }

    @PutMapping
    public void updateAllItems() {
        itemService.updateAllItems();
    }
}
