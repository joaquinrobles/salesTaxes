package com.lastminute.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PurchaseItemFactoryTest {

	PurchaseItemFactory purchaseItemFactory;
	
	@BeforeEach
	void initialise() {
		purchaseItemFactory = new PurchaseItemFactory();
	}

	@Test
	void should_create_with_cantity_from_string() {
		assertEquals(1,purchaseItemFactory
				.createPurchaseItemFromString("1 object at 11")
				.getCantity());
	}

	@Test
	void should_create_with_importe_from_string() {
		assertTrue(purchaseItemFactory
				.createPurchaseItemFromString("1 imported object at 11")
				.getImported());
	}

	@Test
	void should_create_with_product_from_string() {
		assertEquals("object with color",purchaseItemFactory
				.createPurchaseItemFromString("1 object with color at 11")
				.getProduct());
	}

	@Test
	void should_create_with_price_from_string() {
		assertEquals(0,purchaseItemFactory
				.createPurchaseItemFromString("1 object with color at 11.54")
				.getPrice().compareTo(BigDecimal.valueOf(11.54)));
	}
	
	@Test
	void should_create_with_imported_in_middle_of_product_from_string() {
		assertEquals("object of parts with color",purchaseItemFactory
				.createPurchaseItemFromString("1 object of imported parts with color at 11")
				.getProduct());
	}

	@Test
	void should_create_with_zero_taxes_from_string() {
		assertEquals(0,purchaseItemFactory
				.createPurchaseItemFromString("1 object of imported parts with color at 11")
				.getTaxes().compareTo(BigDecimal.ZERO));
	}
}
