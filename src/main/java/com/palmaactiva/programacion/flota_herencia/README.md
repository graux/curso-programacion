# Ejercicio: Hundir la Flota
La resolución usa una solución basada en Orientación a Objetos con Herencia, Interfaces y Excepciones.

Gracias a la Herencia hemos reutilizado más código y nuestras clases son más simples.
Al usar interfaces el tratamiento de los objetos es más genérico y hemos reducido duplicidades de código/procesamiento.
Al usar Excepciones hemos dejado de usar métodos y variables auxiliares para comprobar el estado.

El ejercicio se ha resuelto mediante el uso de las siguientes clases:
- **Posicionable:** Interfaz para representar cualquier objeto que puede añadirse al tablero.
- **Posicion:** Clase para identificar una posición del tablero. __Implementa Posicionable.__
- **Celda:** Cada una de las celdas que ocupa un barco. __Extiende Posición. Por herencia, implementa Posicionable__
- **Disparo:** Información de la posición del disparo y lógica para convertir las posiciones en formato LETRA+NÚMERO (eg A5) a posiciones de nuestro programa: (eg 0,4). __Extiende Posición. Por herencia, implementa Posicionable__
- **Barco:** Es el objeto que representa y crea barcos de forma aleatoria. __Implementa Posicionable__
- **Flota:** Contiene tanto el main para ejecutar el programa como la clase encargada de gestionar el tablero/barcos, los disparos, etc.
- **DisparoFormatException**: Excepción a medida para lanzar errores cuando los datos de la posición a disparar no son correctos.

En la clase Disparo se ha eliminado la gestión de errores mediante try-catch. En su lugar se lanzan excepciones y la clase Flota se encarga de gestionar los errores.

En la clase Flota se intenta leer un disparo, si lanza una excepción se vuelve a intentar de forma recursiva. Si no da error, se devuelve el disparo. Esta implementación no utiliza bucles y es más sencilla de leer que usando bucles.

## El funcionamiento del código es el siguiente: ##
1. El programa se lanza e inicia en el método Flota.main.
2. El main crea una instancia del juego e inicializa algunas variables.
3. El juego mostrará la matriz, números de disparos mientras que queden disparos o barcos a flote.
4. El juego pedirá al usuario que introduzca una casilla mientras que introduzca casillas inválidas.
5. Se crea una instancia de Disparo con los datos de la posición del disparo.
6. Se busca si la posición de disparo concuerda con algún objeto Posicionable. 
7. Si se encuentra el Posicionable, se aplica el disparo si es de tipo barco, si no, se crea una nueva casilla de agua.
8. Se comprueba si se ha terminado el juego (no quedan barcos a flote o no quedan disparos)... si no continua.
9. Se muestra el estado del juego: (y número de disparos restantes)
    - Se imprimen los nombres de las columnas.
    - Para cada fila se imprime el número de fila.
    - Para cada posición se comprueba si hay un Posicionable en esa posición.
    - Se le pide al posicionable la String a pintar: Disparo "~", Barco "X" o " " Posición " ".
10. Se vuelve a pedir que se introduzca una posición para disparar.