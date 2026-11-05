package es.um.calso.dojoreservation.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import es.um.calso.dojoreservation.domain.model.Discount;
import es.um.calso.dojoreservation.domain.model.Fighter;
import es.um.calso.dojoreservation.domain.model.UnemploymentPeriod;

public class ReservationDiscountServiceTest {
	private final ReservationDiscountService service = new ReservationDiscountService();
	
	/// Tests de 'findYoungestFighter'
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
    
    /// Tests de 'calculateBestDiscount'
    @Test
    void testGivenNullFightersWhenCalculatingBestDiscountThenThrowsNullPointerException() {
    	NullPointerException exception = assertThrows(
				NullPointerException.class,
				() -> service.calculateBestDiscount(null, LocalDate.of(2026, 10, 20)));
    	assertEquals("Fighters are required", exception.getMessage());
    }
    
    @Test
    void testGivenNullTrainingDateWhenCalculatingBestDiscountThenThrowsNullPointerException() {
    	NullPointerException exception = assertThrows(
				NullPointerException.class,
				() -> service.calculateBestDiscount(List.of(), null));
		assertEquals("Training date is required", exception.getMessage());
    }
    
    @Test
    void testGivenEmptyFightersListWhenCalculatingBestDiscountThenReturnsZeroDiscount() {
    	Discount discount = service.calculateBestDiscount(List.of(), LocalDate.of(2026, 10, 20));
    	assertEquals(0, discount.percentage());
    }
    
    @Test
    void testGivenYoungestFighterUnder18WhenCalculatingBestDiscountThenReturns15PercentDiscount() {
    	Fighter fighter = new Fighter("Ken", LocalDate.of(2010, 1, 1), null);
    	Discount discount = service.calculateBestDiscount(List.of(fighter), LocalDate.of(2026, 10, 20));
    	assertEquals(15, discount.percentage());
    }
    
    @Test
    void testGivenYoungestFighterWith18YearsOrMoreWhenCalculatingBestDiscountThenReturnsZeroDiscount() {
    	Fighter fighter = new Fighter("Ken", LocalDate.of(2000, 1, 1), null);
    	Discount discount = service.calculateBestDiscount(List.of(fighter), LocalDate.of(2026, 10, 20));
    	assertEquals(0, discount.percentage());
    }
    
    @Test
    void testGivenNullFighterInListWhenCalculatingBestDiscountThenThrowsNullPointerException() {
    	List<Fighter> fighters = new ArrayList<>();
    	fighters.add(null);
    	NullPointerException exception = assertThrows(
				NullPointerException.class,
				() -> service.calculateBestDiscount(fighters, LocalDate.of(2026, 10, 20)));
		assertEquals("Null fighter", exception.getMessage());
    }
    
    @Test
    void testGivenUnemploymentLessThanOneYearWhenCalculatingBestDiscountThenReturnsZeroDiscount() {
    	Fighter fighter = new Fighter("Ken", LocalDate.of(2000, 1, 1),
    			new UnemploymentPeriod(LocalDate.of(2026, 1, 1)));
    	Discount discount = service.calculateBestDiscount(List.of(fighter), LocalDate.of(2026, 10, 20));
    	assertEquals(0, discount.percentage());
    }
    
    @Test
    void testGivenOneYearOfUnemploymentWhenCalculatingBestDiscountThenReturns10PercentDiscount() {
    	Fighter fighter = new Fighter("Ken", LocalDate.of(2000, 1, 1),
    			new UnemploymentPeriod(LocalDate.of(2025, 10, 20)));
    	Discount discount = service.calculateBestDiscount(List.of(fighter), LocalDate.of(2026, 10, 20));
    	assertEquals(10, discount.percentage());
    }
    
    @Test
    void testGivenTwoYearsOfUnemploymentWhenCalculatingBestDiscountThenReturns20PercentDiscount() {
    	Fighter fighter = new Fighter("Ken", LocalDate.of(2000, 1, 1),
    			new UnemploymentPeriod(LocalDate.of(2024, 10, 20)));
    	Discount discount = service.calculateBestDiscount(List.of(fighter), LocalDate.of(2026, 10, 20));
    	assertEquals(20, discount.percentage());
    }
    
    @Test
    void testGivenThreeYearsOfUnemploymentWhenCalculatingBestDiscountThenReturns30PercentDiscount() {
    	Fighter fighter = new Fighter("Ken", LocalDate.of(2000, 1, 1),
    			new UnemploymentPeriod(LocalDate.of(2023, 10, 20)));
    	Discount discount = service.calculateBestDiscount(List.of(fighter), LocalDate.of(2026, 10, 20));
    	assertEquals(30, discount.percentage());
    }
    
    @Test
    void testGivenYouthAndUnemploymentDiscountWhenCalculatingBestDiscountThenReturnsGreatestDiscount() {
    	Fighter youngFighter = new Fighter("Young", LocalDate.of(2010, 1, 1), null);
    	Fighter unemploymentFighter = new Fighter("Unemployment", LocalDate.of(2000, 1, 1),
    			new UnemploymentPeriod(LocalDate.of(2023, 10, 20)));
    	Discount discount = service.calculateBestDiscount(List.of(youngFighter, unemploymentFighter),
    			LocalDate.of(2026, 10, 20));
    	assertEquals(30, discount.percentage());
    }
}
