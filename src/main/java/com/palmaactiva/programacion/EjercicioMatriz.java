package com.palmaactiva.programacion;

public class EjercicioMatriz {
    public static void main(String[] args) {
        System.out.println("¿Cuántas FILAS va a tener tu Matriz?");
        int numFilas = Utiles.leerInt();
        System.out.println("¿Cuántas COLUMNAS va a tener tu Matriz?");
        int numColumnas = Utiles.leerInt();
        System.out.println("Tu matriz es " + numFilas + "x" + numColumnas);
        // Creamos una matriz vacía con las dos dimensiones leídas.
        int[][] laMatriz = new int[numFilas][numColumnas];
        String textoPosicion = null;
        for (int indiceFilas = 0; indiceFilas < numFilas; indiceFilas++) {
            for (int indiceCols = 0; indiceCols < numColumnas; indiceCols++) {
                // Preparamos el texto de la posición.
                textoPosicion = (indiceFilas + 1) + "," + (indiceCols + 1);
                System.out.println("Introduce el número para la posición [" + textoPosicion + "] :");
                // Asignamos el valor leído a la posición de la matriz.
                laMatriz[indiceFilas][indiceCols] = Utiles.leerInt();
            }
        }
        // Llamamos a otro método para mostrar los elementos de la matriz.
        printMatriz(laMatriz);
    }

    public static void printMatriz(int[][] matriz) {
        System.out.println("Tu MATRIZ es: ");
        // Recorremos las filas
        for (int[] filas : matriz) {
            // Recorremos las celdas/columnas de cada fila
            for (int celda : filas) {
                // String format %5d muestra un número ocupando 5 espacios. Ejemplo "    3" "  333"
                System.out.print(String.format("%5d", celda));
            }
            System.out.println();
        }
    }
}