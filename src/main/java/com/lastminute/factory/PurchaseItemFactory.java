package com.lastminute.factory;

import java.math.BigDecimal;

import com.lastminute.valueObject.PurchaseItem;
import com.lastminute.valueObject.PurchaseItem.PurchaseItemBuilder;

public class PurchaseItemFactory {

	private static final String IMPORTED = "imported";
	private static final String SPACE = " ";
	private static final int POSITION_OF_CANTITY = 0;

	public PurchaseItem createPurchaseItemFromString(String itemAsString) {
		PurchaseItemBuilder purchaseItem = PurchaseItem.builder();
		
		processItemAsString(itemAsString, purchaseItem);
		
		return purchaseItem.build();
	}

	private void processItemAsString(String itemAsString, PurchaseItemBuilder purchaseItem) {
		itemAsString = processImported(itemAsString, purchaseItem);
		
		itemAsString = processCantity(itemAsString, purchaseItem);
		
		processProduct(itemAsString, purchaseItem);
		
		processPrice(itemAsString, purchaseItem);
		
		initialiseTaxes(purchaseItem);
	}

	private void initialiseTaxes(PurchaseItemBuilder purchaseItem) {
		purchaseItem.taxes(BigDecimal.ZERO);
	}

	private void processPrice(String itemAsString, PurchaseItemBuilder purchaseItem) {
		purchaseItem.price(new BigDecimal(itemAsString.substring(itemAsString.indexOf(" at")+4,itemAsString.length())));
	}

	private void processProduct(String itemAsString, PurchaseItemBuilder purchaseItem) {
		purchaseItem.product(itemAsString.substring(0,itemAsString.indexOf(" at")));
	}

	private String processCantity(String itemAsString, PurchaseItemBuilder purchaseItem) {
		String[] wordsInItem = itemAsString.split("\\s");
		
		purchaseItem.cantity(Integer.valueOf(wordsInItem[POSITION_OF_CANTITY]));
		
		itemAsString = itemAsString.replace(wordsInItem[POSITION_OF_CANTITY] + SPACE, "");
		
		return itemAsString;
	}

	private String processImported(String itemAsString, PurchaseItemBuilder purchaseItem) {
		if(itemAsString.contains(IMPORTED)) {
			itemAsString = itemAsString.replace(IMPORTED + SPACE, "");
			purchaseItem.imported(true);
		}else {
			purchaseItem.imported(false);
		}
		return itemAsString;
	}
}
