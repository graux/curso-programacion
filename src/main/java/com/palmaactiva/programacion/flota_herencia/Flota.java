package com.palmaactiva.programacion.flota_herencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Flota {
    private List<Posicionable> objetosTablero;
    private int disparosDisponibles = 30;
    public static final int NUM_FILAS = 9;
    public static final int NUM_COLUMNAS = 9;

    public Flota() {
        // Iniciamos las colecciones de barcos y de disparos al agua
        this.objetosTablero = new ArrayList<Posicionable>();
        // Cremamos los barcos con un método
        this.crearBarco(5);
        this.crearBarco(4);
        this.crearBarco(2);
        this.crearBarco(2);
        this.crearBarco(2);
    }

    private void crearBarco(int numCeldas) {
        Barco nuevoBarco = null;
        do {
            // Creamos un barco nuevo
            nuevoBarco = new Barco(numCeldas);
            // Y para los barcos creados anteriormente...
            for (Posicionable barco : this.objetosTablero) {
                // Comprobamos si se solapan los dos barcos (el nuevo con el de bucle)
                if (barco.seSolapaCon(nuevoBarco)) {
                    // Si se solapan, descartamos el nuevo barco usando null y salimos del bucle
                    // (este barco no sirve, necesitamos otro)
                    nuevoBarco = null;
                    break;
                }
            }
            // Repetiremos esto mientras que nuevoBarco es null, es decir, mientras que el
            // nuevoBarco se solape con otro.
        } while (nuevoBarco == null);

        // Añado el nuevo barco a mi lista de barcos.
        this.objetosTablero.add(nuevoBarco);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Mostramos los nombres de las columnas y una línea
        sb.append(" |a|b|c|d|e|f|g|h|i|\n");
        sb.append("--------------------\n");
        // Declaramos algunas variables para ver el estado de cada celda
        Posicion posicionActual;
        Posicionable posicionAPintar;
        // Recorremos todas las filas
        for (int indiceFila = 0; indiceFila < NUM_FILAS; indiceFila++) {
            // Mostramos el número de la fila: 1, 2, 3, 4
            sb.append((indiceFila + 1));
            // Recorremos las columnas de la fila actual
            for (int indiceColumna = 0; indiceColumna < NUM_COLUMNAS; indiceColumna++) {
                // Empezamos con una barra
                sb.append("|");
                // Crearmos una posición para la posición actual
                posicionActual = new Posicion(indiceFila, indiceColumna);
                // Por defecto pintaremos la posición, que es el texto vacío " ".
                posicionAPintar = posicionActual;
                // Buscamos en los objetos del tablero
                for (Posicionable objetoTablero : this.objetosTablero) {
                    // Si el objeto está en la posición actual.
                    if (objetoTablero.seSolapaCon(posicionActual)) {
                        // El objeto a pintar será el objeto del tablero en la posición actual
                        posicionAPintar = objetoTablero;
                        // Salimos del bucle porque ya hemos encontrado el objeto en la posición actual
                        break;
                    }
                }
                // Pintamos la posición a pintar
                sb.append(posicionAPintar.getStringCelda(posicionActual));
            }
            // Ponemos una barra al final y cambiamos de línea \n
            sb.append("|\n");
        }
        // Dibujamos una línea final y la cantidad de disparos restantes.
        sb.append("--------------------\n");
        sb.append("Disparos Restantes: " + this.disparosDisponibles + "\n");
        // Devolvemos la String resultante.
        return sb.toString();
    }

    public boolean puedeContinuar() {
        // Quedan disparos?
        if (this.disparosDisponibles > 0) {
            // Miramos todos los objetos del tablero
            Barco barco;
            for (Posicionable objTablero : this.objetosTablero) {
                // Buscamos sólo barcos
                if (objTablero instanceof Barco) {
                    // Si convertimos el objeto tablero en barco ahora que sabemos que es un barco
                    barco = (Barco) objTablero;
                    // si no está hundido...
                    if (!barco.estaHundido()) {
                        // Se puede continuar
                        return true;
                    }
                }
            }
        }
        // Si llega hasta aquí es que o no quedan barcos o no quedan disparos.
        return false;
    }

    public boolean esVictoria() {
        Barco barco;
        // Buscamos en todos los objetos
        for (Posicionable objTablero : this.objetosTablero) {
            // Solo en los barcos, no queremos los disparos
            if (objTablero instanceof Barco) {
                // Convertimos el objeto tablero en barco ahora que sabemos que es un barco
                barco = (Barco) objTablero;
                // Si no está hundido...
                if (!barco.estaHundido()) {
                    // El juego todavía no ha terminado
                    return false;
                }
            }
        }
        // Si todos los barcos se han hundido -> Victoria
        return true;
    }

    public void disparar(Disparo disparo) {
        // Decrementamos los disparos disponibles.
        this.disparosDisponibles--;
        for (Posicionable objTablero : this.objetosTablero) {
            // Buscamos el objeto que se encuentra en la posición
            // Y si es Barco...
            if (objTablero.seSolapaCon(disparo) && objTablero instanceof Barco) {
                // Disparamos al barco
                ((Barco) objTablero).disparar(disparo);
                return;
            }
        }
        // Si no se encuentra un barco, entonces se guarda el disparo como posición de
        // agua.
        this.objetosTablero.add(disparo);
    }

    public static void main(String[] args) {
        Flota juegoFlota = new Flota();
        Scanner teclado = new Scanner(System.in);
        Disparo disparo = null;
        do {
            // Mostramos el tablero/estado del juego
            System.out.println(juegoFlota);
            disparo = leerDisparo(teclado);
            // Se efectua el disparo
            juegoFlota.disparar(disparo);
            // Se continua mientras que el juego no termine
        } while (juegoFlota.puedeContinuar());

        if (juegoFlota.esVictoria()) {
            // Si se gana
            System.out.println("Felicidades has aniquilado la flota enemiga!");
        } else {
            // Si se pierde
            System.out.println("GAME OVER, no te quedan disparos!");
        }

        // Cerramos el teclado porque ya no lo vamos a usar más.
        teclado.close();
    }

    private static Disparo leerDisparo(Scanner teclado) {
        System.out.println("¿A qué posición quieres disparar?");
        Disparo disparo = null;
        try {
            // Utilizamos un método estático para crear disparos
            disparo = Disparo.parseDisparo(teclado.nextLine());
        } catch (NumberFormatException numberExcep) {
            // Si el disparo tiene error, mostramos el error
            System.out.println("Error: Introduce un número de Fila válido");
            // Y volvemos a intentarlo
            return leerDisparo(teclado);
        } catch (DisparoFormatException disparoExcep) {
            // Si el disparo tiene error, mostramos el error
            System.out.println("Error: La celda no existe");
            // Y volvemos a intentarlo
            return leerDisparo(teclado);
        }
        return disparo;
    }
}