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
        // Se invoca otro método para buscar el número.
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
        // Creamos una variable para detectar si se ha encontrado o no el número.
        boolean encontrado = false;
        // Creamos una variable para guardar la posición si se encuentra el número.
        String textoPosicion = null;
        // Comprobamos que recorremos las filas y que aún no se haya encontrado el número
        for (int indiceFilas = 0; !encontrado && indiceFilas < matriz.length; indiceFilas++) {
            // Comprobamos que recorremos las columnas y que aún no se haya encontrado el número
            for (int indiceCols = 0; !encontrado && indiceCols < matriz[indiceFilas].length; indiceCols++) {
                // Si es el mismo número
                if (numeroBusqueda == matriz[indiceFilas][indiceCols]) {
                    // Marcamos que hemos encontrado el elemento. 
                    // Los bucles no continuarán una vez comprueben su condición.
                    encontrado = true;
                    // Guardamos la posición actual.
                    textoPosicion = (indiceFilas + 1) + "," + (indiceCols + 1);
                }
            }
        }
        // Si hemos encontrado el elemento.
        if (encontrado) {
            // Mostramos la posición donde se econtró el elemento.
            System.out.println("El elemento aparece por primera vez en la posición: " + textoPosicion);
        } else {
            System.out.println("El número no se ha encontrado en la matriz.");
        }
    }
    public static void buscarNumero2(int[][] matriz, int numeroBusqueda) {
        // Creamos una variable para detectar si se ha encontrado o no el número.
        boolean encontrado = false;
        // Creamos una variable para guardar la posición si se encuentra el número.
        String textoPosicion = null;
        // Comprobamos que recorremos las filas y que aún no se haya encontrado el número
        for (int indiceFilas = 0; indiceFilas < matriz.length; indiceFilas++) {
            // Comprobamos que recorremos las columnas y que aún no se haya encontrado el número
            for (int indiceCols = 0; indiceCols < matriz[indiceFilas].length; indiceCols++) {
                // Si es el mismo número
                if (numeroBusqueda == matriz[indiceFilas][indiceCols]) {
                    // Marcamos que hemos encontrado el elemento.
                    encontrado = true;
                    // Guardamos la posición actual.
                    textoPosicion = (indiceFilas + 1) + "," + (indiceCols + 1);
                    // Salimos del primer bucle si hemos encontrado el elemento
                    break;
                }
            }
            if (encontrado){
                // Salimos del segundo bucle si se ha encontrado el elemento.
                break;
            }
        }
        // Si hemos encontrado el elemento.
        if (encontrado) {
            // Mostramos la posición donde se econtró el elemento.
            System.out.println("El elemento aparece por primera vez en la posición: " + textoPosicion);
        } else {
            System.out.println("El número no se ha encontrado en la matriz.");
        }
    }
}