package com.emredemir.calculator.service;

import com.emredemir.calculator.generator.InvoiceGenerator;
import com.emredemir.calculator.model.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class InvoiceServiceImpl implements InvoiceService {
	private final InvoiceGenerator invoiceGenerator;

	public InvoiceServiceImpl(InvoiceGenerator invoiceGenerator) {
		this.invoiceGenerator = invoiceGenerator;
	}

	@Override
	public void generate(Customer customer) {
		invoiceGenerator.generate(customer);
	}

	@Override
	public void print(Customer customer) {
		ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

		System.out.println("--- Showing invoice started ---");
		
		try {
			String json = mapper.writeValueAsString(customer);
			System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		System.out.println("--- Showing invoice ended ---");
	}
}
