package com.example.esnproject.controllers;

import com.example.esnproject.entities.Item;
import com.example.esnproject.repositories.ItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    private ItemRepository dao;

    public ItemController(ItemRepository dao) {
        this.dao = dao;
    }

    @PostMapping("api/saveItems")
    public Item save(@RequestBody Item item){
        return dao.save(item);
    }

    @GetMapping("api/items")
    public Iterable<Item> get() {
        return dao.findAll();
    }

    @GetMapping("api/sample")
    public String getSample() {
        return "sample";
    }

    @GetMapping("hello")
    public String sayHello(){
        return "Hello";
    }
}
