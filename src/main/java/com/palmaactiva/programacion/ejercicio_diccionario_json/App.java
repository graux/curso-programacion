package com.palmaactiva.programacion.ejercicio_diccionario_json;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.palmaactiva.programacion.ejercicio_diccionario_json.Diccionario.Idioma;

public class App {
    private enum AcciónTeclado {
        CONSULTAR, INSERTAR, ERROR, GUARDAR, SALIR
    }

    private Diccionario miDic;

    public App() {
        miDic = new Diccionario();
    }

    public void iniciarAplicación() {
        System.out.println("=> El diccionario contiene " + miDic.getNumTerminos() + " términos.\n");
        Scanner teclado = new Scanner(System.in);
        AcciónTeclado acción = null;
        do {
            System.out.println(
                    "¿Qué deseas realizar, 'insertar' o 'consultar' un término, o 'guardar' el diccionario? (o 'salir')");
            acción = getAcciónTeclado(teclado);
            switch (acción) {
            case INSERTAR:
                this.insertarTermino(teclado);
                break;
            case CONSULTAR:
                this.consultarTérmino(teclado);
                break;
            case GUARDAR:
                this.guardarDiccionario();
                break;
            }
        } while (acción != AcciónTeclado.SALIR);
        teclado.close();
        System.out.println("\nHasta la próxima!");
    }

    private void guardarDiccionario() {
        Gson gson = new GsonBuilder().create();
        Traduccion[] traducciones = this.miDic.getTraducciones();
        String rutaArchivo = "/tmp/diccionario.json";
        try (OutputStreamWriter escritorDiccionario = new OutputStreamWriter(new FileOutputStream(rutaArchivo))) {
            gson.toJson(traducciones, escritorDiccionario);
            System.out.println(
                    "Diccionario con " + traducciones.length + " entradas guardado con éxito en: " + rutaArchivo);
        } catch (Exception ex) {
            System.out.println("No se pudo guardar el diccionario: " + ex.getLocalizedMessage());
        }
    }

    private void cargarDiccionario() {
        Gson gson = new GsonBuilder().create();
        String rutaArchivo = "/tmp/diccionario.json";
        try (InputStreamReader lectorDiccionario = new InputStreamReader(new FileInputStream(rutaArchivo))) {
            Traduccion[] traducciones = gson.fromJson(lectorDiccionario, Traduccion[].class);
            System.out.println("Diccionario cargado con éxito. Se leyeron un total de " + traducciones.length
                    + " entradas desde: " + rutaArchivo);
            for (Traduccion traduccion : traducciones) {
                miDic.añadirTérmino(traduccion);
            }
        } catch (Exception ex) {
            System.out.println("No se pudo cargar el diccionario: " + ex.getLocalizedMessage());
        }
    }

    private void insertarTermino(Scanner teclado) {
        System.out.println("¿En qué idioma está el término a insertar? (en/es)");
        Idioma idioma = getIdioma(teclado);
        System.out.println("Escribe el término:");
        String termino = teclado.nextLine().trim();
        System.out.println("Escribe la traducción:");
        String traduccion = teclado.nextLine().trim();

        String terminoEspañol, terminoIngles = null;
        if (idioma == Idioma.ESPAÑOL) {
            terminoEspañol = termino;
            terminoIngles = traduccion;
        } else {
            terminoEspañol = traduccion;
            terminoIngles = termino;
        }

        this.miDic.añadirTérmino(new Traduccion(terminoEspañol, terminoIngles));
        System.out.println("Término insertado en el diccionario.");
        System.out.println();
    }

