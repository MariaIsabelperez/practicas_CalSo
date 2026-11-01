package com.calso.robot.domain.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.EnumMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.calso.robot.domain.valueobjects.Energy;

public class CleaningProgramTest {
	@Test
	void testGivenNullIdWhenCreatingProgramThenThrowsIllegalArgumentException() {
		Energy energy = Energy.of(10);
		Map<CleaningProduct, Integer> requirements = Map.of();
		assertThrows(
				IllegalArgumentException.class, 
					() -> new CleaningProgram(
							null,
							"Program",
							energy,
							requirements));
	}
	
	@Test
	void testGivenEmptyIdWhenCreatingProgramThenThrowsIllegalArgumentException() {
		Energy energy = Energy.of(10);
		Map<CleaningProduct, Integer> requirements = Map.of();
		assertThrows(
				IllegalArgumentException.class, 
				() -> new CleaningProgram(
						"",
						"Program",
						energy,
						requirements));
	}
	
	@Test
	void testGivenBlankIdWhenCreatingProgramThenThrowsIllegalArgumentException() {
		Energy energy = Energy.of(10);
		Map<CleaningProduct, Integer> requirements = Map.of();
		assertThrows(
				IllegalArgumentException.class, 
				() -> new CleaningProgram(
						" ",
						"Program",
						energy,
						requirements));
	}
	
	@Test
	void testGivenNullNameWhenCreatingProgramThenThrowsIllegalArgumentException() {
		Energy energy = Energy.of(10);
		Map<CleaningProduct, Integer> requirements = Map.of();
		assertThrows(
				IllegalArgumentException.class, 
				() -> new CleaningProgram(
						"P1",
						null,
						energy,
						requirements));
	}
	
	@Test
	void testGivenEmptyNameWhenCreatingProgramThenThrowsIllegalArgumentException() {
		Energy energy = Energy.of(10);
		Map<CleaningProduct, Integer> requirements = Map.of();
		assertThrows(
				IllegalArgumentException.class, 
				() -> new CleaningProgram(
						"P1",
						"",
						energy,
						requirements));
	}
	
	@Test
	void testGivenBlankNameWhenCreatingProgramThenThrowsIllegalArgumentException() {
		Energy energy = Energy.of(10);
		assertThrows(
				IllegalArgumentException.class, 
				() -> new CleaningProgram(
						"P1",
						" ",
						energy,
						Map.of()));
	}
	
	@Test
	void testGivenNullEnergyWhenCreatingProgramThenThrowsNullPointerException() {
		assertThrows(NullPointerException.class, 
				() -> new CleaningProgram("P1", "Program", null, Map.of()));
	}
	
	@Test
	void testGivenNullRequirementsWhenCreatingProgramThenThrowsNullPointerException() {
		Energy energy = Energy.of(10);
		assertThrows(
				NullPointerException.class, 
				() -> new CleaningProgram(
						"P1",
						"Program",
						energy,
						null));
	}
	
	@Test
	void testGivenNegativeRequiredQuantityWhenCreatingProgramThenThrowsIllegalArgumentException() {
		Energy energy = Energy.of(10);
		Map<CleaningProduct, Integer> requirements = new EnumMap<>(CleaningProduct.class);
	    requirements.put(CleaningProduct.WATER, -1);
		assertThrows(
				IllegalArgumentException.class, 
				() -> new CleaningProgram(
						"P1",
						"Program",
						energy,
						requirements));
	}
	
	@Test
	void testGivenProgramWithSameIdWhenComparingThenTheyAreEqual() {
		CleaningProgram p1 = new CleaningProgram("P1", "ProgramA", Energy.of(10), Map.of());
		CleaningProgram p2 = new CleaningProgram("P1", "ProgramB", Energy.of(20), Map.of(CleaningProduct.WATER, 2));
		assertEquals(p1, p2);
	}
	
	@Test
	void testGivenProgramWithDifferentIdsWhenComparingThenTheyAreNotEqual() {
		CleaningProgram p1 = new CleaningProgram("P1", "Program", Energy.of(10), Map.of());
		CleaningProgram p2 = new CleaningProgram("P2", "Program", Energy.of(10), Map.of());
		assertNotEquals(p1, p2);
	}
	
	@Test
	void testGivenProgramWithSameIdsWhenComputingHashCodeThenHashCodesMatch() {
		CleaningProgram p1 = new CleaningProgram("P1", "ProgramA", Energy.of(10), Map.of());
		CleaningProgram p2 = new CleaningProgram("P1", "ProgramB", Energy.of(20), Map.of());
		assertEquals(p1.hashCode(), p2.hashCode());
	}
	
	@Test
	void testGivenRequirementsMapWhenModifiedAfterConstructionThenProgramRequirementsRemainUnchanged() {
	    Map<CleaningProduct, Integer> requirements =
	    		new EnumMap<>(CleaningProduct.class);
	    requirements.put(CleaningProduct.WATER, 5);
	    CleaningProgram program = new CleaningProgram("P1", "Program", Energy.of(10), requirements);
	    requirements.put(CleaningProduct.WATER, 50);
	    assertEquals(5, program.getRequirements().get(CleaningProduct.WATER));
	}
	
	@Test
	void testGivenRequirementsWhenTryingToModifyReturnedMapThenThrowsUnsupportedOperationException() {
	    CleaningProgram program = new CleaningProgram("P1", "Program", Energy.of(10), Map.of());
	    assertThrows(UnsupportedOperationException.class,
	    		() -> program.getRequirements().put(CleaningProduct.WATER, 1));
	}
	
	@Test
	void testGivenEmptyRequirementsMapWhenCreatingProgramThenCreatesProgram() {
	    assertDoesNotThrow(() -> new CleaningProgram("P1", "Program", Energy.of(10), Map.of()));
	}
	
	@Test
	void testGivenZeroRequiredQuantityWhenCreatingProgramThenCreatesProgram() {
	    assertDoesNotThrow(() -> new CleaningProgram("P1", "Program", Energy.of(10), Map.of(CleaningProduct.WATER, 0)));
	}
}
