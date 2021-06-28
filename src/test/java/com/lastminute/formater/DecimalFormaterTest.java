package com.lastminute.formater;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class DecimalFormaterTest {
	@Test
	void should_format_with_two_digits_and_dot_separator() {
		assertEquals("5.35", DecimalFormater.getBigDecimalWithFormat(BigDecimal.valueOf(5.35)));
	}

}
