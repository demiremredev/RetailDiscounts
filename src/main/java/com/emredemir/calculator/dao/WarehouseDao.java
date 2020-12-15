package com.emredemir.calculator.dao;

import com.emredemir.calculator.model.Product;

import java.util.Set;
import java.util.UUID;

public interface WarehouseDao {
    boolean updateInventory(Product product);

    boolean updateInventoryBatch(Set<Product> products);

    Product getProduct(UUID pid);

    Set<Product> getAllProducts();
}
