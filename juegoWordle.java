package ud5;

import java.util.Random;
import java.util.Scanner;

public class juegoWordle {
    static String palabraSecreta;
    static int numIntentosConsumidos;
    static int numLetrasAdivinadas;
    static Scanner entrada = new Scanner(System.in);

    public static void main (String[] args) {
        char opcion=' ';
        do {
            System.out.println("Bienvenid@ al juego de Wordle.");
            System.out.println("El objetivo es descubrir la palabra oculta de 5 letras.");
            System.out.println("Introduce una palabra de 5 letras:");
            System.out.print(">");
            String palabra = entrada.nextLine();
            generaPalabra();
            if (palabra.equals(palabraSecreta)) {
                System.out.println("la adivinaste!");
            }
            opcion = entrada.nextLine().charAt(0);
        }while((opcion!='n') || (opcion!='N'));

    }

    public static void generaPalabra() {
        //Habilitamos metodo Random
        Random genAle = new Random();
        //Generamos string con 20 palabras
        String[] palabras = {
                "canto",
                "perla",
                "miedo",
                "salto",
                "lunar",
                "campo",
                "tigre",
                "nubes",
                "fresa",
                "doler",
                "vapor",
                "rueda",
                "cielo",
                "balon",
                "sabor",
                "tecla",
                "orina",
                "moral",
                "pleno",
                "gusto"
        };

        //Elegimos palabra
        palabraSecreta = palabras[genAle.nextInt(palabras.length)];

    }
}
