package com.emredemir.calculator.service;

import com.emredemir.calculator.model.Product;

import java.util.Set;
import java.util.UUID;

public interface WarehouseDBService {
    Set<Product> getInventory();

    boolean checkProduct(UUID pid, int quantity);

    void updateInventory(Set<Product> cartProducts);
}
