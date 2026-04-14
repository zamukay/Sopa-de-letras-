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
import java.util.Random;

public class SopaDeLetras {
    private char[][] sopa;
    private String palabra;
    private int inicioFila, inicioColumna;
    private int finFila, finColumna;

    public SopaDeLetras(String palabra, int tamanoSopa) {
        this.palabra = palabra;
        sopa = new char[tamanoSopa][tamanoSopa];
        llenarSopa();
        colocarPalabra();
    }

    // Rellenar la sopa de letras con letras aleatorias
    private void llenarSopa() {
        Random random = new Random();
        for (int i = 0; i < sopa.length; i++) {
            for (int j = 0; j < sopa[i].length; j++) {
                sopa[i][j] = (char) ('A' + random.nextInt(26));  // Letras aleatorias A-Z
            }
        }
    }

    // Colocar la palabra en la sopa
    private void colocarPalabra() {
        Random random = new Random();
        boolean colocada = false;

        while (!colocada) {
            // Escoger una posición aleatoria para la palabra
            int direccion = random.nextInt(8);  // 8 direcciones posibles

            // Escoger una fila y columna de inicio aleatoria
            int filaInicio = random.nextInt(sopa.length);
            int columnaInicio = random.nextInt(sopa.length);

            // Comprobar si la palabra cabe en la sopa según la dirección
            if (direccion == 0 && filaInicio + palabra.length() <= sopa.length) { // Abajo
                colocarVertical(filaInicio, columnaInicio, 1, 0);
                colocada = true;
            } else if (direccion == 1 && filaInicio - palabra.length() >= -1) { // Arriba
                colocarVertical(filaInicio, columnaInicio, -1, 0);
                colocada = true;
            } else if (direccion == 2 && columnaInicio + palabra.length() <= sopa.length) { // Derecha
                colocarHorizontal(filaInicio, columnaInicio, 0, 1);
                colocada = true;
            } else if (direccion == 3 && columnaInicio - palabra.length() >= -1) { // Izquierda
                colocarHorizontal(filaInicio, columnaInicio, 0, -1);
                colocada = true;
            } else if (direccion == 4 && filaInicio + palabra.length() <= sopa.length && columnaInicio + palabra.length() <= sopa.length) { // Diagonal Abajo Derecha
                colocarDiagonal(filaInicio, columnaInicio, 1, 1);
                colocada = true;
            } else if (direccion == 5 && filaInicio - palabra.length() >= -1 && columnaInicio + palabra.length() <= sopa.length) { // Diagonal Arriba Derecha
                colocarDiagonal(filaInicio, columnaInicio, -1, 1);
                colocada = true;
            } else if (direccion == 6 && filaInicio + palabra.length() <= sopa.length && columnaInicio - palabra.length() >= -1) { // Diagonal Abajo Izquierda
                colocarDiagonal(filaInicio, columnaInicio, 1, -1);
                colocada = true;
            } else if (direccion == 7 && filaInicio - palabra.length() >= -1 && columnaInicio - palabra.length() >= -1) { // Diagonal Arriba Izquierda
                colocarDiagonal(filaInicio, columnaInicio, -1, -1);
                colocada = true;
            }
        }
    }

    // Métodos para colocar la palabra en la sopa (en distintas direcciones)
    private void colocarVertical(int fila, int col, int pasoFila, int pasoCol) {
        for (int i = 0; i < palabra.length(); i++) {
            sopa[fila + (i * pasoFila)][col + (i * pasoCol)] = palabra.charAt(i);
        }
        inicioFila = fila;
        inicioColumna = col;
        finFila = fila + (palabra.length() - 1) * pasoFila;
        finColumna = col + (palabra.length() - 1) * pasoCol;
    }

    private void colocarHorizontal(int fila, int col, int pasoFila, int pasoCol) {
        for (int i = 0; i < palabra.length(); i++) {
            sopa[fila + (i * pasoFila)][col + (i * pasoCol)] = palabra.charAt(i);
        }
        inicioFila = fila;
        inicioColumna = col;
        finFila = fila + (palabra.length() - 1) * pasoFila;
        finColumna = col + (palabra.length() - 1) * pasoCol;
    }

    private void colocarDiagonal(int fila, int col, int pasoFila, int pasoCol) {
        for (int i = 0; i < palabra.length(); i++) {
            sopa[fila + (i * pasoFila)][col + (i * pasoCol)] = palabra.charAt(i);
        }
        inicioFila = fila;
        inicioColumna = col;
        finFila = fila + (palabra.length() - 1) * pasoFila;
        finColumna = col + (palabra.length() - 1) * pasoCol;
    }

    // Imprimir la sopa de letras
    public void imprimirSopa() {
        for (int i = 0; i < sopa.length; i++) {
            for (int j = 0; j < sopa[i].length; j++) {
                System.out.print(sopa[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Método para obtener las coordenadas de la palabra
    public int[] obtenerCoordenadas() {
        return new int[]{inicioFila, inicioColumna, finFila, finColumna};
    }
}