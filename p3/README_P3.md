# Práctica 3 - Diseño y ejecución de pruebas de software de caja blanca y de caja negra

## Resolución del grupo

### Integrantes, responsabilidades y enlaces de integración

| Integrante | Caja blanca: métodos de dominio | Caja negra: interfaz |
|---|---|---|
| **Paula Mei Zaragoza Villegas** | `containsAllRequiredWeapons` y `DojoSearchService.findFirstAvailableDojo`  | Búsqueda de dojos (`/dojos`) |
| **María Isabel Pérez Lisón** | `ReservationDiscountService.findYoungestFighter` y `calculateBestDiscount` | Costes y bonificaciones (`/coste`) |

---

## Pruebas de caja blanca 

### Grafos de flujo
#### ReservationDiscountService.findYoungestFighter
![Grafo findYoungestFighter](imagenes/findYoungestFighter.png)


#### ReservationDiscountService.calculateBestDiscount
![Grafo calculateBestDiscount](imagenes/calculateBestDiscount.png)

---

### Complejidad ciclomática
#### ReservationDiscountService.findYoungestFighter
**Fórmula 1**  
V(G) = P+1 siendo P=5 --> V(G) = 6  
**Fórmula 2**  
El grafo contiene múltiples salidas excepcionales y un retorno normal. 
Para aplicar formalmente la expresión V(G)=E-N+2 debe considerarse un nodo final común al que converjan todas las terminaciones del método.  
**Fórmula 3**  
V(G) = R = 6  

**Conclusión**
La complejidad ciclomática del método es: V(G) = 6


#### ReservationDiscountService.calculateBestDiscount
**Fórmula 1**  
V(G) = P+1 siendo P=11 --> V(G) = 12  
**Fórmula 2**  
El grafo contiene múltiples salidas excepcionales y un retorno normal. 
Para aplicar formalmente la expresión V(G)=E-N+2 debe considerarse un nodo final común al que converjan todas las terminaciones del método.  
**Fórmula 3**  
El número de regiones debe contabilizarse sobre el grafo definitivo considerando el nodo final común.  

**Conclusión**
La complejidad ciclomática del método es: V(G) = 12


