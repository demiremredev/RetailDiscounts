package com.emredemir.calculator.dao;

import com.emredemir.calculator.generator.InventoryGenerator;
import com.emredemir.calculator.model.Product;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

public class WarehouseDaoImpl implements WarehouseDao {

    InventoryGenerator inventoryGenerator;

    public WarehouseDaoImpl(InventoryGenerator inventoryGenerator) {
        this.inventoryGenerator = inventoryGenerator;
    }

    @Override
    public boolean updateInventory(Product product) {
        boolean result = false;
        LinkedHashMap<UUID, Product> products = inventoryGenerator.getProductInventory().get().getProducts();
        
        if (products.containsKey(product.getId())) {
        	products.put(product.getId(), product);
        	
            result = true;
        }
        
        return result;
    }

    @Override
    public boolean updateInventoryBatch(Set<Product> products) {
        boolean result = false;
        
        for (Product p : products) {
            updateInventory(p);
        }
        
        return result;
    }

    @Override
    public Product getProduct(UUID pid) {
        Product product = null;
        if (inventoryGenerator.getProductInventory().get().getProducts().containsKey(pid)) {
            product = inventoryGenerator.getProductInventory().get().getProducts().get(pid);
        }
        return product;
    }

    @Override
    public Set<Product> getAllProducts() {
        return new LinkedHashSet<>(inventoryGenerator.getProductInventory().get().getProducts().values());
    }
}
