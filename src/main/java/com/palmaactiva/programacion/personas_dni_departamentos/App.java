package com.palmaactiva.programacion.personas_dni_departamentos;

import com.palmaactiva.programacion.EjercicioDNI;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class App {
    private enum Accion {
        CREAR, CARGAR, GUARDAR, CONSULTAR, SALIR, LISTAR, ERROR
    }

    private static final String[] nombres = new String[] { "Fran", "Jose", "Paco", "Pepe", "Maria", "Marta", "Sandra",
            "Ana", "Lucía", "Sofia", "Roberto", "Álvaro", "Clara" };
    private static final String[] apellidos = new String[] { "Garcia", "Fernández", "Martínez", "Torres", "Hernandez",
            "Martín", "Pons", "González", "Rodríguez", "López", "Sánchez", "Ruíz", "Navarro", "Marí", "Tur", "Pons",
            "Coll" };
    private HashMap<String, Persona> personas;
    private String departamentoActual = "clientes";

    public App() {
        this.personas = new HashMap<>();
    }

    private void crearSocios(int numSocios) {
        Persona socio = null;
        System.out.println("Creando socios:");
        for (int indice = 0; indice < numSocios; indice++) {
            socio = crearSocioAleatorio();
            this.personas.put(socio.getDNI(), socio);
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

    public void cargarPersonas(String departamento) {
        if (departamento == null) {
            departamento = this.departamentoActual;
        }
        this.personas.clear();
        System.out.println("Cargando personas del departamento: " + departamento + "...");
        try (ObjectInputStream lectorObjetos = new ObjectInputStream(
                new FileInputStream("/tmp/" + departamento + ".ser"))) {
            Persona personaArchivo = null;
            while (true) {
                personaArchivo = (Persona) lectorObjetos.readObject();
                this.personas.put(personaArchivo.getDNI(), personaArchivo);
            }

        } catch (IOException ioEx) {
            System.out.println("Se han leído " + this.personas.size() + " personas del departamento: " + departamento);
        } catch (ClassNotFoundException cnfEx) {
            System.out.println("El archivo contiene clases desconocidas!");
        }
    }

    public void guardarPersonas(String departamento) {
        if (departamento == null) {
            departamento = this.departamentoActual;
        }
        System.out.println("Guardando personas del departamento: " + departamento + "...");
        try (ObjectOutputStream escritorObjetos = new ObjectOutputStream(
                new FileOutputStream("/tmp/" + departamento + ".ser"))) {
            for (Persona persona : this.personas.values()) {
                escritorObjetos.writeObject(persona);
            }
            System.out
                    .println("Se han guardado " + this.personas.size() + " personas del departamento: " + departamento);
        } catch (IOException ioEx) {
            System.out.println("Error guardando el departamento " + departamento + ": " + ioEx.getLocalizedMessage());
        }
    }

    public void consultarSocio(String dni) {
        if (personas.containsKey(dni)) {
            Persona persona = personas.get(dni);
            System.out.println(persona);
            System.out.println();
        } else {
            System.out.println("No se encontró el socio.");
        }
    }

    private Persona crearPersona(Scanner teclado) {
        System.out.println("1. Introduce el Nombre:");
        String nombre = teclado.nextLine();

        System.out.println("2. Introduce el Apellido:");
        String apellido = teclado.nextLine();

        System.out.println("3. Introduce el número de DNI: (sin letra)");
        int dni = -1;
        do {
            try {
                dni = teclado.nextInt();
            } catch (Exception ex) {
                System.out.println("Introduce un número de DNI válido.");
            }
        } while (dni < 0);

        System.out.println("4. Introduce la fecha de Nacimiento (DD-MM-YYYY):");
        SimpleDateFormat sDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaNacimiento = null;
        do {
            try {
                fechaNacimiento = sDateFormat.parse(teclado.nextLine());
            } catch (ParseException e) {
                System.out.println("Introduce una fecha válida en formato: DD-MM-YYYY");
            }
        } while (fechaNacimiento == null);

        String dniCompleto = String.valueOf(dni) + EjercicioDNI.getLetraDNI(dni);
        return new Persona(nombre, apellido, dniCompleto, fechaNacimiento);
    }

    public void añadirPersona(Persona persona) {
        this.personas.put(persona.getDNI(), persona);
    }

    private void listarPersonas(String departamento) {
        if (departamento == null) {
            departamento = this.departamentoActual;
        }
        if (departamento != this.departamentoActual) {
            this.cargarPersonas(departamento);
        }
        System.out.println("Personas del departamento " + departamento + " (" + this.personas.size() + "):");
        int posicion = 1;
        for (Persona per : this.personas.values()) {
            System.out.println(posicion + ".-" + per);
            posicion++;
        }
    }

    private static String parametroAccion;

    public static void main(String[] args) {
        App miPrograma = new App();
        miPrograma.cargarPersonas(null);

        Scanner teclado = new Scanner(System.in);
        Accion accion;
        do {
            System.out.println();
            System.out.println("Introduce el DNI a buscar o una acción:");
            System.out.println(" - crear: Crea nuevas personas");
            System.out.println(" - cargar [departamento]: Carga las personas del departamento actual o el proporcionado.");
            System.out.println(" - guardar [departamento]: Guarda las personas del departamento actual o el proporcionado.");
            System.out.println(" - listar [departamento]: Lista las personas del departamento actual o el proporcionado.");
            System.out.println(" - salir: Sale de la aplicación.");

        
            accion = getAccion(teclado);
            switch (accion) {
            case CONSULTAR:
                miPrograma.consultarSocio(parametroAccion.toUpperCase());
                break;
            case GUARDAR:
                miPrograma.guardarPersonas(parametroAccion);
                break;
            case CARGAR:
                miPrograma.cargarPersonas(parametroAccion);
                break;
            case CREAR:
                Persona persona = miPrograma.crearPersona(teclado);
                miPrograma.añadirPersona(persona);
                break;
            case LISTAR:
                miPrograma.listarPersonas(parametroAccion);
                break;
            case ERROR:
                System.out.println("Error, opción no válida.");
                break;
            }

        } while (accion != Accion.SALIR);
        teclado.close();
        System.out.println("\nHasta pronto!\n");
    }

    private static Accion getAccion(Scanner teclado) {
        String linea = teclado.nextLine().trim().toLowerCase();
        String[] partesLinea = linea.split(" ");
        parametroAccion = null;
        switch (partesLinea[0]) {
        case "salir":
            return Accion.SALIR;
        case "guardar":
            if (partesLinea.length > 1) {
                parametroAccion = partesLinea[1];
            }
            return Accion.GUARDAR;
        case "cargar":
            if (partesLinea.length > 1) {
                parametroAccion = partesLinea[1];
            }
            return Accion.CARGAR;
        case "crear":
            return Accion.CREAR;
        case "listar":
            if (partesLinea.length > 1) {
                parametroAccion = partesLinea[1];
            }
            return Accion.LISTAR;
        default:
            if (linea.length() > 7 && linea.length() <= 10) {
                parametroAccion = partesLinea[0];
                return Accion.CONSULTAR;
            }
            return Accion.ERROR;
        }
    }
}
