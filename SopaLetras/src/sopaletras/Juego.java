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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Juego {
    private ArrayList<Jugador> jugadores;
    private ArrayList<String> palabras;
    private int rondas;
    private int tiempoMaximo;
    private Scanner sc;
    private int tamanoSopa;
    private String dificultad;

    public Juego() {
        jugadores = new ArrayList<>();
        palabras = new ArrayList<>();
        rondas = 5; // Rondas por defecto
        tiempoMaximo = 30000; // Tiempo por defecto (30 segundos)
        tamanoSopa = 5; // Tamaño por defecto de la sopa de letras
        dificultad = "Fácil"; // Dificultad por defecto
        sc = new Scanner(System.in);
    }

    // Método para registrar jugadores
    public void registrarJugador() {
        System.out.print("Ingrese el nombre del jugador: ");
        String nombre = sc.nextLine();
        jugadores.add(new Jugador(nombre));
    }

    // Método para registrar palabras
    public void registrarPalabras() {
        System.out.print("Ingrese la palabra o cargue desde archivo (1 para teclado, 2 para archivo): ");
        int opcion = Integer.parseInt(sc.nextLine());

        if (opcion == 1) {
            System.out.print("Ingrese la palabra: ");
            String palabra = sc.nextLine();
            palabras.add(palabra);
        } else if (opcion == 2) {
            cargarPalabrasDesdeArchivo();
        }
    }

    // Método para cargar palabras desde un archivo
    public void cargarPalabrasDesdeArchivo() {
        String rutaArchivo = "C:/Users/ASUS VIVOBOOK/Documents/NetBeansProjects/SopaLetras/palabras.txt";


        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String palabra;
            while ((palabra = br.readLine()) != null) {
                if (!palabra.trim().isEmpty()) {
                    palabras.add(palabra.trim());
                }
            }
            System.out.println("Palabras cargadas exitosamente desde el archivo.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Seleccionar dificultad
    public void seleccionarDificultad() {
        System.out.println("Seleccione la dificultad:");
        System.out.println("1. Fácil");
        System.out.println("2. Medio");
        System.out.println("3. Difícil");
        System.out.println("4. Extremo");
        int opcion = Integer.parseInt(sc.nextLine());

        switch (opcion) {
            case 1:
                dificultad = "Fácil";
                tamanoSopa = 5;
                break;
            case 2:
                dificultad = "Medio";
                tamanoSopa = 7;
                break;
            case 3:
                dificultad = "Difícil";
                tamanoSopa = 10;
                break;
            case 4:
                dificultad = "Extremo";
                tamanoSopa = 15;
                break;
            default:
                System.out.println("Opción no válida, se seleccionó 'Fácil' por defecto.");
                dificultad = "Fácil";
                tamanoSopa = 5;
        }
        System.out.println("Dificultad seleccionada: " + dificultad);
    }

    // Seleccionar rondas
    public void seleccionarRondas() {
        System.out.print("Ingrese el número de rondas: ");
        rondas = Integer.parseInt(sc.nextLine());
    }

    // Método para jugar
    public void jugar() {
        for (int ronda = 1; ronda <= rondas; ronda++) {
            System.out.println("Comienza la ronda " + ronda);
            String palabra = palabras.get(new Random().nextInt(palabras.size()));
            SopaDeLetras sopa = new SopaDeLetras(palabra, tamanoSopa);

            sopa.imprimirSopa();
            long tiempoInicio = System.currentTimeMillis();

            // Permitir al jugador adivinar la palabra
            System.out.println("Adivina la posición de la palabra (formato: fila,columna fila,columna):");
            String respuesta = sc.nextLine();
            String[] partes = respuesta.split(" ");

            String[] inicio = partes[0].split(",");
            String[] fin = partes[1].split(",");

            int filaInicioJugador = Integer.parseInt(inicio[0]) - 1;
            int colInicioJugador = Integer.parseInt(inicio[1]) - 1;
            int filaFinJugador = Integer.parseInt(fin[0]) - 1;
            int colFinJugador = Integer.parseInt(fin[1]) - 1;

            // Obtener las coordenadas correctas de la palabra en la sopa
            int[] coordenadasCorrectas = sopa.obtenerCoordenadas();
            int filaInicioCorrecta = coordenadasCorrectas[0];
            int colInicioCorrecta = coordenadasCorrectas[1];
            int filaFinCorrecta = coordenadasCorrectas[2];
            int colFinCorrecta = coordenadasCorrectas[3];

            // Verificar si las coordenadas del jugador son correctas
            if (filaInicioJugador == filaInicioCorrecta && colInicioJugador == colInicioCorrecta &&
                filaFinJugador == filaFinCorrecta && colFinJugador == colFinCorrecta) {
                System.out.println("¡Respuesta correcta!");
                for (Jugador jugador : jugadores) {
                    jugador.aumentarPuntaje();
                }
            } else {
                System.out.println("¡Respuesta incorrecta!");
            }

            long tiempoFin = System.currentTimeMillis();
            long tiempoTardado = tiempoFin - tiempoInicio;
            System.out.println("Tiempo de respuesta: " + (tiempoTardado / 1000.0) + " segundos");
        }
    }

    // Mostrar el ganador
    public void mostrarGanador() {
        Jugador ganador = null;
        int maxPuntaje = 0;
        
        for (Jugador jugador : jugadores) {
            if (jugador.getPuntaje() > maxPuntaje) {
                ganador = jugador;
                maxPuntaje = jugador.getPuntaje();
            }
        }

        if (ganador != null) {
            System.out.println("El ganador es: " + ganador.getNombre());
        } else {
            System.out.println("Nadie ha ganado.");
        }
    }

    // Iniciar el juego
    public void iniciarJuego() {
        registrarJugador();
        registrarPalabras();
        seleccionarRondas();
        seleccionarDificultad();  // Seleccionar dificultad
        jugar();
        mostrarGanador();
    }

    // Método main para ejecutar el juego
    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.iniciarJuego();
    }
}