# Práctica 2 - Pruebas unitarias y análisis de calidad

## Resolución del grupo

### Integrantes, responsabilidades y enlaces de integración

**María Isabel Pérez Lisón**
- Diseño e implementación de pruebas unitarias para corrección de los defectos detectados en las siguientes clases:
    - Energy
    - CleaningProduct
    - CleaningProgram
    - ExecutionResult
    - CleaningProgramByEnergyComparator

**Ramas utilizadas**
- `dominio-comparador`


**Paula Mei Zaragoza Villegas**
- Diseño e implementación de pruebas unitarias para corrección de los defectos detectados en las siguientes clases:
    - StubCleaningInventory
    - ExecuteCleaningProgramUseCase

**Ramas utilizadas**
- `suplantador-usos`

**Pull Requests**

Merge #1 de la rama `dominio-comparador`: `925705f`

### Casos de prueba

| ID | Clase | Condición y resultado esperado | Método JUnit |
| --- | --- | --- | --- |
| CP-01 | Energy | Crear una energía con valor 10 almacena correctamente el valor | testGivenEnergy10WhenCreatingEnergyThenStoresValue10 |
| CP-02 | Energy | Crear una energía con valor 0 provoca una excepción | testGivenEnergy0WhenCreatingEnergyThenThrowsIllegalArgumentException |
| CP-03 | Energy | Dos energías con el mismo valor son iguales | testGivenTwoEnergyObjectsWithValue10WhenComparingThenTheyAreEqual |
| CP-04 | Energy | Dos energías iguales generan el mismo hashCode | testGivenTwoEqualEnergyObjectsWhenComputingHashCodeThenHashCodesMatch |
| CP-05 | CleaningProduct | WATER devuelve "agua" | testGivenWaterWhenCallingToStringThenReturnsAgua |
| CP-06 | CleaningProduct | SOAP devuelve "jabón" | testGivenSoapWhenCallingToStringThenReturnsJabon |
| CP-07 | CleaningProduct | WAX devuelve "cera" | testGivenWaxWhenCallingToStringThenReturnsCera |
| CP-08 | CleaningProduct | GLASS_CLEANER devuelve "limpiacristales" | testGivenGlassCleanerWhenCallingToStringThenReturnsLimpiacristales |
| CP-09 | CleaningProgram | Id nulo provoca excepción | testGivenNullIdWhenCreatingProgramThenThrowsException |
| CP-10 | CleaningProgram | Id vacío provoca excepción | testGivenEmptyIdWhenCreatingProgramThenThrowsException |
| CP-11 | CleaningProgram | Id en blanco provoca excepción | testGivenBlankIdWhenCreatingProgramThenThrowsException |
| CP-12 | CleaningProgram | Nombre nulo provoca excepción | testGivenNullNameWhenCreatingProgramThenThrowsException |
| CP-13 | CleaningProgram | Nombre vacío provoca excepción | testGivenEmptyNameWhenCreatingProgramThenThrowsException |
| CP-14 | CleaningProgram | Nombre en blanco provoca excepción | testGivenBlankNameWhenCreatingProgramThenThrowsException |
| CP-15 | CleaningProgram | Energía nula provoca excepción | testGivenNullEnergyWhenCreatingProgramThenThrowsException |
| CP-16 | CleaningProgram | Requirements nulo provoca excepción | testGivenNullRequirementsWhenCreatingProgramThenThrowsException |
| CP-17 | CleaningProgram | Cantidad negativa provoca excepción | testGivenNegativeRequiredQuantityWhenCreatingProgramThenThrowsIllegalArgumentException |
| CP-18 | CleaningProgram | Cantidad cero es válida | testGivenZeroRequiredQuantityWhenCreatingProgramThenCreatesProgram |
| CP-19 | CleaningProgram | Un mapa vacío es válido | testGivenEmptyRequirementsMapWhenCreatingProgramThenCreatesProgram |
| CP-20 | CleaningProgram | Dos programas con el mismo id son iguales | testGivenProgramWithSameIdWhenComparingThenTheyAreEqual |
| CP-21 | CleaningProgram | Dos programas con distinto id son distintos | testGivenProgramWithDifferentIdsWhenComparingThenTheyAreNotEqual |
| CP-22 | CleaningProgram | Dos programas con el mismo id generan el mismo hashCode | testGivenProgramWithSameIdsWhenComputingHashCodeThenHashCodesMatch |
| CP-23 | CleaningProgram | El programa realiza copia defensiva de requirements | testGivenRequirementsMapWhenModifiedAfterConstructionThenProgramRequirementsRemainUnchanged |
| CP-24 | CleaningProgram | El mapa devuelto es inmodificable | testGivenRequirementsWhenTryingToModifyReturnedMapThenThrowsUnsupportedOperationException |
| CP-25 | CleaningProgramByEnergyComparator | Orden ascendente por energía | testGivenProgramsWithEnergies10And20WhenComparingThenOrdersAscendingByEnergy |
| CP-26 | CleaningProgramByEnergyComparator | En caso de empate, orden por id | testGivenProgramsWithSameEnergyAndIdsP1AndP2WhenComparingThenOrdersById |
| CP-27 | CleaningProgramByEnergyComparator | Si energía e id coinciden devuelve 0 | testGivenProgramsWithSameEnergyAndSameIdWhenComparingThenReturnsZero |

