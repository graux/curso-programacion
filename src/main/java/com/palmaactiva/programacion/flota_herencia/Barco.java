package com.palmaactiva.programacion.flota_herencia;

import java.util.Random;

public class Barco implements Posicionable {
    private Celda[] celdas;

    public Barco(int numCeldas) {
        // Inicializamos un array de celdas de la longitud demandada
        this.celdas = new Celda[numCeldas];
        // El barco podrá empezar desde 0 hasta 9 (en filas o columnas)...
        int filaMax = Flota.NUM_FILAS;
        int colMax = Flota.NUM_COLUMNAS;

        // Creamos un nuevo objeto de valores aleatorios
        Random aleatorio = new Random();
        // Creamos un boolean aleatorio true/false para ver si es horizontal o vertical
        boolean esHorizontal = aleatorio.nextBoolean();

        if (esHorizontal) {
            // Si es horizontal
            // La posición máxima de columna donde puede empezar nuestro barco es 9 - el
            // tamaño del barco.
            colMax = Flota.NUM_COLUMNAS - numCeldas;
        } else {
            // Si es vertical
            // La posición máxima de fila donde puede empezar nuestro barco es 9 - el tamaño
            // del barco.
            filaMax = Flota.NUM_FILAS - numCeldas;
        }

        // Conseguimos un número de fila aleatorio de 0 al máximo
        int filaInicial = aleatorio.nextInt(filaMax);
        // Conseguimos un número de columna aleatorio de 0 al máximo
        int columnaInicial = aleatorio.nextInt(colMax);

        // Variable auxiliar para mis celdas
        Celda nuevaCelda = null;
        // Bucle para crear tantas celdas como me han pedido para este barco
        for (int indice = 0; indice < numCeldas; indice++) {
            if (esHorizontal) {
                // Si es horizontal, siempre tiene la misma fila, y la columna se va
                // incrementando
                nuevaCelda = new Celda(filaInicial, columnaInicial + indice);
            } else {
                // Si es vertical, siempre tiene la misma columna, y la fila se va incrementando
                nuevaCelda = new Celda(filaInicial + indice, columnaInicial);
            }
            // Guardamos la nueva celda en el barco.
            this.celdas[indice] = nuevaCelda;
        }
    }

    public boolean seSolapaCon(Barco nuevoBarco) {
        // Para ver si dos barcos se solapa, tenemos que mirar si cualquiera de sus
        // celdas están en la misma posición
        // Para cada una de las celdas de este barco
        for (Celda miCelda : this.celdas) {
            // Miramos cada una de las celdas del otro barco
            for (Celda otraCelda : nuevoBarco.celdas) {
                // Si son la "misma" celda (hemos implementado equals para que compruebe si
                // están en la misma posición)
                if (miCelda.equals(otraCelda)) {
                    // Devolvemos true (todos los bucles terminan aquí)
                    return true;
                }
            }
        }
        // Quierde decir que hemos recorrido todas las celdas y ninguna se solapa.
        return false;
    }

    public boolean estaHundido() {
        // Comprobamos si tiene TODAS las celdas hundidas
        for (Celda celda : this.celdas) {
            if (!celda.estaHundida()) {
                // Si una celda NO está hundida, significa que el barco no está hundido
                return false;
            }
        }
        // Si llegamos aquí es que todas las celdas estaban hundidas, ergo el barco
        // también lo está.
        return true;
    }

    public boolean disparar(Disparo disparo) {
        // Si disparas a un barco, disparas a sus celdas
        for (Celda celda : this.celdas) {
            // Si hay impacto en una celda
            if (celda.disparar(disparo)) {
                // Devolvemos true: impacto
                return true;
            }
        }
        // No ha habido impacto de este disparo con ninguna de las celdas
        return false;
    }

    @Override
    public String getStringCelda(Posicion pos) {
        // Conseguimos la celda de esa posición
        Celda celda = this.getCeldaPosicion(pos);
        if (celda != null) {
            // Si se encuentra la celda, pintamos una "X" si está hundida o " " si no.
            return celda.estaHundida() ? "X" : " ";
        } else {
            // Si no hay celda, vacío (esto no debería darse nunca)
            return " ";
        }
    }

    protected Celda getCeldaPosicion(Posicion pos) {
        // Comprobamos para cada una de las celdas del barco
        for (Celda miCelda : this.celdas) {
            // Si la celda está en la posición de los parámetros
            if (miCelda.seSolapaCon(pos)) {
                // Se devuelve la celda
                return miCelda;
            }
        }
        // No lo hemos encontrado, entonces false
        return null;
    }

    public boolean seSolapaCon(Posicion pos) {
        Celda celda = this.getCeldaPosicion(pos);
        return celda != null;
    }

    @Override
    public Posicion[] getPosiciones() {
        // Celdas es un array de Celdas, cada celda extiende posición, por tanto un array de celdas es un array de posiciones.
        return this.celdas;
    }
}