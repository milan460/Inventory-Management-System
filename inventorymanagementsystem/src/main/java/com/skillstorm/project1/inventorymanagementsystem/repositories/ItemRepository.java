package com.skillstorm.project1.inventorymanagementsystem.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skillstorm.project1.inventorymanagementsystem.models.Item;



@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{
        //repository for CRUD Function for Items table

        //custom query to retrieve items by warehouse id
        @Query("select i from Item i where i.warehouse.id = :warehouse_id")
        Optional<List<Item>> findItemsByWarehouseId (@Param("warehouse_id") int warehouseId);

        //custom query to retrieve items by admin id
        @Query("select i from Item i join i.warehouse w where w.admin.id = :admin_id")
        Optional<List<Item>> findItemsByAdminId (@Param("admin_id") int adminId);
}
