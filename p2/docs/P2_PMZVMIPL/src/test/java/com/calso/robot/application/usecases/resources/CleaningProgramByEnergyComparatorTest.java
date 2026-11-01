package com.calso.robot.application.usecases.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.calso.robot.domain.model.CleaningProgram;
import com.calso.robot.domain.valueobjects.Energy;

public class CleaningProgramByEnergyComparatorTest {
	private final CleaningProgramByEnergyComparator comparator = new CleaningProgramByEnergyComparator();
	
	@Test
	void testGivenProgramsWithEnergies10And20WhenComparingThenOrderAscendingByEnergy() {
		CleaningProgram first = new CleaningProgram("P1", "Program1", Energy.of(10), Map.of());
		CleaningProgram second = new CleaningProgram("P2", "Program2", Energy.of(20), Map.of());
		assertTrue(comparator.compare(first, second) < 0);
	}
	
	@Test
	void testGivenProgramsWithSameEnergyAndIdsP1AndP2WhenComparingThenOrderById() {
		CleaningProgram first = new CleaningProgram("P1", "Program1", Energy.of(10), Map.of());
		CleaningProgram second = new CleaningProgram("P2", "Program2", Energy.of(10), Map.of());
		assertTrue(comparator.compare(first, second) < 0);
	}
	
	@Test
	void testGivenProgramsWithSameEnergyAndSameIdWhenComparingThenReturnsZero() {
		CleaningProgram first = new CleaningProgram("P1", "Program1", Energy.of(10), Map.of());
		CleaningProgram second = new CleaningProgram("P1", "Program2", Energy.of(10), Map.of());
		assertEquals(0, comparator.compare(first, second));
	}
}
