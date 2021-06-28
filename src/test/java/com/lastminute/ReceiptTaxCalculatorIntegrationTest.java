package com.lastminute;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lastminute.factory.PurchaseItemFactory;

public class ReceiptTaxCalculatorIntegrationTest {
	
	ReceiptTaxCalculator receiptTaxCalculator;
	TaxCalculator taxCalculator;
	PurchaseItemFactory purchaseItemFactory;
	CategoryExempt categoryExempt;
	
	@BeforeEach
	void initialise() {
		purchaseItemFactory = new PurchaseItemFactory();
		categoryExempt = new CategoryExempt();
		taxCalculator = new TaxCalculator(purchaseItemFactory, categoryExempt);
		receiptTaxCalculator = new ReceiptTaxCalculator(taxCalculator);
	}
	  
	@Test
	void should_print_output_with_taxes_with_no_imported_items(){
		List<String> items = new ArrayList<>();
		items.add("1 book at 12.49");
		items.add("1 music CD at 14.99");
		items.add("1 chocolate bar at 0.85");
		
		List<String> outputWithTaxes = receiptTaxCalculator.printReceiptDetailsWithTaxes(items);
		
		assertTrue(outputWithTaxes.contains("1 book: 12.49"));
		assertTrue(outputWithTaxes.contains("1 music CD: 16.49"));
		assertTrue(outputWithTaxes.contains("1 chocolate bar: 0.85"));
		assertTrue(outputWithTaxes.contains("Sales Taxes: 1.50"));
		assertTrue(outputWithTaxes.contains("Total: 29.83"));
	}

	@Test
	void should_print_output_with_taxes_with_imported_items(){
		List<String> items = new ArrayList<>();
		items.add("1 imported box of chocolates at 10.00");
		items.add("1 imported bottle of perfume at 47.50");
		
		List<String> outputWithTaxes = receiptTaxCalculator.printReceiptDetailsWithTaxes(items);
		
		assertTrue(outputWithTaxes.contains("1 imported box of chocolates: 10.50"));
		assertTrue(outputWithTaxes.contains("1 imported bottle of perfume: 54.65"));
		assertTrue(outputWithTaxes.contains("Sales Taxes: 7.65"));
		assertTrue(outputWithTaxes.contains("Total: 65.15"));
		
	}
	
	@Test
	void should_print_output_with_taxes_with_imported_and_no_imported_items(){
		List<String> items = new ArrayList<>();
		items.add("1 imported bottle of perfume at 27.99");
		items.add("1 bottle of perfume at 18.99");
		items.add("1 packet of headache pills at 9.75");
		items.add("1 box of imported chocolates at 11.25");

		List<String> outputWithTaxes = receiptTaxCalculator.printReceiptDetailsWithTaxes(items);
		
		assertTrue(outputWithTaxes.contains("1 imported bottle of perfume: 32.19"));
		assertTrue(outputWithTaxes.contains("1 bottle of perfume: 20.89"));
		assertTrue(outputWithTaxes.contains("1 packet of headache pills: 9.75"));
		assertTrue(outputWithTaxes.contains("1 imported box of chocolates: 11.85"));
		assertTrue(outputWithTaxes.contains("Sales Taxes: 6.70"));
		assertTrue(outputWithTaxes.contains("Total: 74.68"));
	}
}
