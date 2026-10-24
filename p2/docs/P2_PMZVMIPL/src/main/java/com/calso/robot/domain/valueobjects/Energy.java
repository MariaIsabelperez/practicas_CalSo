package com.calso.robot.domain.valueobjects;

public final class Energy {
    private final int units;
    
    private Energy(int units) { 
        this.units = units; 
    }
    
    public static Energy of(int units) {
        if (units <= 0)	//Sólo acepta valores estrictamente mayores que 0
            throw new IllegalArgumentException("Energy must be positive");
        return new Energy(units);
    }
    
    public int getValue() { 
        return units; 
    }
    
    @Override
    public boolean equals(Object other) { //Debe compararse el atributo que representa la energía
        if (this == other) {
        	return true;
        }
        if (!(other instanceof Energy energy)) {
        	return false;
        }
        return units == energy.units;
    }
    
    @Override
    public int hashCode() { //Debe depender del valor almacenado
        return Integer.hashCode(units); 
    }
}
