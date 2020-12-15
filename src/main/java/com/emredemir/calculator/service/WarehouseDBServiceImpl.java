package com.emredemir.calculator.service;

import com.emredemir.calculator.model.Product;
import com.emredemir.calculator.dao.WarehouseDao;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

public class WarehouseDBServiceImpl implements WarehouseDBService {

	WarehouseDao warehouseDao;

	public WarehouseDBServiceImpl(WarehouseDao warehouseDao) {
		this.warehouseDao = warehouseDao;
	}

	@Override
	public Set<Product> getInventory() {
		return warehouseDao.getAllProducts();
	}

	@Override
	public boolean checkProduct(UUID pid, int quantity) {
		boolean result = false;
		Product product = warehouseDao.getProduct(pid);

		if (product != null && product.getQuantity() >= quantity) {
			result = true;
		}

		return result;
	}

	@Override
	public void updateInventory(Set<Product> cartProducts) {
		Set<Product> productsAdded = new LinkedHashSet<>(cartProducts.size());

		for (Product p : cartProducts) {
			Product storeProduct = warehouseDao.getProduct(p.getId());

			if (checkProduct(p.getId(), p.getQuantity())) {
				storeProduct.setQuantity(storeProduct.getQuantity() - p.getQuantity());
				productsAdded.add(storeProduct);
			}
		}

		if (!productsAdded.isEmpty()) {
			warehouseDao.updateInventoryBatch(productsAdded);
		}
	}
}
