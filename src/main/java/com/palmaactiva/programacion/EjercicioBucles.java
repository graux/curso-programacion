package com.palmaactiva.programacion;

public class EjercicioBucles {
    public static void main(String[] args) {
        int[] numeros = { 3, 6, 9, 12, 15, 18, 21, 24, 27, 30 };

        System.out.println("- Ejercicio Bucle For:");
        ejercicioForNumeros(numeros);

        System.out.println("\n-Ejercicio Bucle For Each:");
        ejercicioForEachNumeros(numeros);

        System.out.println("\n-Ejercicio Bucle For Invertido:");
        ejercicioForInvertidoNumeros(numeros);

        System.out.println("\nEjercicio Bucle For Invertido Pares:");
        ejercicioForInvertidoNumerosPares(numeros);
    }

    public static void ejercicioForNumeros(int[] numeros) {
        int numero;
        for (int indice = 0; indice < numeros.length; indice++) {
            numero = numeros[indice];
            System.out.println("Índice "+indice+",Número: " + numero);
        }
    }

    public static void ejercicioForEachNumeros(int[] numeros) {
        for (int numero : numeros) {
            System.out.println("Número: " + numero);
        }
    }

    public static void ejercicioForInvertidoNumeros(int[] numeros) {
        int numero;
        for (int indice = numeros.length - 1; indice >= 0; indice--) {
            numero = numeros[indice];
            System.out.println("Número: " + numero);
        }
    }

    public static void ejercicioForInvertidoNumerosPares(int[] numeros) {
       for (int numero : numeros) {
           if (numero % 2 == 0){
            System.out.println("Número Par: " + numero);
           }
        }
    }
}