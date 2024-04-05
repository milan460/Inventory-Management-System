package com.skillstorm.project1.inventorymanagementsystem.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skillstorm.project1.inventorymanagementsystem.models.Warehouse;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer>{
    //repository for CRUD function for Warehouse table

    //custom query to retrieve warehouses by admin id
    @Query("select w from Warehouse w where w.admin.id = :admin_id")
    Optional<List<Warehouse>> findWarehousesByAdminId (@Param("admin_id") int adminId);
}


