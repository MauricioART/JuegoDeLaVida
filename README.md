# Juego de la Vida

Implementación en Java del autómata celular de John Conway, con una versión gráfica (Swing) y una versión de consola.

## Demostración



https://github.com/user-attachments/assets/653f1bff-cccb-4da8-8737-5487f90930e0



## Requisitos

- Java 17 o superior (`java -version`)
- Apache Maven 3.6 o superior (`mvn -v`)

## Compilar

Desde la carpeta raíz del proyecto:

```bash
mvn package
```

Esto genera el archivo `target/juego-de-la-vida-1.0-SNAPSHOT.jar`.

## Ejecutar

### Versión gráfica (predeterminada)

Con Maven:

```bash
mvn compile exec:java
```

O con el jar generado:

```bash
java -jar target/juego-de-la-vida-1.0-SNAPSHOT.jar
```

**Uso:**
- Haz clic o arrastra sobre el tablero para activar o desactivar células.
- **Iniciar** / **Pausar**: corre la simulación automáticamente.
- **Paso**: avanza una sola generación.
- **Limpiar**: reinicia el tablero.
- Elige el tamaño del tablero (12, 24, 36 o 48) y la regla de vecindad:
  - *Conway (8 vecinos)*: incluye las diagonales.
  - *Original (4 vecinos)*: solo arriba, abajo, izquierda y derecha.

### Versión de consola

Con Maven:

```bash
mvn compile exec:java -Dmain.class=JuegoDeLaVida
```

O directamente con Java, después de compilar:

```bash
java -cp target/classes JuegoDeLaVida
```

**Uso:**
1. Ingresa la dimensión `n` del tablero (n × n).
2. Escribe la fila y la columna de cada célula viva; responde `1` para agregar otra o `0` para terminar.
3. En el menú elige `1` para ver la siguiente generación, `2` para reiniciar o `3` para salir.

## Estructura

```
src/main/java/
├── Celula.java                 # Estado de una célula y sus vecinos
├── Mundo.java                  # Tablero y reglas de evolución
├── JuegoDeLaVida.java          # Interfaz de consola
└── JuegoDeLaVidaGrafico.java   # Interfaz gráfica (Swing)
```

## Autor

Mauricio Arturo Aguilera Roa
