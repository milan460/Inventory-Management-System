package com.skillstorm.project1.inventorymanagementsystem.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project1.inventorymanagementsystem.models.Item;

import com.skillstorm.project1.inventorymanagementsystem.services.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("/items")
public class ItemController {
    
   @Autowired
   ItemService itemService;


   //Get Request to retrieve all items
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
    }


   //Get Request to retrieve the item by the id
   @GetMapping("/item/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable int id) {
        Item item = itemService.findItemById(id);
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

   //Get request to retrieve all items by the warehouse id
    @GetMapping("/warehouse/{id}")
    public ResponseEntity<List<Item>> getItemsByWarehouseId(@PathVariable int id) {
        
        List<Item> items = itemService.findItemsByWarehouseId(id);
        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
    }
    
   //Get request to retrieve all items by the Admin id
   @GetMapping("/admin/{id}")
   public ResponseEntity<List<Item>> getItemsByAdminId(@PathVariable int id) {
       
        List<Item> items = itemService.findItemsByAdminId(id);

        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
   }
   

   //Post request to create an item
    @PostMapping("/createItem")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        
        Item newItem = itemService.createItem(item);
    
        return new ResponseEntity<Item>(newItem, HttpStatus.CREATED);
    }

   //Put request to update an item
    @PutMapping("/updateItem")
    public ResponseEntity<Item> updateItem(@RequestBody Item item) {
        
        Item updatedItem = itemService.updateItem(item);
        
        return new ResponseEntity<Item>(updatedItem, HttpStatus.CREATED);
    }

   //Delete request to delete an item
    @DeleteMapping("/deleteItem/{id}")
    public ResponseEntity<Item> deleteItem(@PathVariable int id) {

//        Item item = itemService.findItemById(id);

        itemService.deleteItem(id);
        
        return ResponseEntity.noContent().build();
    }
}
