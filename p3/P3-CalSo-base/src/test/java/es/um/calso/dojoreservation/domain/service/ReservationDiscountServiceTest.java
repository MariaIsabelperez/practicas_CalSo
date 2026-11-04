package es.um.calso.dojoreservation.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import es.um.calso.dojoreservation.domain.model.Fighter;

public class ReservationDiscountServiceTest {
	private final ReservationDiscountService service = new ReservationDiscountService();
	
	@Test
	void testGivenNullFightersWhenFindingYoungestFighterThenThrowsNullPointerException() {
		NullPointerException exception = assertThrows(
				NullPointerException.class,
				() -> service.findYoungestFighter(null, LocalDate.of(2026, 10, 20)));
		assertEquals("Fighters are required", exception.getMessage());
	}
	
	@Test
	void testGivenNullReferenceDateWhenFindingYoungestFighterThenThrowsNullPointerException() {
		NullPointerException exception = assertThrows(
				NullPointerException.class,
				() -> service.findYoungestFighter(List.of(), null));
		assertEquals("Reference date is required", exception.getMessage());
	}
	
	@Test
	void testGivenEmptyFightersWhenFindingYoungestFighterThenReturnsEmptyOptional() {
		assertTrue(service.findYoungestFighter(List.of(),
				LocalDate.of(2026, 10, 20)).isEmpty());
	}
	
	@Test
    void testGivenNullFighterInListWhenFindingYoungestFighterThenThrowsNullPointerException() {
		List<Fighter> fighters = new ArrayList<>();
	    fighters.add(null);
	    
		NullPointerException exception = assertThrows(
                        NullPointerException.class,
                        () -> service.findYoungestFighter(
                                fighters, LocalDate.of(2026, 10, 20)));
        assertEquals("Null fighter", exception.getMessage());
    }

    @Test
    void testGivenSingleFighterWhenFindingYoungestFighterThenReturnsThatFighter() {
        Fighter fighter =new Fighter("Ken", LocalDate.of(2005, 1, 1), null);
        Optional<Fighter> result =
                service.findYoungestFighter(
                        List.of(fighter),
                        LocalDate.of(2026, 10, 20));
        assertEquals(fighter, result.orElseThrow());
    }

    @Test
    void testGivenTwoFightersWhenFindingYoungestFighterThenReturnsYoungestFighter() {
        Fighter older = new Fighter("Older", LocalDate.of(2000, 1, 1), null);
        Fighter younger = new Fighter("Younger", LocalDate.of(2005, 1, 1), null);
        Optional<Fighter> result =
                service.findYoungestFighter(
                        List.of(older, younger),
                        LocalDate.of(2026, 10, 20));
        assertEquals(younger, result.orElseThrow());
    }
}
