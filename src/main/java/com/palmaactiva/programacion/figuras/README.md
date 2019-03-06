# Ejercicio: Figuras
Este ejercicio es una demostración visual de un ejercicio de Herencia / Interfaces para crear y dibujar figuras geométricas.

El ejercicio consta de las siguientes clases e interfaces:
- **App:** Contiene el main para ejecutar la demo. También es la ventana java.
- **AreaFiguras:** Es el componente sobre el que se dibujan las figuras. **Implementa Actualizable**
- **FabricaFiguras:** Esta clase se encarga de crear/guardar/manipular las figuras. Se pueden añadir más figuras o tipos de figuras en esta clase.
- **Figura:** Interfaz que define la mínima funcionalidad de que una figura debe tener.
- **FiguraConBorde:** Clase abstracta con detalles para crear figuras con borde. **Implementa Figura**
- **FiguraConArea:** Clase abstracta con los detalles para crear figuras con area y borde. **Extiende FiguraConBorde y por tanto Implementa Figura**
- **Actualizable:** Interfaz que define la funcionalidad para notificar a una clase cuando tiene que actualizarse.
- **Utiles:** Clase con métodos estáticos para crear contenidos aleatorios: colores, tamaños, puntos, etc.
- **Carpeta Formas:** En esta carpeta/package se incluyen las clases que han de implementar Figura.

## El funcionamiento del código es el siguiente: ##
1. El programa se lanza: crea una ventana y area de figuras.
2. El area de figuras creará una fábrica de figuras y un gestor de eventos para clicks de ratón.
3. Se muestra la ventana y se le pide al area que cree algunas fuguras. Este le pide a su vez a la fábrica que las cree.
4. La fábrica crea figuras y al añadir cada una le pide al area que se repinte.
5. Al repintarse, llama a su método de pintado y luego le pide a la fábrica que pinte las figuras.
6. La fábrica recorre las figuras por orden de creación y les pide que se vayan pintando.
7. Si se hace click en el area de figuras, se crea una nueva figura de forma aleatoria.
