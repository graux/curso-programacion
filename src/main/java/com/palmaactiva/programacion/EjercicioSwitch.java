package com.palmaactiva.programacion;

public class EjercicioSwitch {
    public static void main(String[] args) {
        int numeroDia = 4;
        String nombreDia = getNombreDiaSemana(numeroDia);
        System.out.println("El día " + numeroDia + " es el: " + nombreDia);
        int numeroDiaInvertido = getNumeroDiaSemana(nombreDia);
        System.out.println("El día " + nombreDia + " es el día número: " + numeroDiaInvertido);
        numeroDiaInvertido = getNumeroDiaSemanaSimplificado(nombreDia);
        System.out.println("(II) El día " + nombreDia + " es el día número: " + numeroDiaInvertido);
    }

    public static String getNombreDiaSemana(int numeroDia) {
        // Comprobamos que el número esté entre 1 y 7, si no devolveremos null
        if (numeroDia < 1 || numeroDia > 7) {
            return null;
        }

        String nombreDia = null;

        // Miramos qué valor tiene numeroDia para asignarle un valor de nombre dia
        switch (numeroDia) {
        case 1:
            nombreDia = "Lunes";
            break;
        case 2:
            nombreDia = "Martes";
            break;
        case 3:
            nombreDia = "Miércoles";
            break;
        case 4:
            nombreDia = "Jueves";
            break;
        case 5:
            nombreDia = "Viernes";
            break;
        case 6:
        case 7:
            nombreDia = "Fin de Semana";
            break;
        default:
            // Esto nunca ocurrirá, se puede eliminar este default.
            // El if del principio de este método comprueba que numeroDia sólo puede ser un
            // valor entre 1 y 7.
            // Al crear todos los casos, default no haría falta, pero es un doble sistema de
            // seguirdad.
            nombreDia = null;
        }
        return nombreDia;
    }

    public static int getNumeroDiaSemana(String nombreDia) {
        // Comprobamos si el valor es null antes de operar con él.
        if(nombreDia == null){
            return -1;
        }
        // Como no sabemos si el nombre está en mayúsculas/minúsculas, lo pasamos a
        // minúsculas todo:
        nombreDia = nombreDia.toLowerCase();
        // Por defecto numeroDia es -1, error.
        int numeroDia = -1;
        switch (nombreDia) {
        case "lunes":
            numeroDia = 1;
            break;
        case "martes":
            numeroDia = 2;
            break;
        case "miercoles": // Comprobamos con el valor con y sin acento... por si acaso
        case "miércoles":
            numeroDia = 3;
            break;
        case "jueves":
            numeroDia = 4;
            break;
        case "viernes":
            numeroDia = 5;
            break;
        case "sabado": // Comprobamos con el valor con y sin acento... por si acaso
        case "sábado":
            numeroDia = 6;
            break;
        case "domingo": // Si es fin de semana, elegimos el domingo
        case "fin de semana":
            numeroDia = 7;
            break;
        default: // No haría falta, es el mismo valor que el valor por defecto
            numeroDia = -1;
        }
        return numeroDia;
    }

    public static int getNumeroDiaSemanaSimplificado(String nombreDia) {
        // Comprobamos si el valor es null antes de operar con él.
        if(nombreDia == null){
            return -1;
        }
        // Como no sabemos si el nombre está en mayúsculas/minúsculas, lo pasamos a
        // minúsculas todo:
        nombreDia = nombreDia.toLowerCase();
        switch (nombreDia) {
        case "lunes":
            return 1;
        // Al hacer return no es necesario el break, porque saldrá del método y no
        // seguirá ejecutando nada.
        case "martes":
            return 2;
        case "miercoles": // Comprobamos con el valor con y sin acento... por si acaso
        case "miércoles":
            return 3;
        case "jueves":
            return 4;
        case "viernes":
            return 5;
        case "sabado": // Comprobamos con el valor con y sin acento... por si acaso
        case "sábado":
            return 6;
        case "domingo": // Si es fin de semana, elegimos el domingo
        case "fin de semana":
            return 7;
        default:
            return -1;
        }
    }
}