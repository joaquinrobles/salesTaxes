package com.lastminute;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lastminute.factory.PurchaseItemFactory;
import com.lastminute.valueObject.PurchaseItem;

@ExtendWith(MockitoExtension.class)
public class TaxCalculatorTest {
	
	@Mock
	PurchaseItemFactory purchaseItemFactory;
	
	@Mock
	CategoryExempt categoryExempt;

	TaxCalculator taxCalculator;
	
	@BeforeEach
	void initialise() {
		taxCalculator = new TaxCalculator(purchaseItemFactory, categoryExempt);
	}
	
	@Test
	void should_create_purchaseItem_from_string(){
		String itemAsString = "1 object at 11";
		
		PurchaseItem purchaseItem = PurchaseItem.builder()
				.price(BigDecimal.valueOf(11))
				.imported(false)
				.taxes(BigDecimal.ZERO).build();
		
		when(purchaseItemFactory.createPurchaseItemFromString(itemAsString)).thenReturn(purchaseItem);

		taxCalculator.processPurchaseItem(itemAsString);
		
		verify(purchaseItemFactory).createPurchaseItemFromString(itemAsString);
	}
	
	@Test
	void should_apply_imported_taxed_from_string(){
		String itemAsString = "1 object imported at 100";
		
		PurchaseItem purchaseItem = PurchaseItem.builder()
				.price(BigDecimal.valueOf(100))
				.imported(true)
				.product("object")
				.taxes(BigDecimal.ZERO).build();
		
		
		when(categoryExempt.isExempt("object")).thenReturn(true);
		
		when(purchaseItemFactory.createPurchaseItemFromString(itemAsString)).thenReturn(purchaseItem);
		
		assertEquals(0,
				BigDecimal.valueOf(5).compareTo(taxCalculator.processPurchaseItem(itemAsString).getTaxes()));
	}
	
	@Test
	void should_apply_tax_when_no_category_exempt_from_string(){
		String itemAsString = "1 object imported at 100";
		
		PurchaseItem purchaseItem = PurchaseItem.builder()
				.price(BigDecimal.valueOf(100))
				.imported(true)
				.product("object")
				.taxes(BigDecimal.ZERO).build();
		
		when(purchaseItemFactory.createPurchaseItemFromString(itemAsString)).thenReturn(purchaseItem);
		
		when(categoryExempt.isExempt("object")).thenReturn(false);
		
		assertEquals(0,
				BigDecimal.valueOf(15).compareTo(taxCalculator.processPurchaseItem(itemAsString).getTaxes()));
	}

}
