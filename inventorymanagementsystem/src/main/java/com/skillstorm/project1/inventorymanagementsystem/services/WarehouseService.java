package com.skillstorm.project1.inventorymanagementsystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.project1.inventorymanagementsystem.models.Warehouse;
import com.skillstorm.project1.inventorymanagementsystem.repositories.AdminRepository;
import com.skillstorm.project1.inventorymanagementsystem.repositories.WarehouseRepository;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;


    //retrieving warehouse by the warehouse id
    public Warehouse findWarehouseById(int id){

        Optional<Warehouse> warehouse = warehouseRepository.findById(id);

        if(warehouse.isPresent()){
            return warehouse.get();
        }

        return null;
    }

    //retrieve all warehouses from the database
    public List<Warehouse> getAllWarehouses(){
        return warehouseRepository.findAll();
    }

    //retrieve all warehouse by the admin id
    public List<Warehouse> findWarehousesByAdminId(int adminId){
        
        Optional<List<Warehouse>> warehouses = warehouseRepository.findWarehousesByAdminId(adminId);

        if(warehouses.isPresent()){
            return warehouses.get();
        }
        return null;
    }

    //create warehouse and inserting into database
    public Warehouse createWarehouse(Warehouse warehouse){

        return warehouseRepository.save(warehouse);

    }

    /*
     * update warehouse
     * can update any field
     */
    public Warehouse updateWarehouse(Warehouse warehouse){

        return warehouseRepository.save(warehouse);
    }

    //delete a warehouse from the database
    public void deleteWarehouse(int id){
        Warehouse warehouse = findWarehouseById(id);
        warehouseRepository.delete(warehouse);
    }
}
