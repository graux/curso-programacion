package com.palmaactiva.programacion;

public class EjercicioDNI {
    public static void main(String[] args) {
        System.out.println("Por favor, introduce tu número de DNI:");
        int numeroDNI = Utiles.leerInt();
        char letraDNI = getLetraDNI(numeroDNI);
        System.out.println("Tu DNI Completo es: " + numeroDNI + "-" + letraDNI);
    }

    public static char getLetraDNI(int numeroDNI) {
        char[] letrasDNI = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V',
                'H', 'L', 'C', 'K', 'E' };
        int indiceLetra = numeroDNI % letrasDNI.length; // letrasDNI.length es 23
        return letrasDNI[indiceLetra];
    }
}