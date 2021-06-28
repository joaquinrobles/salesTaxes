package com.lastminute.valueObject;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.lastminute.formater.DecimalFormater;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class PurchaseItem {
	final Integer cantity;
	final BigDecimal price;
	final String product;
	final Boolean imported;
	BigDecimal taxes;
	static final BigDecimal tick = BigDecimal.valueOf(0.05);
	
	public String toString() {
		return new StringBuilder()
				.append(cantity)
				.append(imported?" imported ":" ")
				.append(product)
				.append(": ")
				.append(DecimalFormater.getBigDecimalWithFormat(price.add(taxes)))
				.toString();
	}	
	
	public BigDecimal getTotalTaxes() {
		return taxes.multiply(BigDecimal.valueOf(cantity));
	}

	public BigDecimal getTotalWithTaxes() {
		return price.multiply(BigDecimal.valueOf(cantity)).add(getTotalTaxes());
	}

	public void addTaxes(BigDecimal tax) {
		taxes = taxes.add(roundWithTick(tax));
	}

	private BigDecimal roundWithTick(BigDecimal tax) {
		BigDecimal taxWithRound = price.multiply(tax).divide(tick,9,RoundingMode.CEILING);
		return taxWithRound.setScale(0, RoundingMode.CEILING).multiply(tick);
	}
}
