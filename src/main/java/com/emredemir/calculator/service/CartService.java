package com.emredemir.calculator.service;

import com.emredemir.calculator.model.Product;
import com.emredemir.calculator.model.Cart;
import com.emredemir.calculator.exception.InventoryException;

import java.util.Set;
import java.util.UUID;

public interface CartService {
	boolean addProduct(Product product, Cart cart);

	boolean addProductBatch(Set<Product> products, Cart cart);

	Product getProduct(UUID pid, Cart cart);

	Set<Product> getAllProducts(Cart cart);

	boolean addProductToCart(int loadQuantity, Cart cart) throws InventoryException;

	Cart generateCart();
}