    private void consultarTérmino(Scanner teclado) {
        System.out.println("¿En qué idioma está el término? (en/es)");
        Idioma idioma = getIdioma(teclado);
        System.out.println("Escribe el término a consultar:");
        String termino = teclado.nextLine().trim().toLowerCase();
        String traduccion = this.miDic.traducir(idioma, termino);
        if (traduccion == null) {
            System.out.println("El término '" + termino + "' no se encuentra en el diccionario.");
        } else {
            System.out.println("La traducción de '" + termino + "' es '" + traduccion + "'");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        App miDicApp = new App();
        // miDicApp.poblarDiccionario();
        miDicApp.cargarDiccionario();
        miDicApp.iniciarAplicación();
    }

    private AcciónTeclado getAcciónTeclado(Scanner teclado) {
        String linea = teclado.nextLine().trim().toLowerCase();
        switch (linea) {
        case "insertar":
            return AcciónTeclado.INSERTAR;
        case "consultar":
            return AcciónTeclado.CONSULTAR;
        case "salir":
            return AcciónTeclado.SALIR;
        case "guardar":
            return AcciónTeclado.GUARDAR;
        default:
            System.out.println(
                    "La accion no es válida, por favor introduce: 'insertar', 'consultar', 'guardar' o 'salir'");
            return getAcciónTeclado(teclado);
        }
    }

    private Idioma getIdioma(Scanner teclado) {
        String linea = teclado.nextLine().trim().toLowerCase();
        switch (linea) {
        case "es":
            return Idioma.ESPAÑOL;
        case "en":
            return Idioma.INGLÉS;
        default:
            System.out.println("Idioma no válido, escribe simplemente 'es' Español o 'en' para Inglés...");
            return getIdioma(teclado);
        }
    }

    private void poblarDiccionario() {
        this.miDic.añadirTérmino(new Traduccion("abeja", "bee"));
        this.miDic.añadirTérmino(new Traduccion("águila", "eagle"));
        this.miDic.añadirTérmino(new Traduccion("araña", "spider"));
        this.miDic.añadirTérmino(new Traduccion("avispa", "wasp"));
        this.miDic.añadirTérmino(new Traduccion("ballena", "whale"));
        this.miDic.añadirTérmino(new Traduccion("bisonte", "bison"));
        this.miDic.añadirTérmino(new Traduccion("búfalo", "buffalo"));
        this.miDic.añadirTérmino(new Traduccion("burro", "donkey"));
        this.miDic.añadirTérmino(new Traduccion("caballo", "horse"));
        this.miDic.añadirTérmino(new Traduccion("camello", "camel"));
        this.miDic.añadirTérmino(new Traduccion("canario", "canary"));
        this.miDic.añadirTérmino(new Traduccion("cangrejo", "crab"));
        this.miDic.añadirTérmino(new Traduccion("canguro", "kangaroo"));
        this.miDic.añadirTérmino(new Traduccion("caracol", "snail"));
        this.miDic.añadirTérmino(new Traduccion("cebra", "zebra"));
        this.miDic.añadirTérmino(new Traduccion("cerdo", "pig"));
        this.miDic.añadirTérmino(new Traduccion("chimpancé", "chimpanzee"));
        this.miDic.añadirTérmino(new Traduccion("ciervo", "deer"));
        this.miDic.añadirTérmino(new Traduccion("cisne", "swan"));
        this.miDic.añadirTérmino(new Traduccion("cocodrilo", "crocodile"));
        this.miDic.añadirTérmino(new Traduccion("elefante", "elephant"));
        this.miDic.añadirTérmino(new Traduccion("escarabajo", "beetle"));
        this.miDic.añadirTérmino(new Traduccion("escorpión", "scorpion"));
        this.miDic.añadirTérmino(new Traduccion("foca", "seal"));
        this.miDic.añadirTérmino(new Traduccion("gallina", "hen"));
        this.miDic.añadirTérmino(new Traduccion("gallo", "cock , rooster"));
        this.miDic.añadirTérmino(new Traduccion("gato", "cat"));
        this.miDic.añadirTérmino(new Traduccion("golondrina", "swallow"));
        this.miDic.añadirTérmino(new Traduccion("hipopótamo", "hippopotamus"));
        this.miDic.añadirTérmino(new Traduccion("hormiga", "ant"));
        this.miDic.añadirTérmino(new Traduccion("jabalí", "wild boar"));
        this.miDic.añadirTérmino(new Traduccion("jirafa", "giraffe"));
        this.miDic.añadirTérmino(new Traduccion("león", "lion"));
        this.miDic.añadirTérmino(new Traduccion("loro", "parrot"));
        this.miDic.añadirTérmino(new Traduccion("mosca", "fly"));
        this.miDic.añadirTérmino(new Traduccion("mosquito", "mosquito"));
        this.miDic.añadirTérmino(new Traduccion("oso", "bear"));
        this.miDic.añadirTérmino(new Traduccion("oveja", "sheep"));
        this.miDic.añadirTérmino(new Traduccion("perdiz", "partridge"));
        this.miDic.añadirTérmino(new Traduccion("perro", "dog"));
        this.miDic.añadirTérmino(new Traduccion("pingüino", "penguin "));
        this.miDic.añadirTérmino(new Traduccion("pollo", "chick"));
        this.miDic.añadirTérmino(new Traduccion("saltamontes", "grasshoppers"));
        this.miDic.añadirTérmino(new Traduccion("serpiente", "snake"));
        this.miDic.añadirTérmino(new Traduccion("tigre", "tiger"));
        this.miDic.añadirTérmino(new Traduccion("topo", "mole"));
        this.miDic.añadirTérmino(new Traduccion("toro", "bull"));
        this.miDic.añadirTérmino(new Traduccion("tortuga", "turtle"));
        this.miDic.añadirTérmino(new Traduccion("vaca", "cow"));
        this.miDic.añadirTérmino(new Traduccion("zorro", " fox"));
    }
}