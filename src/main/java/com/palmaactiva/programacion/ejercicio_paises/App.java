package com.palmaactiva.programacion.ejercicio_paises;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class App implements Comparator<Pais> {
    private Pais[] paisesEjercicio;
    private static final DecimalFormat formatoNumero = (DecimalFormat) NumberFormat.getInstance(new Locale("es", "ES"));

    public App(int numPaises) {
        paisesEjercicio = crearPaises(numPaises);
    }

    private String getCodigoPaisAleatorio() {
        Random aleatorio = new Random();
        return this.paisesEjercicio[aleatorio.nextInt(this.paisesEjercicio.length)].getCodigo();
    }

    private Pais[] crearPaises(int numPaises) {
        Pais[] paises = new Pais[numPaises];
        for (int indice = 0; indice < paises.length; indice++) {
            paises[indice] = Pais.crearAleatorio();
            // System.out.println(paises[indice]);
            if (indice % 50 == 0 && indice > 0) {
                System.out.println((indice) + " paises creados.");
            }
        }
        System.out.println(paises.length + " paises creados.");

        return paises;
    }

    private long ordernarArray() {
        long tStart = System.nanoTime();
        Pais[] paises = paisesEjercicio.clone();
        Arrays.sort(paises, this);
        System.out.println("Array Min/Max: " + paises[0] + " / " + paises[paises.length - 1]);
        long tEnd = System.nanoTime();
        return tEnd - tStart;
    }

    private long ordernarTree() {
        TreeSet<Pais> tree = new TreeSet<>(this);
        long tStart = System.nanoTime();
        tree.addAll(Arrays.asList(this.paisesEjercicio));
        System.out.println("Tree Min/Max: " + tree.first() + " / " + tree.last());
        long tEnd = System.nanoTime();
        return tEnd - tStart;
    }

    private long ordernarArrayList() {
        ArrayList<Pais> arrList = new ArrayList<>();
        long tStart = System.nanoTime();
        arrList.addAll(Arrays.asList(this.paisesEjercicio));
        arrList.sort(this);
        System.out.println("ArrayList Min/Max: " + arrList.get(0) + " / " + arrList.get(arrList.size() - 1));
        long tEnd = System.nanoTime();
        return tEnd - tStart;
    }

    private long ordernarLinkedList() {
        LinkedList<Pais> lnkList = new LinkedList<>();
        long tStart = System.nanoTime();
        lnkList.addAll(Arrays.asList(this.paisesEjercicio));
        lnkList.sort(this);
        System.out.println("LinkedList Min/Max: " + lnkList.get(0) + " / " + lnkList.get(lnkList.size() - 1));
        long tEnd = System.nanoTime();
        return tEnd - tStart;
    }

    private long busquedaArray(String codigo) {
        long tStart = System.nanoTime();
        for (Pais pais : this.paisesEjercicio) {
            if (pais.getCodigo().equals(codigo)) {
                break;
            }
        }
        long tEnd = System.nanoTime();
        return tEnd - tStart;
    }

    private long busquedaArrayList(ArrayList<Pais> arrList, String codigo) {
        long tStart = System.nanoTime();
        for (Pais pais : arrList) {
            if (pais.getCodigo().equals(codigo)) {
                break;
            }
        }
        long tEnd = System.nanoTime();
        return tEnd - tStart;
    }

    private long busquedaLinkedList(LinkedList<Pais> lnkList, String codigo) {
        long tStart = System.nanoTime();
        for (Pais pais : lnkList) {
            if (pais.getCodigo().equals(codigo)) {
                break;
            }
        }
        long tEnd = System.nanoTime();
        return tEnd - tStart;
    }

    private long busquedaTreeSet(TreeSet<Pais> treeSet, String codigo) {
        long tStart = System.nanoTime();
        for (Pais pais : treeSet) {
            if (pais.getCodigo().equals(codigo)) {
                break;
            }
        }
        long tEnd = System.nanoTime();
        return tEnd - tStart;
    }

    public long busquedasArray(App miApp, int numBusquedas) {
        long tiempos = 0;
        String codigoPais = null;
        for (int indice = 0; indice < numBusquedas; indice++) {
            codigoPais = miApp.getCodigoPaisAleatorio();
            tiempos += miApp.busquedaArray(codigoPais);
        }
        return tiempos / numBusquedas;
    }

    public long busquedasArrayList(App miApp, int numBusquedas) {
        long tiempos = 0;
        String codigoPais = null;
        ArrayList<Pais> arrList = new ArrayList<>();
        arrList.addAll(Arrays.asList(this.paisesEjercicio));
        for (int indice = 0; indice < numBusquedas; indice++) {
            codigoPais = miApp.getCodigoPaisAleatorio();
            tiempos += miApp.busquedaArrayList(arrList, codigoPais);
        }
        return tiempos / numBusquedas;
    }

    public long busquedasLinkedList(App miApp, int numBusquedas) {
        long tiempos = 0;
        String codigoPais = null;
        LinkedList<Pais> lnkList = new LinkedList<>();
        lnkList.addAll(Arrays.asList(this.paisesEjercicio));
        for (int indice = 0; indice < numBusquedas; indice++) {
            codigoPais = miApp.getCodigoPaisAleatorio();
            tiempos += miApp.busquedaLinkedList(lnkList, codigoPais);
        }
        return tiempos / numBusquedas;
    }

    public long busquedasTreeSet(App miApp, int numBusquedas) {
        long tiempos = 0;
        String codigoPais = null;
        TreeSet<Pais> treeSet = new TreeSet<>();
        treeSet.addAll(Arrays.asList(this.paisesEjercicio));
        for (int indice = 0; indice < numBusquedas; indice++) {
            codigoPais = miApp.getCodigoPaisAleatorio();
            tiempos += miApp.busquedaTreeSet(treeSet, codigoPais);
        }
        return tiempos / numBusquedas;
    }

    public long busquedasTreeMap(App miApp, int numBusquedas) {
        long tiempos = 0;
        String codigoPais = null;
        TreeMap<String, Pais> treeMap = new TreeMap<>();
        for (Pais pais : this.paisesEjercicio) {
            treeMap.put(pais.getCodigo(), pais);
        }
        long tStart, tEnd;
        for (int indice = 0; indice < numBusquedas; indice++) {
            codigoPais = miApp.getCodigoPaisAleatorio();
            tStart = System.nanoTime();
            treeMap.get(codigoPais);
            tEnd = System.nanoTime();
            tiempos += tEnd - tStart;
        }
        return tiempos / numBusquedas;
    }

    public long busquedasHashMap(App miApp, int numBusquedas) {
        long tiempos = 0;
        String codigoPais = null;
        HashMap<String, Pais> hashMap = new HashMap<>();
        for (Pais pais : this.paisesEjercicio) {
            hashMap.put(pais.getCodigo(), pais);
        }
        long tStart, tEnd;
        for (int indice = 0; indice < numBusquedas; indice++) {
            codigoPais = miApp.getCodigoPaisAleatorio();
            tStart = System.nanoTime();
            hashMap.get(codigoPais);
            tEnd = System.nanoTime();
            tiempos += tEnd - tStart;
        }
        return tiempos / numBusquedas;
    }

    private static String formatearTiempo(long tiempo) {
        return String.format("%10s", formatoNumero.format(tiempo));
    }

    public static void main(String[] args) {
        App miApp = new App(250);
        System.out.println("\n\n> Tiempos de Ordenación <");
        System.out.println("*** Array ***");
        System.out.println("Tiempo: " + formatearTiempo(miApp.ordernarArray()) + "ns");
        System.out.println("*************\n");
        System.out.println("*** LinkedList ***");
        System.out.println("Tiempo: " + formatearTiempo(miApp.ordernarLinkedList()) + "ns");
        System.out.println("*************\n");
        System.out.println("*** ArrayList ***");
        System.out.println("Tiempo: " + formatearTiempo(miApp.ordernarArrayList()) + "ns");
        System.out.println("*************\n");
        System.out.println("*** Tree ***");
        System.out.println("Tiempo: " + formatearTiempo(miApp.ordernarTree()) + "ns");
        System.out.println("*************\n");
        System.out.println(
                " ==> Tiempos de ordenación para ordenar una sola vez, normalmente los datos de los programas van cambiando y tendrán que ordenar constantemente\n\n");

        int numBusquedas = 100;
        System.out.println("> Tiempos de Búsqueda (Media " + numBusquedas + " Búsquedas) <");
        System.out.println("*** Array ***");
        System.out.println("Tiempo: " + formatearTiempo(miApp.busquedasArray(miApp, numBusquedas)) + "ns");
        System.out.println("*************\n");
        System.out.println("*** ArrayList ***");
        System.out.println("Tiempo: " + formatearTiempo(miApp.busquedasArrayList(miApp, numBusquedas)) + "ns");
        System.out.println("*************\n");
        System.out.println("*** LinkedList ***");
        System.out.println("Tiempo: " + formatearTiempo(miApp.busquedasLinkedList(miApp, numBusquedas)) + "ns");
        System.out.println("*************\n");
        System.out.println("*** TreeSet ***");
        System.out.println("Tiempo: " + formatearTiempo(miApp.busquedasTreeSet(miApp, numBusquedas)) + "ns");
        System.out.println("*************\n");
        System.out.println("*** TreeMap ***");
        System.out.println("Tiempo: " + formatearTiempo(miApp.busquedasTreeMap(miApp, numBusquedas)) + "ns");
        System.out.println("*************\n");
        System.out.println("*** HashMap ***");
        System.out.println("Tiempo: " + formatearTiempo(miApp.busquedasHashMap(miApp, numBusquedas)) + "ns");
        System.out.println("*************\n");
    }

    @Override
    public int compare(Pais pais1, Pais pais2) {
        return pais1.getPoblacion() - pais2.getPoblacion();
    }
}