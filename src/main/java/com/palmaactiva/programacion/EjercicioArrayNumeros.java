package com.palmaactiva.programacion;

public class EjercicioArrayNumeros {
    public static void main(String[] args) {
        System.out.println("¿Cuántos números va a tener tu Array?");
        int numElementos = Utiles.leerInt();
        // Creamos un array vacío con el número de elementos que nos indica el Usuario.
        int[] elArray = new int[numElementos];
        for (int indice = 0; indice < numElementos; indice++) {
            System.out.println("Introduce el número " + (indice + 1) + ":");
            // Asignamos el número a al índice actual del bucle.
            elArray[indice] = Utiles.leerInt();
        }
        System.out.println("Tus números son: ");
        int suma = 0;
        for (int numero : elArray) {
            // Escribimos todos los números sin cambios de línea.
            System.out.print(numero + " ");
            // Añadimos a suma, el número actual
            suma += numero;
        }
        System.out.println("La suma es: " + suma);
    }
}