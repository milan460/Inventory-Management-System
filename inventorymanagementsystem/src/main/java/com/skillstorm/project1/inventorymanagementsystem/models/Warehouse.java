package com.skillstorm.project1.inventorymanagementsystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "Warehouses")
public class Warehouse {
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String category;

    @Column(name = "max_capacity")
    @Min(1)
    private int maxCapacity;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    public Warehouse() {
    }

    public Warehouse(int id, String name, String address, String category, int maxCapacity, Admin admin) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.category = category;
        this.maxCapacity = maxCapacity;
        this.admin = admin;
    }

    public Warehouse(String name, String address, String category, int maxCapacity, Admin admin) {
        this.name = name;
        this.address = address;
        this.category = category;
        this.maxCapacity = maxCapacity;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + maxCapacity;
        result = prime * result + ((admin == null) ? 0 : admin.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Warehouse other = (Warehouse) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        if (maxCapacity != other.maxCapacity)
            return false;
        if (admin == null) {
            if (other.admin != null)
                return false;
        } else if (!admin.equals(other.admin))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Warehouse [id=" + id + ", name=" + name + ", address=" + address + ", category=" + category
                + ", maxCapacity=" + maxCapacity + ", admin=" + admin + "]";
    }
    
}
