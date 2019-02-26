package com.palmaactiva.programacion.flota;

import java.util.ArrayList;
import java.util.Scanner;

public class Flota {
    private ArrayList<Barco> losBarcos;
    private ArrayList<Disparo> disparosAgua;
    private int disparosDisponibles = 30;
    public static final int NUM_FILAS = 9;
    public static final int NUM_COLUMNAS = 9;

    public Flota() {
        this.losBarcos = new ArrayList<Barco>();
        this.disparosAgua = new ArrayList<Disparo>();
        this.crearBarco(5);
        this.crearBarco(4);
        this.crearBarco(2);
        this.crearBarco(2);
        this.crearBarco(2);
    }

    private void crearBarco(int numCeldas) {
        Barco nuevoBarco = null;
        do {
            nuevoBarco = new Barco(numCeldas);
            for (Barco barco : this.losBarcos) {
                if (barco.seSolapaCon(nuevoBarco)) {
                    nuevoBarco = null;
                    break;
                }
            }
        } while (nuevoBarco == null);

        this.losBarcos.add(nuevoBarco);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" |a|b|c|d|e|f|g|h|i|\n");
        sb.append("--------------------\n");
        boolean hayBarco = false;
        boolean hayImpacto = false;
        boolean hayAgua = false;
        for (int indiceFila = 0; indiceFila < NUM_FILAS; indiceFila++) {
            sb.append((indiceFila + 1));
            for (int indiceColumna = 0; indiceColumna < NUM_COLUMNAS; indiceColumna++) {
                sb.append("|");
                hayBarco = false;
                hayAgua = false;
                hayImpacto = false;
                for (Disparo disparo : this.disparosAgua) {
                    if (disparo.sobrePosicion(indiceFila, indiceColumna)) {
                        hayAgua = true;
                    }
                }
                if (!hayAgua) {
                    for (Barco barco : this.losBarcos) {
                        if (barco.sobrePosicion(indiceFila, indiceColumna)) {
                            hayBarco = true;
                            hayImpacto = barco.posicionHundida(indiceFila, indiceColumna);
                            break;
                        }
                    }
                }
                if (hayAgua) {
                    sb.append("~");
                } else if (hayImpacto) {
                    sb.append("X");
                } else {
                    sb.append(" ");
                }
            }
            sb.append("|\n");
        }
        sb.append("--------------------\n");
        sb.append("Disparos Restantes: " + this.disparosDisponibles + "\n");
        return sb.toString();
    }

    public boolean puedeContinuar() {
        if (this.disparosDisponibles > 0) {
            for (Barco barco : this.losBarcos) {
                if (!barco.estaHundido()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean esVictoria() {
        for (Barco barco : this.losBarcos) {
            if (!barco.estaHundido()) {
                return false;
            }
        }
        return true;
    }

    public void disparar(Disparo disparo) {
        this.disparosDisponibles--;
        for (Barco barco : this.losBarcos) {
            if (barco.disparar(disparo)) {
                return;
            }
        }
        this.disparosAgua.add(disparo);
    }

    public static void main(String[] args) {
        Flota juegoFlota = new Flota();
        Scanner teclado = new Scanner(System.in);
        Disparo disparo = null;
        do {
            System.out.println(juegoFlota);
            do {
                System.out.println("¿A qué posición quieres disparar?");
                disparo = new Disparo(teclado.nextLine());
            } while (!disparo.esValido());
            juegoFlota.disparar(disparo);
        } while (juegoFlota.puedeContinuar());

        if (juegoFlota.esVictoria()) {
            System.out.println("Felicidades has aniquilado la flota enemiga!");
        } else {
            System.out.println("GAME OVER, no te quedan disparos!");
        }

        teclado.close();
    }
}