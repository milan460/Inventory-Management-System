package com.skillstorm.project1.inventorymanagementsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.skillstorm.project1.inventorymanagementsystem.models.Warehouse;
import com.skillstorm.project1.inventorymanagementsystem.services.WarehouseService;

import org.springframework.web.bind.annotation.PutMapping;



@RestController
@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("/warehouses")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    //Get request to retrieve a list of all warehouses in the database
    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.getAllWarehouses();
        return new ResponseEntity<List<Warehouse>>(warehouses, HttpStatus.OK);
    }

    //Get request to retrieve the warehouse by the id
    @GetMapping("/warehouse/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable int id) {
        Warehouse warehouse = warehouseService.findWarehouseById(id);
        return new ResponseEntity<Warehouse>(warehouse, HttpStatus.OK);
    }

    //Get request to retrieve a list of all warehouses associated to an admin. Using admin id to find the warehouses
    @GetMapping("/admin/{id}")
    public ResponseEntity<List<Warehouse>> getWarehousesByAdminId(@PathVariable int id) {

        List<Warehouse> warehouses = warehouseService.findWarehousesByAdminId(id);
        return new ResponseEntity<List<Warehouse>>(warehouses, HttpStatus.OK);
    }

    //Post request to create an new warehouse 
    @PostMapping("/createWarehouse")
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        
        Warehouse newWarehouse = warehouseService.createWarehouse(warehouse);
    
        return new ResponseEntity<Warehouse>(newWarehouse, HttpStatus.CREATED);
    }


    //Put request to update an existing warehouse
    @PutMapping("/updateWarehouse")
    public ResponseEntity<Warehouse> updateWarehouse(@RequestBody Warehouse warehouse) {
        
        Warehouse updatedWarehouse = warehouseService.updateWarehouse(warehouse);
        
        return new ResponseEntity<Warehouse>(updatedWarehouse, HttpStatus.CREATED);
    }

    //Delete request to delete an existing warehouse
    @DeleteMapping("/deleteWarehouse/{id}")
    public ResponseEntity<Warehouse> deleteWarehouse(@PathVariable int id) {

        warehouseService.deleteWarehouse(id);
        
        return ResponseEntity.noContent().build();
    } 
}
