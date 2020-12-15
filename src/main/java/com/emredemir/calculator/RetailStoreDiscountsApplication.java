package com.emredemir.calculator;

import com.emredemir.calculator.model.Customer;
import com.emredemir.calculator.model.Cart;
import com.emredemir.calculator.model.CustomerDetails;
import com.emredemir.calculator.exception.InventoryException;
import com.emredemir.calculator.service.CartServiceImpl;
import com.emredemir.calculator.service.InvoiceService;
import com.emredemir.calculator.service.WarehouseDBService;

public class RetailStoreDiscountsApplication {

    private final WarehouseDBService warehouseDBService;
    private final CartServiceImpl cartService;
    private final InvoiceService invoiceService;

    public RetailStoreDiscountsApplication(WarehouseDBService warehouseDBService,
                               CartServiceImpl cartService,
                               InvoiceService invoiceService) {
        this.warehouseDBService = warehouseDBService;
        this.cartService = cartService;
        this.invoiceService = invoiceService;
    }

    public double shop(CustomerDetails customerDetails) throws InventoryException {
        Cart cart = cartService.generateCart();
        Customer customer = new Customer(customerDetails, cart);

        cartService.addProductToCart(3, cart); //Basically I added 3 items for each product. This can be changed as desired. 

        warehouseDBService.updateInventory(cartService.getAllProducts(cart));
        invoiceService.generate(customer);
        invoiceService.print(customer);
        
        return customer.getInvoice().getAmount();
    }


}
