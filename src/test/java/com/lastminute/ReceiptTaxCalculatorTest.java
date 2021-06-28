package com.lastminute;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lastminute.valueObject.PurchaseItem;

@ExtendWith(MockitoExtension.class)
public class ReceiptTaxCalculatorTest {

	@Mock
	TaxCalculator taxCalculator;
	
	ReceiptTaxCalculator receiptTaxCalculator;
	
	@BeforeEach
	void initialise() {
		receiptTaxCalculator = new ReceiptTaxCalculator(taxCalculator);
	}
	
	@Test
	void should_call_taxCalculator_for_every_item(){
		String purchaseItem = "1 object at 11";
		
		PurchaseItem purchaseItemWithTax = PurchaseItem.builder()
				.cantity(1)
				.product("object")
				.price(BigDecimal.valueOf(11))
				.imported(false)
				.taxes(BigDecimal.ZERO)
				.build();
		
		when(taxCalculator.processPurchaseItem(purchaseItem)).thenReturn(purchaseItemWithTax);

		List<String> detailsWithTaxes = receiptTaxCalculator.printReceiptDetailsWithTaxes(Arrays.asList(purchaseItem));
		
		assertTrue(detailsWithTaxes.contains(purchaseItemWithTax.toString()));
	}
	
	@Test
	void should_return_total_taxes() {
		String purchaseItemOne = "1 object1 at 11";
		String purchaseItemTwo = "1 object2 at 11";
		
		PurchaseItem purchaseItemWithTax = PurchaseItem.builder()
				.cantity(1)
				.product("object")
				.price(BigDecimal.valueOf(11))
				.imported(false)
				.taxes(BigDecimal.ONE)
				.build();
		
		when(taxCalculator.processPurchaseItem(purchaseItemOne)).thenReturn(purchaseItemWithTax);
		when(taxCalculator.processPurchaseItem(purchaseItemTwo)).thenReturn(purchaseItemWithTax);

		List<String> detailsWithTaxes = receiptTaxCalculator.printReceiptDetailsWithTaxes(Arrays.asList(purchaseItemOne, purchaseItemTwo));
		
		assertTrue(detailsWithTaxes.contains("Sales Taxes: 2.00"));
		
	}

	@Test
	void should_return_total() {
		String purchaseItemOne = "1 object1 at 11";
		String purchaseItemTwo = "1 object2 at 11";
		
		PurchaseItem purchaseItemWithTax = PurchaseItem.builder()
				.cantity(1)
				.product("object")
				.price(BigDecimal.valueOf(11))
				.imported(false)
				.taxes(BigDecimal.ONE)
				.build();
		
		when(taxCalculator.processPurchaseItem(purchaseItemOne)).thenReturn(purchaseItemWithTax);
		when(taxCalculator.processPurchaseItem(purchaseItemTwo)).thenReturn(purchaseItemWithTax);
		
		List<String> detailsWithTaxes = receiptTaxCalculator.printReceiptDetailsWithTaxes(Arrays.asList(purchaseItemOne, purchaseItemTwo));
		
		assertTrue(detailsWithTaxes.contains("Total: 24.00"));
		
	}
}
