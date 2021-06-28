package com.lastminute;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.lastminute.formater.DecimalFormater;
import com.lastminute.valueObject.PurchaseItem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReceiptTaxCalculator {
	private final TaxCalculator taxCalculator;
	
	public List<String> printReceiptDetailsWithTaxes(List<String> purchaseItems) {
		List<String> output = new ArrayList<>();
		BigDecimal taxes = BigDecimal.ZERO;
		BigDecimal totalWithTaxes = BigDecimal.ZERO;
		
		List<PurchaseItem> itemsWithTax = purchaseItems.stream()
		.map(taxCalculator::processPurchaseItem)
		.collect(Collectors.toList());
		
		for(PurchaseItem item : itemsWithTax) {
			output.add(item.toString());
			taxes = taxes.add(item.getTaxes());
			totalWithTaxes = totalWithTaxes.add(item.getTotalWithTaxes());
		}
		
		output.add("Sales Taxes: " + DecimalFormater.getBigDecimalWithFormat(taxes));
		output.add("Total: " + DecimalFormater.getBigDecimalWithFormat(totalWithTaxes));
		
		return output;
	}

}
