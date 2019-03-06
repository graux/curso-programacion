package com.palmaactiva.programacion.figuras;

import java.awt.Color;

public abstract class FiguraConArea extends FiguraConBorde {
    protected Color colorRelleno;
    public abstract float getArea();
}