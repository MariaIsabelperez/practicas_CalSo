package es.um.calso.dojoreservation.infrastructure.e2e;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

// Incorporar las importaciones JUnit 5 y Selenium necesarias para los casos diseñados.
// La configuración del navegador y las esperas se heredan de BrowserE2ESupport.

class ReservationCostE2ETest extends BrowserE2ESupport {
	@Test
	void testGivenUnknownTeamWhenCalculatingCostThenDisplaysError() {
		open("/coste?teamId=EQ-999&dojoId=D-001&trainingDate=2026-10-21");
		assertEquals("Team does not exist", textOf("error"));
	}
	
	@Test
	void testGivenUnknownDojoWhenCalculatingCostThenDisplaysError() {
		open("/coste?teamId=EQ-001&dojoId=D-999&trainingDate=2026-10-21");
	    assertEquals("Dojo does not exist", textOf("error"));
	}
	
	@Test
	void testGivenTodayTrainingDateWhenCalculatingCostThenDisplaysValidationError() {
		calculateCost("EQ-001", "D-001", "2026-10-20");
		assertEquals("Training date must be in the future", textOf("error"));
	}
	
	@Test
	void testGivenPastTrainingDateWhenCalculatingCostThenDisplaysValidationError() {
		calculateCost("EQ-001", "D-001", "2026-10-19");
		assertEquals("Training date must be in the future", textOf("error"));
	}
	
	@Test
	void testGivenYouthBoundaryTeamWhenCalculatingCostThenAppliesYouthDiscount() {
		calculateCost("EQ-003", "D-003", "2026-10-21");
		assertEquals("15", textOf("discount"));
	}
	
	@Test
	void testGivenTwoYearUnemploymentBoundaryWhenCalculatingCostThenAppliesTwentyPercentDiscount() {
		calculateCost("EQ-004", "D-003", "2026-10-21");
		assertEquals("20", textOf("discount"));
	}
	
	@Test
	void testGivenThreeYearUnemploymentBoundaryWhenCalculatingCostThenAppliesThirtyPercentDiscount() {
		calculateCost("EQ-005", "D-003", "2026-10-21");
		assertEquals("30", textOf("discount"));
	}
}
