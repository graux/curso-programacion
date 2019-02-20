package com.palmaactiva.programacion;

public class EjercicioMatriz {
    public static void main(String[] args) {
        System.out.println("¿Cuántas FILAS va a tener tu Matriz?");
        int numFilas = Utiles.leerInt();
        System.out.println("¿Cuántas COLUMNAS va a tener tu Matriz?");
        int numColumnas = Utiles.leerInt();
        System.out.println("Tu matriz es " + numFilas + "x" + numColumnas);
        int[][] laMatriz = new int[numFilas][numColumnas];
        String textoPosicion = null;
        for (int indiceFilas = 0; indiceFilas < numFilas; indiceFilas++) {
            for (int indiceCols = 0; indiceCols < numColumnas; indiceCols++) {
                textoPosicion = (indiceFilas + 1) + "," + (indiceCols + 1);
                System.out.println("Introduce el número para la posición [" + textoPosicion + "] :");
                laMatriz[indiceFilas][indiceCols] = Utiles.leerInt();
            }
        }
        printMatriz(laMatriz);
    }

    public static void printMatriz(int[][] matriz) {
        System.out.println("Tu MATRIZ es: ");
        for (int[] filas : matriz) {
            for (int celda : filas) {
                System.out.print(String.format("%5d", celda));
            }
            System.out.println();
        }
    }
}