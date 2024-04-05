package com.skillstorm.project1.inventorymanagementsystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.project1.inventorymanagementsystem.models.Admin;
import com.skillstorm.project1.inventorymanagementsystem.repositories.AdminRepository;

@Service
public class AdminService {
    
    @Autowired
    AdminRepository adminRepository;

    //retrieving admin by the id
    public Admin findAdminById(int id){

        Optional<Admin> admin = adminRepository.findById(id);

        if(admin.isPresent()){
            return admin.get();
        }

        return null;
    }

    //retrieve all admins from the database
    public List<Admin> getAllAdmins(){
        return adminRepository.findAll();
    }

    //create admin and inserting into database
    public Admin createAdmin(Admin admin){

        return adminRepository.save(admin);

    }

    /*
     * update admin
     * can update any field
     */
    public Admin updateAdmin(Admin admin){
        return adminRepository.save(admin);
    }

    //delete an admin from the database
    public void deleteAdmin(int id){

        Admin admin = findAdminById(id);
        adminRepository.delete(admin);
    }
}