---

### Defectos detectados y corregidos

| ID | Prueba que revela el defecto | Esperado / observado | Causa y corrección | Commit |
| --- | --- | --- | --- | --- |
| D-01 | testGivenEnergy0WhenCreatingEnergyThenThrowsIllegalArgumentException | Debía rechazarse el valor 0 y era aceptado | Se modificó la condición de validación de `< 0` a `<= 0` | 4588f64 |
| D-02 | testGivenTwoEnergyObjectsWithValue10WhenComparingThenTheyAreEqual | Objetos equivalentes se consideraban distintos | Se reimplementó equals() utilizando el valor de energía | 4588f64 |
| D-03 | testGivenTwoEqualEnergyObjectsWhenComputingHashCodeThenHashCodesMatch | Objetos equivalentes producían hashCode distintos | Se reimplementó hashCode() utilizando el valor de energía | 4588f64 |
| D-04 | testGivenGlassCleanerWhenCallingToStringThenReturnsLimpiacristales | Se obtenía "cristales" en lugar de "limpiacristales" | Se corrigió el literal asociado al enum | 60906d2 |
| D-05 | testGivenProgramWithSameIdWhenComparingThenTheyAreEqual | equals() utilizaba el nombre del programa | Se cambió la igualdad para basarla exclusivamente en id | 5619b0d |
| D-06 | testGivenProgramWithSameIdsWhenComputingHashCodeThenHashCodesMatch | hashCode() utilizaba el nombre del programa | Se cambió hashCode() para basarlo exclusivamente en id | 5619b0d |
| D-07 | testGivenRequirementsMapWhenModifiedAfterConstructionThenProgramRequirementsRemainUnchanged | El programa compartía la referencia del mapa original | Se añadió copia defensiva mediante Map.copyOf() | 5619b0d |
| D-08 | testGivenRequirementsWhenTryingToModifyReturnedMapThenThrowsUnsupportedOperationException | El mapa obtenido podía modificarse | Se devolvió un mapa inmodificable | 5619b0d |
| D-09 | testGivenProgramsWithEnergies10And20WhenComparingThenOrdersAscendingByEnergy | El comparador ordenaba de forma descendente | Se corrigió el orden de comparación de energías | ef4ee59 |

---

### Suplantador y desarrollo incremental

*Explicar brevemente.*

### Indicadores de calidad

| Herramienta | Resultado obtenido | Evidencia |
| --- | --- | --- |
| JUnit 5 | | |
| JaCoCo (instrucciones y ramas) | | |
| PIT (mutation coverage y test strength) | | |
| SonarQube (Quality Gate e incidencias) | | |

**Commit de `main` analizado:** *completar*.

**Enlace al vídeo externo (oculto/no listado):** *completar*.
