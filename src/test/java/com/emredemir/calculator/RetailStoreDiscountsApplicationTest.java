package com.emredemir.calculator;

import com.emredemir.calculator.model.CustomerDetails;
import com.emredemir.calculator.constant.UserTypes;
import com.emredemir.calculator.dao.WarehouseDaoImpl;
import com.emredemir.calculator.exception.InventoryException;
import com.emredemir.calculator.generator.InvoiceGenerator;
import com.emredemir.calculator.generator.InventoryGenerator;
import com.emredemir.calculator.service.CartServiceImpl;
import com.emredemir.calculator.service.InvoiceService;
import com.emredemir.calculator.service.InvoiceServiceImpl;
import com.emredemir.calculator.service.WarehouseDBService;
import com.emredemir.calculator.service.WarehouseDBServiceImpl;

import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

import static junit.framework.TestCase.assertEquals;

public class RetailStoreDiscountsApplicationTest {

	private WarehouseDBService storeDBService;
	private CartServiceImpl cartService;
	private InvoiceService invoiceService;
	private DecimalFormat formatter;

	@Before
	public void init() throws Exception {
		storeDBService = new WarehouseDBServiceImpl(new WarehouseDaoImpl(InventoryGenerator.getStore()));
		cartService = new CartServiceImpl(storeDBService);
		invoiceService = new InvoiceServiceImpl(new InvoiceGenerator());
		formatter = new DecimalFormat("#.####");
	}

	@Test
	public void customerTypeAffiliateApplied10PercentDiscountAnd5DollarsExtra() throws InventoryException {
		RetailStoreDiscountsApplication shoppingApplication = new RetailStoreDiscountsApplication(storeDBService, cartService,
				invoiceService);
		LocalDateTime localDateTime = LocalDateTime.of(2020, 12, 15, 1, 1);
		CustomerDetails customerDetails = new CustomerDetails("Emre Demir", UserTypes.AFFILIATE, localDateTime,
				"emre.demir@softtech.com.tr", "+905300408160", "Cayyolu/Ankara 06790");
		assertEquals("customerTypeAffiliateApplied10PercentDiscountAnd5DollarsExtra", formatter.format(129.739d),
				formatter.format(shoppingApplication.shop(customerDetails)));
	}

	@Test
	public void customerTypeEmployeeApplied30PercentDiscount() throws InventoryException {
		RetailStoreDiscountsApplication shoppingApplication = new RetailStoreDiscountsApplication(storeDBService, cartService,
				invoiceService);
		LocalDateTime localDateTime = LocalDateTime.of(2020, 12, 15, 1, 1);
		CustomerDetails customerDetails = new CustomerDetails("Emre Demir", UserTypes.EMPLOYEE, localDateTime,
				"emre.demir@softtech.com.tr", "+905300408160", "Cayyolu/Ankara 06790");
		assertEquals("customerTypeEmployeeApplied30PercentDiscount", formatter.format(102.457d),
				formatter.format(shoppingApplication.shop(customerDetails)));
	}

	@Test
	public void customerTypeNewCustomerApplied5DollarsExtra() throws InventoryException {
		RetailStoreDiscountsApplication shoppingApplication = new RetailStoreDiscountsApplication(storeDBService, cartService,
				invoiceService);
		LocalDateTime localDateTime = LocalDateTime.of(2020, 12, 15, 1, 1);
		CustomerDetails customerDetails = new CustomerDetails("Emre Demir", UserTypes.CUSTOMER, localDateTime,
				"emre.demir@softtech.com.tr", "+905300408160", "Cayyolu/Ankara 06790");
		assertEquals("customerTypeNewCustomerApplied5DollarsExtra", formatter.format(143.38d),
				formatter.format(shoppingApplication.shop(customerDetails)));
	}

	@Test
	public void customerTypeOldCustomerApplied5PercentDiscountAnd5DollarsExtra() throws InventoryException {
		RetailStoreDiscountsApplication shoppingApplication = new RetailStoreDiscountsApplication(storeDBService, cartService,
				invoiceService);
		LocalDateTime localDateTime = LocalDateTime.of(2000, 1, 1, 1, 1);
		CustomerDetails customerDetails = new CustomerDetails("Emre Demir", UserTypes.CUSTOMER, localDateTime,
				"emre.demir@softtech.com.tr", "+905300408160", "Cayyolu/Ankara 06790");
		assertEquals("customerTypeAffiliateApplied5PercentDiscountAnd5DollarsExtra", formatter.format(136.5595d),
				formatter.format(shoppingApplication.shop(customerDetails)));
	}
}
