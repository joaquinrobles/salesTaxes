package com.lastminute;

import java.math.BigDecimal;

import com.lastminute.factory.PurchaseItemFactory;
import com.lastminute.valueObject.PurchaseItem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TaxCalculator {

	final PurchaseItemFactory purchaseItemFactory;
	final CategoryExempt categoryExempt;
	
	final static BigDecimal IMPORTED_TAX = BigDecimal.valueOf(0.05);
	final static BigDecimal REGULAR_TAX = BigDecimal.valueOf(0.10);
	
	public PurchaseItem processPurchaseItem(String itemAsString) {
		PurchaseItem purchaseItem = purchaseItemFactory.createPurchaseItemFromString(itemAsString);
		
		if(purchaseItem.getImported())
			purchaseItem.addTaxes(IMPORTED_TAX);
		
		if(!categoryExempt.isExempt(purchaseItem.getProduct()))
			purchaseItem.addTaxes(REGULAR_TAX);
		
		return purchaseItem;
	}

}
