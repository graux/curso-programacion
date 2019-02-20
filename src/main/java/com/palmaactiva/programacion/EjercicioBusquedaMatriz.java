package com.palmaactiva.programacion;

public class EjercicioBusquedaMatriz {
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

        // Segunda parte Ejercicio.
        System.out.println("¿Qué número quieres que busquemos?");
        int numeroBusqueda = Utiles.leerInt();
        buscarNumero(laMatriz, numeroBusqueda);
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

    public static void buscarNumero(int[][] matriz, int numeroBusqueda) {
        boolean encontrado = false;
        String textoPosicion = null;
        for (int indiceFilas = 0; !encontrado && indiceFilas < matriz.length; indiceFilas++) {
            for (int indiceCols = 0; !encontrado && indiceCols < matriz[indiceFilas].length; indiceCols++) {
                if (numeroBusqueda == matriz[indiceFilas][indiceCols]) {
                    encontrado = true;
                    textoPosicion = (indiceFilas + 1) + "," + (indiceCols + 1);
                    break;
                }
            }
        }
        if (encontrado) {
            System.out.println("El elemento aparece por primera vez en la posición: " + textoPosicion);
        } else {
            System.out.println("El número no se ha encontrado en la matriz.");
        }
    }
}