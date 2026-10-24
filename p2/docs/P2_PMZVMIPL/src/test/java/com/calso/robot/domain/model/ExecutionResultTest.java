package com.calso.robot.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class ExecutionResultTest {
	@Test
	void testGivenExecutedTrueAndEmptyMapWhenCreatingExecutionResultThenStoresValuesCorrectly() {
		ExecutionResult result = new ExecutionResult(true, Map.of());
		assertTrue(result.executed());
		assertTrue(result.missingProducts().isEmpty());
	}
	
	@Test
	void testGivenExecutedFalseAndMissingProductsWhenCreatingExecutionResultThenStoresValuesCorrectly() {
		Map<CleaningProduct, Integer> missing = Map.of(CleaningProduct.SOAP, 2);
		ExecutionResult result = new ExecutionResult(false, missing);
		assertFalse(result.executed());
		assertEquals(2, result.missingProducts().get(CleaningProduct.SOAP));
	}
	
	@Test
	void testGivenMissingProductsWhenTryingToModifyReturnedMapThenThrowsUnsupportedOperationException() {
		ExecutionResult result = new ExecutionResult(false, Map.of(CleaningProduct.WATER, 1));
		assertThrows(UnsupportedOperationException.class, 
					() -> result.missingProducts().put(CleaningProduct.WATER, 2));
	}
	
	@Test
	void testGivenExecutedTrueWhenCreatingExecutionResultThenMissingProductsIsEmpty() {
		ExecutionResult result = new ExecutionResult(true, Map.of());
		assertTrue(result.missingProducts().isEmpty());
	} 
}
