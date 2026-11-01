package com.calso.robot.application.usecases;

import java.util.EnumMap;
import java.util.Map;

import com.calso.robot.domain.model.CleaningProduct;
import com.calso.robot.domain.repositories.ICleaningInventory;

/**
 * Suplantador de inventario para pruebas.
 * La implementación debe mantenerse exclusivamente bajo src/test/java.
 */
public final class StubCleaningInventory implements ICleaningInventory {
	
	private Map<CleaningProduct, Integer> initialStock = new EnumMap<>(CleaningProduct.class); ;

    public StubCleaningInventory(Map<CleaningProduct, Integer> initialStock) {
    	if(initialStock == null)
    		throw new NullPointerException("El stock inicial es nulo.");
    	
    	for (var entry : initialStock.entrySet()) {
    	    CleaningProduct product = entry.getKey();
    	    Integer quantity = entry.getValue();
    	    
    	    if(product == null || quantity == null || quantity < 0)
    	    	throw new IllegalArgumentException("Los valores son nulos o no válidos.");
    	    
    	    this.initialStock.put(product, quantity);
    	}
    }

    @Override
    public int available(CleaningProduct product) {
        if(product == null)
        	throw new NullPointerException("El producto es nulo.");
    	
        Integer quantity = initialStock.get(product);
     
        if(quantity == null)
        	return 0;
        
        return quantity;
    }

    @Override
    public void consume(CleaningProduct product, int quantity) {
    	int stock = available(product);
    	if(quantity > stock|| quantity < 0)
    		throw new IllegalArgumentException("La cantidad no es válida");
    	
    	int stockNuevo = stock - quantity;
    	
    	initialStock.put(product, stockNuevo);
    }
}
