/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopaletras;

import java.util.Scanner;

/**
 *
 * @author ASUS VIVOBOOK
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Crear un objeto de la clase Juego
        Juego juego = new Juego();

        // Mostrar el menú de inicio
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al juego de Sopa de Letras");
        boolean continuar = true;

        while (continuar) {
            // Mostrar opciones al jugador
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Registrar jugadores");
            System.out.println("2. Registrar palabras");
            System.out.println("3. Iniciar juego");
            System.out.println("4. Salir");

            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    // Registrar jugadores
                    juego.registrarJugador();
                    break;

                case 2:
                    // Registrar palabras
                    juego.registrarPalabras();
                    break;

                case 3:
                    // Iniciar juego
                    System.out.println("Iniciando el juego...");
                    juego.iniciarJuego();
                    continuar = false; // El juego finaliza después de la ejecución
                    break;

                case 4:
                    // Salir del programa
                    System.out.println("Gracias por jugar!");
                    continuar = false;
                    break;

                default:
                    System.out.println("Opción no válida, intente nuevamente.");
                    break;
            }
        }

        scanner.close();
    }
}