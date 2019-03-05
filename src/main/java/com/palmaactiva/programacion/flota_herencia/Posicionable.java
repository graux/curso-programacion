package com.palmaactiva.programacion.flota_herencia;

public interface Posicionable {
    // Devolverá la representación textual para pintar la celda
    public String getStringCelda(Posicion pos);

    // Devuelve todas las posiciones del objeto
    public Posicion[] getPosiciones();

    // Método para comprobar si este posicionable se solapa con una posición
    // Implementación por defecto.
    default public boolean seSolapaCon(Posicion pos) {
        for (Posicion miPosicion : this.getPosiciones()) {
            // Comparamos si alguna posición coincide
            if (miPosicion.sobrePosicion(pos)) {
                return true;
            }
        }
        // No ha coincidido ninguna posición
        return false;
    }

    // Método para comprobar si este posicionable se solapa con otro Posicionable
    // Implementación por defecto.
    default public boolean seSolapaCon(Posicionable pos) {
        // Sacamos todas las posiciones de este Posicionable
        Posicion[] misPosiciones = this.getPosiciones();
        // Sacamos todas las posiciones del posicionable pasado como argumento
        Posicion[] otrasPosiciones = pos.getPosiciones();

        for (Posicion miPosicion : misPosiciones) {
            for (Posicion otraPosicion : otrasPosiciones) {
                // Comparamos si alguna posición coincide
                if (miPosicion.sobrePosicion(otraPosicion)) {
                    return true;
                }
            }
        }
        // No ha coincidido ninguna posición
        return false;
    }
}