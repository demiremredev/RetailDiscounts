package com.emredemir.calculator.generator;

import com.emredemir.calculator.model.Invoice;
import com.emredemir.calculator.model.Product;
import com.emredemir.calculator.model.Customer;
import com.emredemir.calculator.model.CustomerDetails;
import com.emredemir.calculator.constant.PercentageDiscounts;
import com.emredemir.calculator.constant.ProductTypes;
import com.emredemir.calculator.constant.UserTypes;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class InvoiceGenerator {
	public void generate(Customer customer) {
		CustomerDetails customerDetails = customer.getCustomerDetails();
		double userDiscountPercentage = 0.0d;
		UserTypes userType = customerDetails.getUserType();
		LocalDateTime createdAt = customerDetails.getCreatedAt();

		if (userType == UserTypes.AFFILIATE) {
			userDiscountPercentage = PercentageDiscounts.AFFILIATE.getDiscount();
		} else if (userType == UserTypes.CUSTOMER) {
			long gap = ChronoUnit.YEARS.between(createdAt, LocalDateTime.now());

			if (gap >= 2) {
				userDiscountPercentage = PercentageDiscounts.CUSTOMER.getDiscount();
			}
		} else if (userType == UserTypes.EMPLOYEE) {
			userDiscountPercentage = PercentageDiscounts.EMPLOYEE.getDiscount();
		}

		double totalDiscountApplied = 0.0d;
		double totalBill = 0.0d;

		for (Product p : customer.getCart().getProducts().getProducts().values()) {
			double discountForCurrentProduct = getDiscount(p, userDiscountPercentage);
			totalBill += p.getQuantity() * p.getUnitPrice() - discountForCurrentProduct;
			totalDiscountApplied += discountForCurrentProduct;
		}

		/*
		 * First of all, I applied percentage based discounts before 5$ discounts. I
		 * mean calculation is like that:
		 * 
		 * For example I am an AFFILIATE, so percentage should be %10.
		 * 
		 * The products for example:
		 * 
		 * sum = (2 x Product1 x 0,9) + (2 x Product2 x 0,9) + (2 x Product3 x 0,9) + (2
		 * x GroceryProduct)
		 * 
		 * as you can see percentage based discount not applied to grocery product.
		 * 
		 * after that I check the sum value. For example sum is 430$ then it should be
		 * applied 20$ additional discount.
		 * 
		 * Here is the formula:
		 * 
		 * Math.floor(sum / 100) => 4
		 */

		double discountFor100Based = Math.floor(totalBill / 100) * 5;
		
		totalDiscountApplied += discountFor100Based;
		totalBill -= discountFor100Based;

		customer.setInvoice(new Invoice(UUID.randomUUID(), LocalDateTime.now(), totalBill, totalDiscountApplied));
	}

	private double getDiscount(Product product, double discountPercentage) {
		if (product.getType().equals(ProductTypes.GROCERY)) {
			return 0;
		}

		return product.getQuantity() * product.getUnitPrice() * discountPercentage;
	}
}
