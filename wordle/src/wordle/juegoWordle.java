package wordle;

import java.util.Random;
import java.util.Scanner;

public class juegoWordle {

	static String palabraSecreta;
	static int numIntentosConsumidos=0;
	static int numLetrasAdivinadas;
	static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {

		String palabra="";
		char opcion;
		int estadisticasMaquina=0;
		int estadisticasHumano=0;

		do {
			System.out.println("Bienvenid@ al juego de Wordle.");
			System.out.println("El objetivo es descubrir la palabra oculta de 5 letras.");
			System.out.println("Introduce la palabra de 5 letras:");
			generaPalabra();
			do {
				System.out.print(">");
				palabra = entrada.nextLine();
				System.out.println(compruebaLetrasAcertadas(palabra));
				numIntentosConsumidos++;
				if (haGanadoJugador(palabra)) {
					System.out.println("Has ganado!");
					estadisticasHumano++;
					System.out.println("Tú: "+estadisticasHumano+" puntos vs Máquina: "+estadisticasMaquina+" punto");
					break;
				}
				else if (haTerminadoJuego(numIntentosConsumidos)) {
					System.out.println("Has perdido!");
					estadisticasMaquina++;
					System.out.println("Tú: "+estadisticasHumano+" puntos vs Máquina: "+estadisticasMaquina+" punto");
					break;
				}
			}while (!palabra.equals(palabraSecreta));
			numIntentosConsumidos=0;

			System.out.println("¿Deseas jugar otra partida? (S/N)");
			opcion = entrada.nextLine().charAt(0);
			if (opcion!='n' && opcion!='N' && opcion!='S' && opcion!='s') {
				do{
					System.out.println("Opción elegida no válida. Intentalo de nuevo.");
					System.out.println("¿Deseas jugar otra partida? (S/N)");
					opcion = entrada.nextLine().charAt(0);
				}while (opcion!='n' && opcion!='N' && opcion!='S' && opcion!='s');
			}
		} while (opcion != 'n' && opcion !='N');
	}

	public static void generaPalabra() {
		Random numAle = new Random();
		String[] generaPalabras = {
				"BARCO", "TIGRE", "PIANO", "LIBRO", "SELVA",
				"MONTE", "PLUMA", "NOCHE", "FRUTA", "CLAVO",
				"MADRE", "PADRE", "GRITO", "PLAYA", "CIELO",
				"VUELO", "TRIGO", "BRAZO", "PUEBLO", "SUELO"
		};
		palabraSecreta = generaPalabras[numAle.nextInt(generaPalabras.length)];
	}

	public static String compruebaLetrasAcertadas(String palabraIntroducida) {
		String comprobacion = "";
		palabraIntroducida = palabraIntroducida.toUpperCase();

		for (int i = 0; i < palabraSecreta.length(); i++) {

			if (palabraIntroducida.length() != 5 || palabraIntroducida.charAt(i) < 'A' || palabraIntroducida.charAt(i) > 'Z') {
				do {

					System.out.println("La palabra introducida no cumple los requisitos. Intentalo de nuevo.");
					palabraIntroducida = entrada.nextLine();
					palabraIntroducida = palabraIntroducida.toUpperCase();

				} while (palabraIntroducida.length() != 5 || palabraIntroducida.charAt(i) < 'A' || palabraIntroducida.charAt(i) > 'Z');

			}
		}
			for (int i = 0; i < palabraSecreta.length(); i++) {
				char letraIntroducida = palabraIntroducida.charAt(i);

				if (palabraIntroducida.charAt(i) == palabraSecreta.charAt(i)) {
					comprobacion += palabraIntroducida.toUpperCase().charAt(i);
				} else if (palabraSecreta.indexOf(letraIntroducida) != -1) {
					comprobacion += palabraIntroducida.toLowerCase().charAt(i);
				} else {
					comprobacion += "*";
				}
			}
		return comprobacion;
	}
	public static boolean haGanadoJugador (String palabra) {
        return palabra.equals(palabraSecreta);
    }
	public static boolean haTerminadoJuego (int numIntentosConsumidos) {
        return numIntentosConsumidos == 6;
    }
}