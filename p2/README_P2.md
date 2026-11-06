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
| CP-28 | ExecuteCleaningProgramUseCase | Si se le pasa un inventario nulo salta una excepción NullPointerException | testGivenNullInventoryWhenCreatingUseCaseThenThrowsNullPointerException |
| CP-29 | ExecuteCleaningProgramUseCase | Si se le pasa un programa nulo salta una excepción NullPointerException | testGivenNullProgramWhenExecutingUseCaseThenThrowsNullPointerException |
| CP-30 | ExecuteCleaningProgramUseCase | Si se intenta ejecutar un programa que requiere de un stock no disponible de un producto que faltan, no ejecuta el programa y no realiza el descuento de productos | testGiven3WaterAnd1WaxStockWhenExecutingProgramRequiring1WaterAnd2WaxThenStockRemainsUnchangedAndMissing1Wax |
| CP-31 | ExecuteCleaningProgramUseCase | Si se intenta ejecutar un programa que requiere de productos que no existen en el inventario y devuelve el mapa con los productos que falta y sus cantidades | testGiven1SoapStockWhenExecutingProgramRequiring1WaterAnd2WaxThenStockRemainsUnchangedAndMissing1WaterAnd2Wax |
| CP-32 | ExecuteCleaningProgramUseCase | Si se intenta ejecutar un programa que tiene stock disponible, debe ejecutarse y descontar los productos correctamente | testGiven3WaterAnd3WaxStockWhenExecutingProgramRequiring1WaterAnd2WaxThenWaterStockBecomes2AndWaxStockBecomes1AndSucceeded |
| CP-33 | ExecuteCleaningProgramUseCase | Si se intenta ejecutar un programa que tiene las cantidades de stock exactas se ejecuta y se reduce el stock a 0 de los productos | testGiven1WaterAnd2WaxStockWhenExecutingProgramRequiring1WaterAnd2WaxThenWaterStockBecomes0AndWaxStockBecomes0AndSucceeded |
| CP-34 | ExecuteCleaningProgramUseCase | Si se intenta ejecutar un programa cuyos requisitos son un mapa vacío, debe ejecutarse y no producir ningún descuento | testGiven1WaterAnd2WaxStockWhenExecutingProgramRequiringNoProductsThenStockRemainsUnchangedAndSucceeded |
| CP-35 | ExecuteCleaningProgramUseCase | Si se intenta ejecutar un programa cuyos requisitos son 0 de un producto, se debe ejecutar y no debe producirse ningún descuento | testGiven1WaterAnd2WaxStockWhenExecutingProgramRequiring0WaterThenStockRemainsUnchangedAndSucceeded |
| CP-36 | ExecuteCleaningProgramUseCase | Si comprueba la mutabilidad del stock entre varias ejecuciones distintas de un programa, para ello se ejecuta el programa 1 vez que reduce el stock a 0 y la segunda vez debe no ejecutarse el programa| testGiven1WaterAnd2WaxStockWhenExecutingTwiceProgramRequiring1WaterAnd2WaxThenFirstSuceededAndSecondRequires1WaterAnd2Wax |
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
| D-10 | testGiven3WaterAnd1WaxStockWhenExecutingProgramRequiring1WaterAnd2WaxThenStockRemainsUnchangedAndMissing1Wax | Se realizaba la llamada a consume aunque no hubiera stock total disponible | Se corrigió el orden de comprobación y consumo para solucionarlo | c28e232 |
| D-11 | testGiven1WaterAnd2WaxStockWhenExecutingProgramRequiring1WaterAnd2WaxThenWaterStockBecomes0AndWaxStockBecomes0AndSucceeded | Si el stock era exacto no se realizaba la ejecución del programa ya que se comprobaba si available <= required | Se modifica la validación de stock por available < required | c28e232 |
| D-12 | testGiven1WaterAnd2WaxStockWhenExecutingProgramRequiring0WaterThenStockRemainsUnchangedAndSucceeded | Si se requiere 0 de un producto, se produce el descuento de menos 0 | Se realiza una comprobación antes de llamar a consume requirement.getValue() > 0 | c28e232 |
| D-13 | testGiven3WaterAnd1WaxStockWhenExecutingProgramRequiring1WaterAnd2WaxThenStockRemainsUnchangedAndMissing1Wax | Si falta de un producto en concreto se agrega al mapa missing required, ese valor no es corresto | Se cambia el valor que se agrega al mapa de missing por required - available | c28e232 |
---

### Suplantador y desarrollo incremental

Con la creación del suplantador estamos sustituyendo la implementación concreta del adaptador (en este caso la base de datos o el repositorio). Gracias a que dependemos de una interfaz y no de una clase concreta, da igual qué objeto se esté usando mientras cumpla dicho contrato, que es lo que ocurre con el suplantador. Esto nos ayuda a separar el desarrollo del testing, permitiéndonos probar el núcleo de la aplicación sin necesidad de tener toda la infraestructura desarrollada.

### Indicadores de calidad

| Herramienta | Resultado obtenido | Evidencia |
| --- | --- | --- |
| JUnit 5 | | |
| JaCoCo (instrucciones y ramas) | | |
| PIT (mutation coverage y test strength) | | |
| SonarQube (Quality Gate e incidencias) | | |

**Commit de `main` analizado:** *completar*.

**Enlace al vídeo externo (oculto/no listado):** *completar*.
