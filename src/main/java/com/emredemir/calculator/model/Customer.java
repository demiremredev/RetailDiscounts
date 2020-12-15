package com.emredemir.calculator.model;

public final class Customer {
    private final CustomerDetails customerDetails;
    private final Cart cart;
    private Invoice invoice;

    public Customer(CustomerDetails customerDetails, Cart cart) {
        this.customerDetails = customerDetails;
        this.cart = cart;
        this.invoice = null;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public Cart getCart() {
        return cart;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Invoice getInvoice() {
        return invoice;
    }
}
