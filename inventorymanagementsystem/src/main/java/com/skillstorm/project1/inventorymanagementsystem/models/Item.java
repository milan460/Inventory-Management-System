package com.skillstorm.project1.inventorymanagementsystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "Items")
public class Item {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column(name = "item_type")
    private String itemType;

    @Column
    @Min(1)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    public Item() {
    }

    public Item(int id, String name, String itemType, int quantity, Warehouse warehouse) {
        this.id = id;
        this.name = name;
        this.itemType = itemType;
        this.quantity = quantity;
        this.warehouse = warehouse;
    }

    public Item(String name, String itemType, int quantity, Warehouse warehouse) {
        this.name = name;
        this.itemType = itemType;
        this.quantity = quantity;
        this.warehouse = warehouse;
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

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((itemType == null) ? 0 : itemType.hashCode());
        result = prime * result + quantity;
        result = prime * result + ((warehouse == null) ? 0 : warehouse.hashCode());
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
        Item other = (Item) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (itemType == null) {
            if (other.itemType != null)
                return false;
        } else if (!itemType.equals(other.itemType))
            return false;
        if (quantity != other.quantity)
            return false;
        if (warehouse == null) {
            if (other.warehouse != null)
                return false;
        } else if (!warehouse.equals(other.warehouse))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", name=" + name + ", itemType=" + itemType + ", quantity=" + quantity
                + ", warehouse=" + warehouse + "]";
    }

    
    
}