### Caminos independientes 
| Método | Nodo o camino identificado | Datos de entrada y fecha | Resultado esperado según especificación | Nombre del test | Resultado ejecución |
|---|---|---|---|---|---|
| findYoungestFighter | C1: fighters nulo | fighters=null, referenceDate=2026-10-20 | NullPointerException("Fighters are required") | testGivenNullFightersWhenFindingYoungestFighterThenThrowsNullPointerException | Superado |
| findYoungestFighter | C2: referenceDate nula | fighters=lista vacía, referenceDate=null | NullPointerException("Reference date is required") | testGivenNullReferenceDateWhenFindingYoungestFighterThenThrowsNullPointerException | Superado |
| findYoungestFighter | C3: lista vacía | fighters=[], referenceDate=2026-10-20 | Optional.empty() | testGivenEmptyFightersListWhenFindingYoungestFighterThenReturnsEmptyOptional | Superado |
| findYoungestFighter | C4: fighter nulo dentro de la lista | fighters=[null], referenceDate=2026-10-20 | NullPointerException("Null fighter") | testGivenNullFighterInListWhenFindingYoungestFighterThenThrowsNullPointerException | Superado |
| findYoungestFighter | C5: un único fighter válido | fighters=[fighter1], referenceDate=2026-10-20 | Optional.of(fighter1) | testGivenSingleFighterWhenFindingYoungestFighterThenReturnsThatFighter | Superado |
| findYoungestFighter | C6: varios luchadores válidos y comparación de fechas de nacimiento | fighters=[fighter1, fighter2], referenceDate=2026-10-20 | Devuelve el fighter con la fecha de nacimiento más reciente (según la especificación, el más joven) | testGivenTwoFightersWhenFindingYoungestFighterThenReturnsYoungestFighter | Fallo inicial. Superado tras la corrección |
| calculateBestDiscount | C1: fighters nulo | fighters=null, trainingDate=2026-10-20 | NullPointerException("Fighters are required") | testGivenNullFightersWhenCalculatingBestDiscountThenThrowsNullPointerException | Superado |
| calculateBestDiscount | C2: trainingDate nula | fighters=[], trainingDate=null | NullPointerException("Training date is required") | testGivenNullTrainingDateWhenCalculatingBestDiscountThenThrowsNullPointerException | Superado |
| calculateBestDiscount | C3: lista vacía | fighters=[], trainingDate=2026-10-20 | Discount(0) | testGivenEmptyFightersListWhenCalculatingBestDiscountThenReturnsZeroDiscount | Superado |
| calculateBestDiscount | C4: fighter más joven con menos de 18 años | lista con un fighter de 17 años, trainingDate=2026-10-20 | Discount(15) | testGivenYoungestFighterUnder18WhenCalculatingBestDiscountThenReturns15PercentDiscount | Superado |
| calculateBestDiscount | C5: fighter más joven con 18 años o más | lista con fighters adultos | Discount(0) | testGivenYoungestFighterWith18YearsOrMoreWhenCalculatingBestDiscountThenReturnsZeroDiscount | Superado |
| calculateBestDiscount | C6: fighter nulo en la lista | fighters=[null], trainingDate=2026-10-20 | NullPointerException("Null fighter") | testGivenNullFighterInListWhenCalculatingBestDiscountThenThrowsNullPointerException | Superado |
| calculateBestDiscount | C7: desempleo menor de 1 año | fighter con desempleo de menos de 1 año completo | Discount(0) | testGivenUnemploymentLessThanOneYearWhenCalculatingBestDiscountThenReturnsZeroDiscount | Superado |
| calculateBestDiscount | C8: desempleo de 1 año completo | fighter con desempleo de exactamente 1 año | Discount(10) | testGivenOneYearOfUnemploymentWhenCalculatingBestDiscountThenReturns10PercentDiscount | Superado |
| calculateBestDiscount | C9: desempleo de 2 años completos | fighter con desempleo de exactamente 2 años | Discount(20) | testGivenTwoYearsOfUnemploymentWhenCalculatingBestDiscountThenReturns20PercentDiscount | Fallo inicial. Superado tras la corrección |
| calculateBestDiscount | C10: desempleo de 3 años completos o más | fighter con desempleo >= 3 años | Discount(30) | testGivenThreeYearsOfUnemploymentWhenCalculatingBestDiscountThenReturns30PercentDiscount | Superado |
| calculateBestDiscount | C11: concurren descuento juvenil y descuento por desempleo | fighter menor de 18 años y fighter con desempleo >= 3 años | Discount(30), aplicando únicamente el mayor descuento | testGivenYouthAndUnemploymentDiscountWhenCalculatingBestDiscountThenReturnsGreatestDiscount | Superado |
| calculateBestDiscount | C12: descuento calculado no supera el actual | bestPercentage ya contiene un valor superior | Se conserva el porcentaje mayor | Cubierto por los casos C10 y C11 | Superado |


### Cobertura de condiciones seleccionadas
| Condicion atomica | Caso que evalua verdadero | Caso que evalua falso | Casos adicionales | Justificacion si no es factible |
|---|---|---|---|---|
| `youngest==null` | Primer fighter de una lista no vacía | Segundo o posteriores fighters de la lista | Ninguno | Factible |
| `fighter.birthDate().isBefore(youngest.birthDate())` | Fighter con fecha de nacimiento anterior a la almacenada en `youngest` | Fighter con fecha de nacimiento igual o posterior a la almacenada en `youngest` | Ninguno | Factible |
| Cortocircuito del operador `\|\|` | Cuando `youngest==null` es verdadero | No aplica | Lista no vacía con un único fighter | La segunda condición no se evalúa debido al cortocircuito del operador `\|\|` |

La decisión analizada está formada por dos condiciones atómicas unidas mediante el operador lógico `||`.  
Cuando la condición `youngest == null` toma el valor verdadero, Java realiza evaluación por cortocircuito y no evalúa la segunda condición
`fighter.birthDate().isBefore(youngest.birthDate())`.  
Por ello se han diseñado casos que permiten observar la evaluación verdadera y falsa de ambas condiciones, así como la situación en la que la segunda condición no llega a evaluarse.

---

