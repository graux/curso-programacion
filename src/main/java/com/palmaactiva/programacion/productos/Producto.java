package com.palmaactiva.programacion.productos;

public class Producto {
    private String nombre;
    private float precio;
    private float peso;
    private int unidades;

    public Producto(String nombre, float precio, float peso) {
        this.nombre = nombre;
        this.precio = precio;
        this.peso = peso;
        this.unidades = 1;
    }

    public void anyadirCantidad(int cantidad) {
        this.unidades += cantidad;
    }

    public void reducirCantidad(int cantidad) {
        this.unidades -= cantidad;
    }

    public float calcularPrecio() {
        return this.precio * this.unidades;
    }

    public float calcularPeso() {
        float pesoTotal = 0;
        if (this.unidades > 0) {
            pesoTotal = this.peso;

            if (this.unidades > 1) {
                int otrasUnidades = this.unidades - 1;
                pesoTotal += otrasUnidades * (this.peso * 0.8);
            }
        }
        return pesoTotal;
    }

    public int getUnidades(){
        return this.unidades;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-23s", this.nombre));
        sb.append(String.format("%12.2f", this.precio));
        sb.append(String.format("%12.2f", this.peso));
        sb.append(String.format("%12d", this.unidades));
        sb.append(String.format("%17.2f", this.calcularPrecio()));
        sb.append(String.format("%17.2f", this.calcularPeso()));

        return sb.toString();
    }
}