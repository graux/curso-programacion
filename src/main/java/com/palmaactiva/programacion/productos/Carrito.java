package com.palmaactiva.programacion.productos;

import java.util.ArrayList;

public class Carrito {
    ArrayList<Producto> productos;

    public Carrito() {
        this.productos = new ArrayList<>();
    }

    public void addProducto(Producto producto) {
        this.productos.add(producto);
    }

    public void vaciar() {
        this.productos.clear();
    }

    public int getNumUnidades() {
        int totalUnidades = 0;
        for (Producto prod : this.productos) {
            totalUnidades += prod.getUnidades();
        }
        return totalUnidades;
    }

    public float getPrecioTotal() {
        float precioTotal = 0;
        for (Producto prod : this.productos) {
            precioTotal += prod.calcularPrecio();
        }
        return precioTotal;
    }

    public float getTotalPeso() {
        float pesoTotal = 0;
        for (Producto prod : this.productos) {
            pesoTotal += prod.calcularPeso();
        }
        return pesoTotal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String line = "";
        for (int index = 0; index < 97; index++) {
            line += "-";
        }
        line += "\n";
        sb.append(line);
        sb.append("|" + String.format(" %-24s", "Nombre"));
        sb.append("|" + String.format("%10s ", "Precio"));
        sb.append("|" + String.format("%10s ", "Peso"));
        sb.append("|" + String.format("%10s ", "Unidades"));
        sb.append("|" + String.format("%15s ", "Precio Total"));
        sb.append("|" + String.format("%15s ", "Peso Total"));
        sb.append("|\n");
        sb.append(line);
        for (Producto prod : this.productos) {
            sb.append("| " + prod.toString() + " |\n");
        }
        sb.append(line);
        sb.append("|" + String.format(" %-24s", "TOTAL"));
        sb.append("|" + String.format("%10s ", ""));
        sb.append("|" + String.format("%10s ", ""));
        sb.append("|" + String.format("%10d ", this.getNumUnidades()));
        sb.append("|" + String.format("%15.2f ", this.getPrecioTotal()));
        sb.append("|" + String.format("%15.2f ", this.getTotalPeso()));
        sb.append("|\n");
        sb.append(line);

        return sb.toString();
    }
}