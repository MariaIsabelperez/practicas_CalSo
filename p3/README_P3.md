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
| findYoungestFighter |---|---|---|---|---|

### Cobertura de condiciones seleccionadas
| Condicion atomica | Caso que evalua verdadero | Caso que evalua falso | Casos adicionales | Justificacion si no es factible |
|---|---|---|---|---|

## Pruebas de caja negra

### Clases de equivalencia
| Código de clase | Variable | Condición de pertenencia | Válida / inválida | Comportamiento exigido |
|---|---|---|---|---|

### Casos
| ID del caso | Clases cubiertas | Equipo/dojo/fecha o URL concreta | Estado inicial | Resultado único esperado | Nombre prueba |
|---|---|---|---|---|---|

### Valores límite
| Variable | Valor del límite | Valor elegido | Expectativas |
|---|---|---|---|


## Material adicional

**Enlace al vídeo externo (oculto/no listado):** *completar*.
