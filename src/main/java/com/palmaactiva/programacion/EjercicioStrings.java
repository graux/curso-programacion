package com.palmaactiva.programacion;

public class EjercicioStrings {
    public static void main(String[] args) {
        String texto = "Esto es un ejercicio de Strings";
        System.out.println("El texto del ejercico es: " + texto);

        char letraCentral = getLetraCentral(texto);
        System.out.println("La letra central es: " + letraCentral);

        String textoInvertido = getTextoInvertido(texto);
        System.out.println("El texto invertido es: " + textoInvertido);
    }

    public static char getLetraCentral(String texto) {
        if (texto == null) {
            return ' ';
        }
        int posicionCentral = texto.length() / 2;
        return texto.charAt(posicionCentral);
    }

    public static String getTextoInvertido(String texto) {
        String textoInvertido = "";
        for (int indice = texto.length() - 1; indice >= 0; indice--) {
            textoInvertido = textoInvertido + texto.charAt(indice);
        }
        return textoInvertido;
    }
}