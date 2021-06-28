package com.lastminute.valueObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class PurchaseItemTest {
	@Test
	void should_show_correct_not_imported_to_string() {
		PurchaseItem item = PurchaseItem.builder()
		.cantity(10)
		.price(BigDecimal.valueOf(55.55))
		.product("product")
		.imported(false)
		.taxes(BigDecimal.valueOf(11.11))
		.build();
		
		assertEquals("10 product: 66.66", item.toString());
	}

	@Test
	void should_show_correct_imported_to_string() {
		PurchaseItem item = PurchaseItem.builder()
				.cantity(10)
				.price(BigDecimal.valueOf(55.55))
				.product("product")
				.imported(true)
				.taxes(BigDecimal.valueOf(11.11))
				.build();
		
		assertEquals("10 imported product: 66.66", item.toString());
	}
	
	@Test
	void should_return_totalTaxes() {
		PurchaseItem item = PurchaseItem.builder()
				.cantity(10)
				.price(BigDecimal.valueOf(55.55))
				.product("product")
				.imported(true)
				.taxes(BigDecimal.valueOf(11.11))
				.build();
		
		assertEquals(0,BigDecimal.valueOf(111.1).compareTo(item.getTotalTaxes()));
	}

	@Test
	void should_return_total() {
		PurchaseItem item = PurchaseItem.builder()
				.cantity(10)
				.price(BigDecimal.valueOf(55.55))
				.product("product")
				.imported(true)
				.taxes(BigDecimal.valueOf(11.11))
				.build();
		
		assertEquals(0,BigDecimal.valueOf(666.6).compareTo(item.getTotalWithTaxes()));
	}

	@Test
	void should_add_taxes() {
		PurchaseItem item = PurchaseItem.builder()
				.cantity(2)
				.price(BigDecimal.valueOf(100))
				.product("product")
				.imported(true)
				.taxes(BigDecimal.ZERO)
				.build();
		
		item.addTaxes(BigDecimal.valueOf(0.05));
		
		assertEquals(0,BigDecimal.valueOf(10).compareTo(item.getTotalTaxes()));
	}

}
