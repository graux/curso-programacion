package com.palmaactiva.programacion;

import java.util.ArrayList;

public class EjercicioArrayListNumeros {
    public static void main(String[] args) {
        System.out.println("Escribe tantos números como quieras. Introduce 0 para terminar");
        // Preparamos la lista
        ArrayList<Integer> numeros = new ArrayList<Integer>();
        int numero;
        // Ejemplo de Do-While
        do {
            // Leemos un número
            numero = Utiles.leerInt();
            // Si no es cero o negativo y no lo hemos añadido previamente a la lista
            if (numero > 0 && !numeros.contains(numero)) {
                // Se añade a la lista de números
                numeros.add(numero);
            }
        // Continuamos en el bucle mientras que el valor se mayor que cero
        } while (numero > 0);

        System.out.println("Tus números son:");
        // Preparamos una variable auxiliar para guardar la suma
        int suma = 0;
        for (Integer numeroInt : numeros) {
            // Vamos incrementando suma con cada valor guardado en la lista
            suma += numeroInt;
            // Mostramos el número
            System.out.print(numeroInt + " ");
        }
        // Mostramos el total de la suma
        System.out.println("La suma total es: " + suma);
    }
}