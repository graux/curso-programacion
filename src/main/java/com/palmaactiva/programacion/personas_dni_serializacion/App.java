package com.palmaactiva.programacion.personas_dni_serializacion;

import com.palmaactiva.programacion.EjercicioDNI;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class App {

    private static final String[] nombres = new String[] { "Fran", "Jose", "Paco", "Pepe", "Maria", "Marta", "Sandra",
            "Ana", "Lucía", "Sofia", "Roberto", "Álvaro", "Clara" };
    private static final String[] apellidos = new String[] { "Garcia", "Fernández", "Martínez", "Torres", "Hernandez",
            "Martín", "Pons", "González", "Rodríguez", "López", "Sánchez", "Ruíz", "Navarro", "Marí", "Tur", "Pons",
            "Coll" };
    private HashMap<String, Persona> socios;

    public App() {
        this.socios = new HashMap<>();
    }

    private void crearSocios(int numSocios) {
        Persona socio = null;
        System.out.println("Creando socios:");
        for (int indice = 0; indice < numSocios; indice++) {
            socio = crearSocioAleatorio();
            this.socios.put(socio.getDNI(), socio);
            System.out.println("  " + (indice + 1) + ".- " + socio.toString());
        }
    }

    private Persona crearSocioAleatorio() {
        Random aleatorio = new Random();
        int numeroDni = this.numeroAleatorio(10000000, 99999999);
        String dni = String.valueOf(numeroDni) + EjercicioDNI.getLetraDNI(numeroDni);
        String nombre = nombres[aleatorio.nextInt(nombres.length)];
        String apellido = apellidos[aleatorio.nextInt(apellidos.length)];

        return new Persona(nombre, apellido, dni, crearFechaNacimientoAleatoria());
    }

    private Date crearFechaNacimientoAleatoria() {
        GregorianCalendar gc = new GregorianCalendar();
        int year = numeroAleatorio(1900, 2010);
        gc.set(GregorianCalendar.YEAR, year);
        int dayOfYear = numeroAleatorio(1, gc.getActualMaximum(GregorianCalendar.DAY_OF_YEAR));
        gc.set(GregorianCalendar.DAY_OF_YEAR, dayOfYear);
        return gc.getTime();
    }

    private int numeroAleatorio(int inicio, int fin) {
        Random aleatorio = new Random();
        return aleatorio.nextInt(fin - inicio) + inicio;
    }

    public static void main(String[] args) {
        App miPrograma = new App();
        miPrograma.crearSocios(200);

        boolean salir = false;
        Scanner teclado = new Scanner(System.in);
        Persona persona = null;
        do {
            System.out.println("Introduce el DNI del socio a buscar:");
            String dni = teclado.nextLine();
            if (dni.equalsIgnoreCase("salir")) {
                salir = true;
            } else if (dni.equalsIgnoreCase("cargar")) {
                try {
                    persona = miPrograma.cargarPersona();
                    System.out.println("Persona cargada: " + persona);
                } catch (Exception exc) {
                    System.out.println("No se pudo cargar desde archivo: " + exc.getLocalizedMessage());
                }
            } else {
                if (miPrograma.socios.containsKey(dni)) {
                    persona = miPrograma.socios.get(dni);
                    System.out.println(persona);
                    miPrograma.guardarPersona(persona);
                    System.out.println("Persona escrita en archivo.");
                    System.out.println();

                } else {
                    System.out.println("No se encontró el socio.");
                }
            }

        } while (!salir);
        teclado.close();
    }

    private void guardarPersona(Persona persona) {
        // Creamos o recibimos una instancia para guardarla
        try {
            // Creamos un objeto para escribir en archivos.
            FileOutputStream fileOut = new FileOutputStream("/tmp/persona.ser");
            // ObjectOutputStream codifica objetos en el archivo
            ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
            // Escribimos uno o varios objetos
            outStream.writeObject(persona);
            // Cerramos todas las instancias para garantizar que se escriben
            // todos los datos en el archivo.
            outStream.close();
            fileOut.close();
        } catch (IOException ioEx) {
            // Pueden haber errores: sin permiso, sin espacio en disco, etc.
            ioEx.printStackTrace();
        }
    }

    // Puede lanzar excepciones de varios tipos
    private Persona cargarPersona() throws IOException, ClassNotFoundException {
        // Preparamos para leer del archivo
        FileInputStream fileIn = new FileInputStream("/tmp/persona.ser");
        // Creamos un ObjectInputStream para leer clases
        ObjectInputStream in = new ObjectInputStream(fileIn);
        // Leemos una instancia de Object y la convertimos a nuestro tipo.
        // Podríamos hacer un instanceof para comprobar si el tipo concuerda.
        Persona persona = (Persona) in.readObject();
        // Cerramos todos los buffers abiertos.
        in.close();
        fileIn.close();
        // Devolvemos nuestra Persona
        return persona;
    }
}
