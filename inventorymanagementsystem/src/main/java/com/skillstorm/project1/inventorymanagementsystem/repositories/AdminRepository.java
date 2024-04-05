package com.skillstorm.project1.inventorymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skillstorm.project1.inventorymanagementsystem.models.Admin;

import jakarta.transaction.Transactional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{
    //repository for CRUD function for the Admin table
    
    // //updating the admin
    // @Query("update Admins ad set a.username = :new_username where id = :admin_id")
    // @Modifying
    // @Transactional
    // public int updateAdminUsername(@Param("admin_id") int id, @Param("new_username") String newUsername);


    // @Query("update Admins ad set a.first_name = :new_first_name where id = :admin_id")
    // @Modifying
    // @Transactional
    // public int updateAdminFirstName(@Param("admin_id") int id, @Param("new_first_name") String newFirstName);

    // @Query("update Admins ad set ad.last_name = :new_last_name where ad.id = :admin_id")
    // @Modifying
    // @Transactional
    // public int updateAdminLastName(@Param("admin_id") int id, @Param("new_last_name") String newLastName);



}
