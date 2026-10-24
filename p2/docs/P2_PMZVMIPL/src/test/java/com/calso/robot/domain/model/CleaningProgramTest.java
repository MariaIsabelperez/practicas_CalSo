package com.calso.robot.domain.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.calso.robot.domain.valueobjects.Energy;

public class CleaningProgramTest {
	@Test
	void testGivenNullIdWhenCreatingProgramThenTrowsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, 
					() -> new CleaningProgram(null, "Program", Energy.of(10), Map.of()));
	}
	
	@Test
	void testGivenEmptyIdWhenCreatingProgramThenTrowsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, 
				() -> new CleaningProgram("", "Program", Energy.of(10), Map.of()));
	}
	
	@Test
	void testGivenBlankIdWhenCreatingProgramThenTrowsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, 
				() -> new CleaningProgram(" ", "Program", Energy.of(10), Map.of()));
	}
	
	@Test
	void testGivenNullNameWhenCreatingProgramThenTrowsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, 
				() -> new CleaningProgram("P1", null, Energy.of(10), Map.of()));
	}
	
	@Test
	void testGivenEmptyNameWhenCreatingProgramThenTrowsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, 
				() -> new CleaningProgram("P1", "", Energy.of(10), Map.of()));
	}
	
	@Test
	void testGivenBlankNameWhenCreatingProgramThenTrowsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, 
				() -> new CleaningProgram("P1", " ", Energy.of(10), Map.of()));
	}
	
	@Test
	void testGivenNullEnergyWhenCreatingProgramThenTrowsNullPointerException() {
		assertThrows(NullPointerException.class, 
				() -> new CleaningProgram("P1", "Program", null, Map.of()));
	}
	
	@Test
	void testGivenNullRequirementsWhenCreatingProgramThenTrowsNullPointerException() {
		assertThrows(NullPointerException.class, 
				() -> new CleaningProgram("P1", "Program", Energy.of(10), null));
	}
	
	@Test
	void testGivenNegativeRequiredQuantityWhenCreatingProgramThenTrowsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, 
				() -> new CleaningProgram("P1", "Program", Energy.of(10), Map.of(CleaningProduct.WATER, -1)));
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
	    Map<CleaningProduct, Integer> requirements = new HashMap<>();
	    requirements.put(CleaningProduct.WATER, 5);
	    CleaningProgram program = new CleaningProgram("P1", "Program", Energy.of(10), requirements);
	    requirements.put(CleaningProduct.WATER, 50);
	    assertEquals(5, program.getRequirements().get(CleaningProduct.WATER));
	}
	
	@Test
	void testGivenRequirementsWhenTryingToModifyReturnedMapThenThrowsUnsupportedOperationException() {
	    CleaningProgram program = new CleaningProgram("P1", "Program", Energy.of(10), Map.of());
	    assertThrows(UnsupportedOperationException.class, () -> program.getRequirements().put(CleaningProduct.WATER, 1));
	}
	
	@Test
	void testGivenEmptyRequirementsMapWhenCreatingProgramThenCreatesProgram() {
	    assertDoesNotThrow(() -> new CleaningProgram("P1", "Program", Energy.of(10), Map.of()));
	}
	
	@Test
	void testGivenZeroRequiredQuantityWhenCreatingProgramThenCreatesProgram() {
	    assertDoesNotThrow(() -> new CleaningProgram("P1", "Program", Energy.of(10), Map.of(CleaningProduct.WATER, 1)));
	}
}
