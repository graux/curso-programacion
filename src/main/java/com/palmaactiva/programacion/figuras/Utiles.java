package com.palmaactiva.programacion.figuras;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

public class Utiles {
    public static Color getColorAleatorio() {
        Random aleatorio = new Random();
        int rojo = aleatorio.nextInt(255);
        int verde = aleatorio.nextInt(255);
        int azul = aleatorio.nextInt(255);

        return new Color(rojo, verde, azul);
    }

    public static float getTamañoAleatorio() {
        Random aleatorio = new Random();
        return aleatorio.nextFloat() * 10;
    }

    public static Point getPuntoAleatorio() {
        Random aleatorio = new Random();
        return new Point(aleatorio.nextInt(800), aleatorio.nextInt(600));
    }
}