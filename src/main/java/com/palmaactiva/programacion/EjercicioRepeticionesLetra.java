package com.palmaactiva.programacion;

public class EjercicioRepeticionesLetra {
    public static void main(String[] args) {
        System.out.println("Por favor introduce un texto:");
        String texto = Utiles.leerString();
        System.out.println("Por favor introduce la letra que deseas buscar:");
        // Solución 1. Usando leerString y cogiendo el primer carácter.
        int numeroApariciones1 = getNumLetrasStringChar(texto);
        // Solución 2. Usando una nueva función para leer un char directamente.
        // int numeroApariciones2 = getNumLetrasChar(texto);
        // Solución 3. Usando Strings únicamente.
        // int numeroApariciones3 = getNumLetrasString(texto);
        System.out.println("Solución 1: La letra aparece " + numeroApariciones1 + " veces.");
        // System.out.println("Solución 2: La letra aparece " + numeroApariciones2 + " veces.");
        // System.out.println("Solución 3: La letra aparece " + numeroApariciones3 + " veces.");
    }

    public static int getNumLetrasStringChar(String texto) {
        // Ponemos el texto en minúsculas para comparar mejor.
        texto = texto.toLowerCase();
        // Leermos una línea, la ponemos en minúsculas y nos quedamos con la primera
        // letra.
        char letra = Utiles.leerString().toLowerCase().charAt(0);
        int numeroApariciones = 0;
        // Uso toCharArray para convertir la String a un char[] y poderlo recorrer
        // usando un bucle for each.
        for (char letraEnTexto : texto.toCharArray()) {
            // Si es la misma letra, entonces incrementamos el contador.
            if (letra == letraEnTexto) {
                numeroApariciones++;
            }
        }
        return numeroApariciones;
    }

    public static int getNumLetrasChar(String texto) {
        // Leemos directamente un char
        char letra = Utiles.leerChar();
        char letraMinuscula = Character.toLowerCase(letra);
        int numeroApariciones = 0;
        // Uso toCharArray para convertir la String a un char[] y poderlo recorrer
        // usando un bucle for each.
        for (char letraEnTexto : texto.toCharArray()) {
            // Si es la misma letra, entonces incrementamos el contador.
            if (letraMinuscula == Character.toLowerCase(letraEnTexto)) {
                numeroApariciones++;
            }
        }
        return numeroApariciones;
    }

    public static int getNumLetrasString(String texto) {
        // Leermos una línea y nos quedamos con una sub-string
        // que contiene sólo el primer carácter.
        String letra = Utiles.leerString().substring(0, 1);
        int numeroApariciones = 0;
        // Uso toCharArray para convertir la String a un char[] y poderlo recorrer
        // usando un bucle for each.
        String letraEnTexto = null;
        for (int indice = 0; indice < texto.length(); indice++) {
            // Extraemos una sub-string de un carácter de longitud y comparamos.
            letraEnTexto = texto.substring(indice, indice + 1);
            // Si es la misma letra, entonces incrementamos el contador. String es objeto,
            // por tanto tenemos que comparar con equals.
            // equalsIgnoreCase hace la comprobación sin importar mayúsculas o minúsculas.
            if (letra.equalsIgnoreCase(letraEnTexto)) {
                numeroApariciones++;
            }
        }
        return numeroApariciones;
    }
}