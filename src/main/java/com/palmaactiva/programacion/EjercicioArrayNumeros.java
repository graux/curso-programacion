package com.palmaactiva.programacion;

public class EjercicioArrayNumeros {
    public static void main(String[] args) {
        System.out.println("¿Cuántos números va a tener tu Array?");
        int numElementos = Utiles.leerInt();
        int[] elArray = new int[numElementos];
        for (int indice = 0; indice < numElementos; indice++) {
            System.out.println("Introduce el número " + (indice + 1) + ":");
            elArray[indice] = Utiles.leerInt();
        }
        System.out.println("Tus números son: ");
        int suma = 0;
        for (int numero : elArray) {
            System.out.print(numero + " ");
            suma += numero;
        }
        System.out.println("La suma es: " + suma);
    }
}