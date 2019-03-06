package com.palmaactiva.programacion.figuras;

import java.awt.Color;
import java.awt.Point;

public abstract class FiguraConBorde implements Figura {
    protected Color colorBorde;
    protected float tamañoBorde;
    protected Point[] puntos;
}