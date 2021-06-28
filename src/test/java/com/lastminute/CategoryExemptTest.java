package com.lastminute;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CategoryExemptTest {
	
	CategoryExempt categoryExempt;
	
	@BeforeEach
	void initialise() {
		categoryExempt = new CategoryExempt();
	}
	
	@ParameterizedTest
	@MethodSource(value = "provideProducts")
	void should_detect_exempt_categories(String product, boolean isExempt) {
		assertEquals(isExempt, categoryExempt.isExempt(product));
	}
	
	public static Stream<Arguments> provideProducts(){
		return Stream.of(
				Arguments.of("book", true),
				Arguments.of("music CD", false),
				Arguments.of("chocolate bar", true),
				Arguments.of("box of chocolates", true),
				Arguments.of("bottle of perfume", false),
				Arguments.of("packet of headache pills", true)
				);
	}
}
