package com.calso.robot.domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class EnergyTest {
	@Test
	void testGivenEnergy10WhenCreatingEnergyTheStoresValue10() {
		Energy energy = Energy.of(10);
		assertEquals(10, energy.getValue());
	}
	
	@Test
	void testGivenEnergy0WhenCreatingEnergyThenThrowsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> Energy.of(0));
	}
	
	@Test
	void testGivenEnergyMinus1WhenCreatingEnergyThenThrowsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> Energy.of(-1));
	}
	
	@Test
	void testGivenTwoEnergyObjectsWithValue10WhenComparingThenTheyAreEqual() {
		Energy first = Energy.of(10);
		Energy second = Energy.of(10);
		assertEquals(first, second);
	}
	
	@Test
	void testGivenTwoEqualEnergyObjectsWhenComputingHashCodeThenHashCodesMatch() {
		Energy first = Energy.of(10);
		Energy second = Energy.of(10);
		assertEquals(first.hashCode(), second.hashCode());
	}
}
