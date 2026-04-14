/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopaletras;

/**
 *
 * @author ASUS VIVOBOOK
 */

public class Jugador {
    private String nombre;
    private int puntaje;
    private long tiempoTotal;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntaje = 0;
        this.tiempoTotal = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public long getTiempoTotal() {
        return tiempoTotal;
    }

    public void aumentarPuntaje() {
        this.puntaje++;
    }

    public void agregarTiempo(long tiempo) {
        this.tiempoTotal += tiempo;
    }
}
