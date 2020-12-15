package com.emredemir.calculator.service;

import com.emredemir.calculator.model.Product;
import com.emredemir.calculator.model.Cart;
import com.emredemir.calculator.exception.InventoryException;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

public class CartServiceImpl implements CartService {

    private final WarehouseDBService warehouseDBService;

    public CartServiceImpl(WarehouseDBService warehouseDBService) {
        this.warehouseDBService = warehouseDBService;
    }

    @Override
    public boolean addProduct(Product product, Cart cart) {
        boolean response = false;
        LinkedHashMap<UUID, Product> productsInCart = cart.getProducts().getProducts();
        if (!productsInCart.containsValue(product)) {
            productsInCart.put(product.getId(), product);
            response = true;
        }
        return response;
    }

    @Override
    public boolean addProductBatch(Set<Product> products, Cart cart) {
        boolean response = false;
        for(Product p : products){
            response = addProduct(p, cart);
            if(!response){
                break;
            }
        }
        return response;
    }

    @Override
    public Product getProduct(UUID pid, Cart cart) {
        return cart.getProducts().getProducts().get(pid);
    }

    @Override
    public Set<Product> getAllProducts(Cart cart) {
        return new LinkedHashSet<>(cart.getProducts().getProducts().values());
    }

    @Override
    public boolean addProductToCart(int loadQuantity, Cart cart) throws InventoryException {
    	Set<Product> cartProducts = new LinkedHashSet<>();
    	
        for (Product p : warehouseDBService.getInventory()) {
            Product clone = null;
            try {
                clone = (Product) p.clone();
            } catch (CloneNotSupportedException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            if(loadQuantity <= p.getQuantity()){
                clone.setQuantity(loadQuantity);
            }
            else {
                throw new InventoryException();
            }
            cartProducts.add(clone);
        }
        
        return addProductBatch(cartProducts, cart);
    }

    public Cart generateCart() {
        return new Cart();
    }
}
