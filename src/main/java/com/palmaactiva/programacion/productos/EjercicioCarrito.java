
package com.palmaactiva.programacion.productos;

public class EjercicioCarrito {
    public static void main(String[] args) {
        Producto torres = new Producto("Dell XPS 8930", 898.99f, 10f);
        torres.anyadirCantidad(1);
        Producto ratones = new Producto("Dell MS116 Primax", 12.99f, 0.413f);
        ratones.anyadirCantidad(1);
        Producto teclados = new Producto("Dell KB216", 15.99f, 0.621f);
        teclados.anyadirCantidad(1);
        Producto monitores = new Producto("Dell S2719H", 257.74f, 5.17f);
        monitores.anyadirCantidad(3);

        Carrito miCarrito = new Carrito();
        miCarrito.addProducto(torres);
        miCarrito.addProducto(ratones);
        miCarrito.addProducto(teclados);
        miCarrito.addProducto(monitores);

        System.out.println("Mi carrito es:");
        System.out.println(miCarrito);
    }
}