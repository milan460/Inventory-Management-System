package com.skillstorm.project1.inventorymanagementsystem.controllers;

import java.awt.font.FontRenderContext;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project1.inventorymanagementsystem.models.Admin;
import com.skillstorm.project1.inventorymanagementsystem.services.AdminService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    AdminService adminService;


    //Get request to retrieve a list of all admins in the database
    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return new ResponseEntity<List<Admin>>(admins, HttpStatus.OK);
    }

    //Get request to retrieve the admin by the id
    @GetMapping("/admin/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable int id) {
        Admin admin = adminService.findAdminById(id);
        return new ResponseEntity<Admin>(admin, HttpStatus.OK);
    }
    
    //Post request to create an new admin 
    @PostMapping("/createAdmin")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        
        Admin newAdmin = adminService.createAdmin(admin);
        
        return new ResponseEntity<Admin>(newAdmin, HttpStatus.CREATED);
    }

    //PUT request to update an existing admin
    @PutMapping("/updateAdmin")
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) {
        
        Admin updatedAdmin = adminService.updateAdmin(admin);
        
        return new ResponseEntity<Admin>(admin, HttpStatus.CREATED);
    }

    //Delete request to delete an existing warehouse
    @DeleteMapping("/deleteAdmin/{id}")
    public ResponseEntity<Admin> deleteAdmin(@PathVariable int id) {
        adminService.deleteAdmin(id);

        return ResponseEntity.noContent().build();
    }
}
