package com.palmaactiva.programacion;

import java.util.Calendar;

public class EjercicioEtiquetasEdad {
    public static void main(String[] args) {
        int anyo = Calendar.getInstance().get(Calendar.YEAR);
        System.out.println("Escribe tu año de nacimiento: ");

        int tuPropiaFecha = Utiles.leerInt();
        int edadCalculada = anyo - tuPropiaFecha;

        String etiqueta = "jubilado";
        if (edadCalculada <= 0) {
            etiqueta = "En proyecto";
        } else if (edadCalculada < 6) {
            etiqueta = "Bebe";
        } else if (edadCalculada < 18) {
            etiqueta = "Estudiante";
        } else if (edadCalculada < 65) {
            etiqueta = "Activo";
        }
        System.out.println("Tu estado es: " + etiqueta);
    }
}