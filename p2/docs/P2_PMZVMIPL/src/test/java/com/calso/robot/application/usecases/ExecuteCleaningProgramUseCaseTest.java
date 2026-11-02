package com.calso.robot.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.calso.robot.domain.model.CleaningProduct;
import com.calso.robot.domain.model.CleaningProgram;
import com.calso.robot.domain.model.ExecutionResult;
import com.calso.robot.domain.valueobjects.Energy;

class ExecuteCleaningProgramUseCaseTest {
	
	private static CleaningProgram program;

	@BeforeAll
	static void setUp() {
		Energy energy = Energy.of(8);
	    program = new CleaningProgram("1", "test", energy, Map.of(CleaningProduct.WATER, 1, CleaningProduct.WAX, 2));
	}

	@Test
	void testGivenNullInventoryWhenCreatingUseCaseThenThrowsNullPointerException() {
		assertThrows(NullPointerException.class, () -> new ExecuteCleaningProgramUseCase(null));
	}
	
	@Test
	void testGivenNullProgramWhenExecutingUseCaseThenThrowsNullPointerException() {
		StubCleaningInventory stock = new StubCleaningInventory(Map.of(CleaningProduct.WATER, 4));
		ExecuteCleaningProgramUseCase useCase = new ExecuteCleaningProgramUseCase(stock);
		
		
		assertThrows(NullPointerException.class, () -> useCase.execute(null));
	}
	

	@Test
	void testGiven3WaterAnd1WaxStockWhenExecutingProgramRequiring1WaterAnd2WaxThenStockRemainsUnchangedAndMissing1Wax() {
		StubCleaningInventory stock = new StubCleaningInventory(Map.of(CleaningProduct.WATER, 3, CleaningProduct.WAX, 1));
		ExecuteCleaningProgramUseCase useCase = new ExecuteCleaningProgramUseCase(stock);
		
		ExecutionResult result = useCase.execute(program);
		
		assertFalse(result.executed());
		assertEquals(0, stock.getNumCallsConsume());
		assertEquals(Map.of(CleaningProduct.WAX, 1), result.missingProducts());
		assertEquals(3, stock.available(CleaningProduct.WATER));
		assertEquals(1, stock.available(CleaningProduct.WAX));
	}
	
	@Test
	void testGiven1SoapStockWhenExecutingProgramRequiring1WaterAnd2WaxThenStockRemainsUnchangedAndMissing1WaterAnd2Wax() {
		StubCleaningInventory stock = new StubCleaningInventory(Map.of(CleaningProduct.SOAP, 1));
		ExecuteCleaningProgramUseCase useCase = new ExecuteCleaningProgramUseCase(stock);
		
		ExecutionResult result = useCase.execute(program);
		
		assertFalse(result.executed());
		assertEquals(0, stock.getNumCallsConsume());
		assertEquals(Map.of(CleaningProduct.WATER, 1, CleaningProduct.WAX, 2), result.missingProducts());
		assertEquals(1, stock.available(CleaningProduct.SOAP));
	}
	
	@Test
	void testGiven3WaterAnd3WaxStockWhenExecutingProgramRequiring1WaterAnd2WaxThenWaterStockBecomes2AndWaxStockBecomes1AndSucceeded() {
		StubCleaningInventory stock = new StubCleaningInventory(Map.of(CleaningProduct.WATER, 3, CleaningProduct.WAX, 3));
		ExecuteCleaningProgramUseCase useCase = new ExecuteCleaningProgramUseCase(stock);
		
		ExecutionResult result = useCase.execute(program);
		
		assertTrue(result.executed());
		assertTrue(result.missingProducts().isEmpty());
		assertEquals(2, stock.available(CleaningProduct.WATER));
		assertEquals(1, stock.available(CleaningProduct.WAX));
	}
	
	@Test
	void testGiven1WaterAnd2WaxStockWhenExecutingProgramRequiring1WaterAnd2WaxThenWaterStockBecomes0AndWaxStockBecomes0AndSucceeded() {
		StubCleaningInventory stock = new StubCleaningInventory(Map.of(CleaningProduct.WATER, 1, CleaningProduct.WAX, 2));
		ExecuteCleaningProgramUseCase useCase = new ExecuteCleaningProgramUseCase(stock);
		
		ExecutionResult result = useCase.execute(program);
		
		assertTrue(result.executed());
		assertTrue(result.missingProducts().isEmpty());
		assertEquals(0, stock.available(CleaningProduct.WATER));
		assertEquals(0, stock.available(CleaningProduct.WAX));
	}
	
	@Test
	void testGiven1WaterAnd2WaxStockWhenExecutingProgramRequiringNoProductsThenStockRemainsUnchangedAndSucceeded() {
		StubCleaningInventory stock = new StubCleaningInventory(Map.of(CleaningProduct.WATER, 1));
		ExecuteCleaningProgramUseCase useCase = new ExecuteCleaningProgramUseCase(stock);
		
		Energy energy = Energy.of(2);
		CleaningProgram programRequiringNoProducts = new CleaningProgram("2", "test", energy, Map.of());
		
		ExecutionResult result = useCase.execute(programRequiringNoProducts);
		
		assertTrue(result.executed());
		assertTrue(result.missingProducts().isEmpty());
		assertEquals(1, stock.available(CleaningProduct.WATER));
	}
	
	@Test
	void testGiven1WaterAnd2WaxStockWhenExecutingProgramRequiring0WaterThenStockRemainsUnchangedAndSucceeded() {
		StubCleaningInventory stock = new StubCleaningInventory(Map.of(CleaningProduct.WATER, 1));
		ExecuteCleaningProgramUseCase useCase = new ExecuteCleaningProgramUseCase(stock);
		
		Energy energy = Energy.of(2);
		CleaningProgram programRequiringNoProducts = new CleaningProgram("3", "test", energy, Map.of(CleaningProduct.WATER, 0));
		
		ExecutionResult result = useCase.execute(programRequiringNoProducts);
		
		assertTrue(result.executed());
		assertTrue(result.missingProducts().isEmpty());
		assertEquals(1, stock.available(CleaningProduct.WATER));
	}
	
	@Test
	void testGiven1WaterAnd2WaxStockWhenExecutingTwiceProgramRequiring1WaterAnd2WaxThenFirstSuceededAndSecondRequires1WaterAnd2Wax() {
		StubCleaningInventory stock = new StubCleaningInventory(Map.of(CleaningProduct.WATER, 1, CleaningProduct.WAX, 2));
		ExecuteCleaningProgramUseCase useCase = new ExecuteCleaningProgramUseCase(stock);
		
		ExecutionResult resultFirst = useCase.execute(program);
		
		assertTrue(resultFirst.executed());
		assertTrue(resultFirst.missingProducts().isEmpty());
		assertEquals(0, stock.available(CleaningProduct.WATER));
		assertEquals(0, stock.available(CleaningProduct.WAX));
		
		
		ExecutionResult resultSecond = useCase.execute(program);
		
		assertFalse(resultSecond.executed());
		assertEquals(1, stock.getNumCallsConsume());
		assertEquals(Map.of(CleaningProduct.WATER, 1, CleaningProduct.WAX, 2), resultSecond.missingProducts());
		assertEquals(0, stock.available(CleaningProduct.WATER));
		assertEquals(0, stock.available(CleaningProduct.WAX));
	}

}
