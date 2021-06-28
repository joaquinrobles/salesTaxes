package com.lastminute;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CategoryExempt {
	public boolean isExempt(String product) {
		return product!=null &&
				(product.toLowerCase().contains("book") ||
				product.toLowerCase().contains("chocolate") ||
				product.toLowerCase().contains("pill"));
	}
}
