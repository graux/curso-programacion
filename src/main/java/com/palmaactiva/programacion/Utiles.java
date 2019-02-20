package com.palmaactiva.programacion;

import java.util.Scanner;

public class Utiles {
    public static int leerInt() {
        Scanner scanner = new Scanner(System.in);
        int numero = scanner.nextInt();
        // scanner.close();
        return numero;
    }

    public static String leerString() {
        Scanner scanner = new Scanner(System.in);
        String texto = scanner.nextLine();
        // scanner.close();
        return texto;
    }

	public static char leerChar() {
        Scanner scanner = new Scanner(System.in);
        char letra = scanner.next().charAt(0);
        return letra;
	}
}