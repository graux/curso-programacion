package com.palmaactiva.programacion.ejercicio_paises;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

public class Pais implements Comparable<Pais> {
    private String codigo;
    private String nombre;
    private int poblacion;
    private static final DecimalFormat formatoNumero = (DecimalFormat) NumberFormat.getInstance(new Locale("es", "ES"));

    private static String[] PREFIJOS = new String[] { "United ", "Central ", "Bis", "Hispa", "Celtic", "Rumi", "Ocran",
            "Naof", "Tiust", "Ruman", "Ridon", "Tomin", "Creon", "Dirn", "Escon", "Sulan", "Mor", "Roku", "Obli",
            "Flat", "Arcad", "Belin", "Aldor", "Ambros", "Pacific ", "Calb", "Cord", "Costa", "Edo", "Elbo", "Ere",
            "Eur", "Estoc", "Gile", "Idri", "Isl", "Ix", "Kaza", "Kum" };
    private static String[] SUFIJOS = new String[] { "lands", "ibia", "land", "enta", "istan", "opa", "menia", "aman",
            "ean", "ilia", "upia", " Republic", " States", " Kingdom", "tria", "dor", " United", "anda", "vion", "osha",
            "davia", "ia", "ria", "ran", "garia", "chistan", "tan", "anda" };
    private static HashMap<String, Pais> paisesAleatorios = new HashMap<>();

    public Pais(String codigo, String nombre, int poblacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.poblacion = poblacion;
    }

    public static Pais crearAleatorio() {
        Random aleatorio = new Random();
        do {
            String pre = PREFIJOS[aleatorio.nextInt(PREFIJOS.length)];
            String suf = SUFIJOS[aleatorio.nextInt(SUFIJOS.length)];
            String codigo = (String.valueOf(pre.charAt(0)) + suf.trim().charAt(0)).toLowerCase();
            if (!paisesAleatorios.containsKey(codigo)) {
                Pais nuevoPais = new Pais(codigo, pre + suf, 100000 + aleatorio.nextInt(100000000));
                paisesAleatorios.put(codigo, nuevoPais);
                return nuevoPais;
            }
        } while (true);
    }

    @Override
    public String toString() {
        return "[" + this.codigo + "] " + String.format("%-12s", this.nombre)
                + String.format("%9s", formatoNumero.format(this.poblacion));
    }

    public int getPoblacion() {
        return this.poblacion;
    }

	public String getCodigo() {
		return this.codigo;
	}

    @Override
    public int compareTo(Pais otroPais) {
        return this.poblacion - otroPais.poblacion;
    }
}