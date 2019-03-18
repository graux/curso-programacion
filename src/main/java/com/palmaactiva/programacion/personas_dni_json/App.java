package com.palmaactiva.programacion.personas_dni_json;

import com.palmaactiva.programacion.EjercicioDNI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;

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
            // Creamos unos objetos para escribir en archivos.
            OutputStream fileOut = new FileOutputStream("/tmp/persona.json");
            Writer writer = new OutputStreamWriter(fileOut);
            // Creamos una instancia de Gson
            Gson gson = new GsonBuilder().create();
            // Convertimos la persona a JSON y directamente la guardamos.
            gson.toJson(persona, writer);
            // Cerramos todo para que se escriban corretamente los archivos.
            writer.close();
            fileOut.close();
        } catch (IOException ioEx) {
            // Pueden haber errores: sin permiso, sin espacio en disco, etc.
            ioEx.printStackTrace();
        }
    }

    // Puede lanzar excepciones de varios tipos
    private Persona cargarPersona() throws JsonSyntaxException, FileNotFoundException, IOException {
        // Creamos unos objetos para escribir en archivos.
        InputStream fileIn = new FileInputStream("/tmp/persona.json");
        Reader reader = new InputStreamReader(fileIn);
        // Creamos una instancia de Gson
        Gson gson = new GsonBuilder().create();
        // Cargamos el JSON del archivo como una instancia Persona
        Persona persona = gson.fromJson(reader, Persona.class);
        // Cerramos el archivo
        reader.close();
        // Devolvemos la instancia
        return persona;
    }
}
