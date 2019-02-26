# Ejercicio: Hundir la Flota
La resolución usa una solución basada en Orientación a Objetos muy básica. No se utilizan elementos como interfaces, herencia y otros.

Más adelante se resolverá este problema con otras técnicas de orientación a objetos que proporcionarán a la solución:
- Menos replicación de código.
- Algoritmos más simplificados.
- Mejor rendimiento.

El ejercicio se ha resuelto mediante el uso de las siguientes clases:
- **Flota:** Contiene tanto el main para ejecutar el programa como la clase encargada de gestionar el tablero/barcos, los disparos, etc.
- **Barco:** Es el objeto que representa y crea barcos de forma aleatoria.
- **Celda:** Cada una de las celdas que ocupa un barco.
- **Disparo:** Información de la posición del disparo y lógica para convertir las posiciones en formato LETRA+NÚMERO (eg A5) a posiciones de nuestro programa: (eg 0,4).

En la clase Disparo existe un tratamiento de errores mediante la estructura: try - catch. La teoría de esta estructura de control de errores se presentará en los siguientes contenidos del curso.

## El funcionamiento del código es el siguiente: ##
1. El programa se lanza e inicia en el método Flota.main.
2. El main crea una instancia del juego e inicializa algunas variables.
3. El juego mostrará la matriz, números de disparos mientras que queden disparos o barcos a flote.
4. El juego pedirá al usuario que introduzca una casilla mientras que introduzca casillas inválidas.
5. Se crea una instancia de Disparo con los datos de la posición del disparo.
6. Se ejecuta el disparo contra el juego, este contra sus barcos y estos contra sus celdas. Si hay impacto en alguno de estos, el disparo se resuelve directamente y deja de comprobar más celdas/barcos.
7. El disparo actualizar la celda impactada o se guarda como posición de agua.
8. Se comprueba si se ha terminado el juego (no quedan barcos a flote o no quedan disparos)... si no continua.
9. Se muestra el estado del juego: (y número de disparos restantes)
    - Se imprimen los nombres de las columnas.
    - Para cada fila se imprime el número de fila.
    - Para cada columna se comprueba que no haya sido un disparo en el agua o si había impacto en un barco.
    - Si hay agua pinta "~", si hay impacto se pinta "X", si no se ha disparado a la posición todavía se pinta " ".
10. Se vuelve a pedir que se introduzca una posición para disparar.