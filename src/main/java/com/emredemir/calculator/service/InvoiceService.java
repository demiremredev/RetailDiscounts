package com.emredemir.calculator.service;

import com.emredemir.calculator.model.Customer;

public interface InvoiceService {
    void generate(Customer customer);
    void print(Customer customer);
}