### Defectos detectados tras la ejecución de pruebas JUnit
#### ReservationDiscountService.findYoungestFighter
Se sustituyó:  
```java
fighter.birthDate().isBefore(youngest.birthDate())
```
por  
```java
fighter.birthDate().isAfter(youngest.birthDate())
```  

La especificación establece que el método debe devolver el luchador con la fecha de nacimiento más reciente (el más joven). Sin embargo, la implementación original utilizaba `isBefore()`, seleccionando al luchador con la fecha de nacimiento más antigua.  
Tras la corrección, se ejecutó nuevamente el test:
`testGivenTwoFightersWhenFindingYoungestFighterThenReturnsYoungestFighter`, 
obteniéndose el resultado esperado.  
Además, se repitió la ejecución completa de las pruebas de 'findYoungestFighter', verificando que no se introdujeron regresiones y que todos los casos continuaban superándose correctamente.

#### ReservationDiscountService.calculateBestDiscount
Se sustituyó:
```java
else if (completeYears > 2)
```
por
```java
else if (completeYears >= 2)
```

La especificación establece que un periodo de desempleo de 2 años completos y menos de 3 años debe producir una bonificación del 20 %.  
La implementación original asignaba el 10 % para exactamente 2 años de desempleo, debido a que utilizaba la condición `completeYears > 2`.  
Tras la corrección, se ejecutó nuevamente el test:
`testGivenTwoYearsOfUnemploymentWhenCalculatingBestDiscountThenReturns20PercentDiscount`, obteniéndose el resultado esperado.  
Asimismo, se repitió la batería completa de pruebas, verificando que no se introdujeron regresiones.

---

## Pruebas de caja negra

### Clases de equivalencia
| Código de clase | Variable | Condición de pertenencia | Válida / inválida | Comportamiento exigido |
|---|---|---|---|---|
| CE1 | teamId | Identificador de equipo existente | Válida | Continúa el cálculo del coste |
| CE2 | teamId | Identificador vacío | Inválida | "Team id is required" |
| CE3 | teamId | Identificador inexistente | Inválida | "Team does not exist" |
| CE4 | dojoId | Identificador de dojo existente | Válida | Continúa el cálculo del coste |
| CE5 | dojoId | Identificador vacío | Inválida | "Dojo id is required" |
| CE6 | dojoId | Identificador inexistente | Inválida | "Dojo does not exist" |
| CE7 | trainingDate | Fecha futura | Válida | Continúa el cálculo del coste |
| CE8 | trainingDate | Fecha actual o anterior a la fecha actual del sistema | Inválida | "Training date must be in the future" |


### Casos
| ID del caso | Clases cubiertas | Equipo/dojo/fecha o URL concreta | Estado inicial | Resultado único esperado | Nombre prueba |
|---|---|---|---|---|---|

### Valores límite
Los valores límite se han seleccionado aplicando la técnica de análisis de valores límite sobre las restricciones definidas en la especificación funcional.  
Para la `trainingDate` se ha utilizado la fecha fija de entorno E2E (20/10/2026), comprobando un valor anterior, el propio límite y un valor posterior. Para las bonificaciones se han considerado los puntos de cambio de comportamiento indicados en la especificación: 18 años para el descuento juvenil y 1, 2 y 3 años completos para los tramos de desempleo.

| Variable | Valor del límite | Valor elegido | Expectativas |
|---|---|---|---|
| trainingDate | Día anterior al límite | 2026-10-19 | Error: "Training date must be in the future" |
| trainingDate | Valor límite | 2026-10-20 | Error: "Training date must be in the future" |
| trainingDate | Día posterior al límite | 2026-10-21 | Fecha válida y cálculo del coste correcto |
| Edad del luchador más joven | 18 años | 17 años | Descuento juvenil del 15% |
| Edad del luchador más joven | 18 años | 18 años | Sin descuento juvenil |
| Edad del luchador más joven | 18 años | 19 años | Sin descuento juvenil |
| Antigüedad de desempleo | 1 año | 1 año | Descuento del 10% |
| Antigüedad de desempleo | 2 años | 2 años | Descuento del 20% |
| Antigüedad de desempleo | 3 años | 3 años | Descuento del 30% |

---

## Material adicional

**Enlace al vídeo externo (oculto/no listado):** *completar*.
