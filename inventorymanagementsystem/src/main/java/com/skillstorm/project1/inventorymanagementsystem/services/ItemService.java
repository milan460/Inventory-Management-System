package com.skillstorm.project1.inventorymanagementsystem.services;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import com.skillstorm.project1.inventorymanagementsystem.models.Warehouse;
import com.skillstorm.project1.inventorymanagementsystem.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.project1.inventorymanagementsystem.models.Item;
import com.skillstorm.project1.inventorymanagementsystem.repositories.ItemRepository;

@Service
public class ItemService {
    
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    WarehouseService warehouseService;


    //retrieve all items from database
    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    //retrieve an item by id
    public Item findItemById(int id){

        Optional<Item> item = itemRepository.findById(id);

        if(item.isPresent()){
            return item.get();
        }
        return null;
    }

    //retrieve items by warehouse id
    public List<Item> findItemsByWarehouseId(int id){

        Optional<List<Item>> items = itemRepository.findItemsByWarehouseId(id);

        if(items.isPresent()){
            return items.get();
        }

        return null;
    }
    
    //retrieve all items associated with admin
    public List<Item> findItemsByAdminId(int id){

        Optional<List<Item>> items = itemRepository.findItemsByAdminId(id);

        if(items.isPresent()){
            return items.get();
        }

        return null;
    }

    //create an item and inserting into database
    public Item createItem(Item item){

        return itemRepository.save(item);

    }

    //update an item from database
    public Item updateItem(Item item){

        return itemRepository.save(item);

    }

    //delete an item from database
    public void deleteItem(int id){

        itemRepository.deleteById(id);
    }
}
