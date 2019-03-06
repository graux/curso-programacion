package com.palmaactiva.programacion;

import java.io.InvalidClassException;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.Random;

public class EjercicioExcepciones {
    public static void main(String[] args) {
        boolean terminar = false;

        System.out.println("Escribe 'salir' para terminar el programa.");
        String tipoDato = null;
        String lineaTexto = null;
        Boolean valorValido = true;
        do {
            tipoDato = getTipoDeDatoAleatorio();
            System.out.println(getPreguntaTipoDato(tipoDato));
            // Mejor que usar esta solución con dos bucles, es mejor usar un bucle y llamara
            // a un método recursivo, hasta que se alcanza un valor.
            do {
                lineaTexto = Utiles.leerString();
                if (lineaTexto.trim().equalsIgnoreCase("salir")) {
                    terminar = true;
                    System.out.println("Cerrando aplicación");
                } else {
                    try {
                        procesarValor(tipoDato, lineaTexto);
                        System.out.println("El valor es válido!\n");
                        valorValido = true;
                    } catch (Exception e) {
                        System.out.println("ERROR: " + e.getMessage() + "!\n");
                        valorValido = false;
                    }
                }
            } while (!valorValido);
        } while (!terminar);
    }

    private static void procesarValor(String tipoDato, String lineaTexto)
            throws NumberFormatException, ParseException, InvalidClassException {
        lineaTexto = lineaTexto.trim().toLowerCase();
        switch (tipoDato.toLowerCase()) {
        case "integer":
            Integer.parseInt(lineaTexto);
            break;
        case "float":
            Float.parseFloat(lineaTexto);
            break;
        case "boolean":
            if (!lineaTexto.equals("verdad") && !lineaTexto.equals("falso")) {
                throw new ParseException(lineaTexto + " no es un valor lógico válido", 0);
            }
            break;
        case "string":
            if (lineaTexto.isEmpty()) {
                throw new ParseException("La cadena debe tener algún valor", 0);
            }
            break;
        default:
            throw new InvalidClassException("El tipo no es válido");
        }
    }

    private static String getTipoDeDatoAleatorio() {
        String[] tiposDeDatos = new String[] { Integer.class.getSimpleName(), Float.class.getSimpleName(),
                Boolean.class.getSimpleName(), String.class.getSimpleName() };
        Random aleatorio = new Random();
        int indice = aleatorio.nextInt(tiposDeDatos.length);
        return tiposDeDatos[indice];
    }

    private static String getPreguntaTipoDato(String tipoDato) {
        switch (tipoDato.toLowerCase()) {
        case "integer":
            return "Introduce un número entero:";
        case "float":
            return "Introduce un número decimal:";
        case "boolean":
            return "Introduce 'verdad' o 'falso':";
        case "string":
            return "Introduce un texto cualquiera:";
        }
        throw new InvalidParameterException(tipoDato + " no es un tipo válido en este ejercicio.");
    }
}