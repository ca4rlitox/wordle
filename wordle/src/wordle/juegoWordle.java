package wordle;

import java.util.Random;
import java.util.Scanner;

public class juegoWordle {

	static String palabraSecreta;
	static int numIntentosConsumidos;
	static int numLetrasAdivinadas;
	static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {

		generaPalabra();
		String palabra = entrada.nextLine();
		System.out.println(compruebaLetrasAcertadas(palabra));
		System.out.println(palabraSecreta);


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
}
