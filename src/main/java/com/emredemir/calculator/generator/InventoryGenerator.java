package com.emredemir.calculator.generator;

import com.emredemir.calculator.model.Product;
import com.emredemir.calculator.model.Products;
import com.emredemir.calculator.constant.ProductTypes;

import java.util.UUID;

//This class used as a database manipulator
public final class InventoryGenerator {

    private final ThreadLocal<Products> productInventory = new ThreadLocal<>();

    private InventoryGenerator() {
        productInventory.set(generate());
    }

    private static final class StoreLoader {
        private static final InventoryGenerator singleton = new InventoryGenerator();
    }

    public static final InventoryGenerator getStore() {
        return StoreLoader.singleton;
    }

    public final ThreadLocal<Products> getProductInventory() {
        return productInventory;
    }

    private final Products generate() {
        Products inventory = new Products();

        Product p = new Product(UUID.randomUUID(), "PRODUCT_TYPE_1 name", 100, ProductTypes.PRODUCT_TYPE_1, 25.49d);
        inventory.getProducts().put(p.getId(), p);
        p = new Product(UUID.randomUUID(), "PRODUCT_TYPE_2 name", 200, ProductTypes.PRODUCT_TYPE_2, 15.99d);
        inventory.getProducts().put(p.getId(), p);
        p = new Product(UUID.randomUUID(), "PRODUCT_TYPE_GROCERY name", 500, ProductTypes.GROCERY, 3.99d);
        inventory.getProducts().put(p.getId(), p);
        p = new Product(UUID.randomUUID(), "PRODUCT_TYPE_4 name", 60, ProductTypes.PRODUCT_TYPE_4, 3.99d);
        inventory.getProducts().put(p.getId(), p);

        return inventory;
    }
}
