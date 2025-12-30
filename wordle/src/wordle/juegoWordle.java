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
				numIntentosConsumidos++;
				System.out.print(">");
				palabra = entrada.nextLine();
				System.out.println(compruebaLetrasAcertadas(palabra));
				if (haGanadoJugador(numLetrasAdivinadas)) {
					System.out.println("Has ganado!");
					estadisticasHumano++;
					System.out.println("Tú: "+estadisticasHumano+" punto/s vs Máquina: "+estadisticasMaquina+" punto/s");
					break;
				}
				else if (haTerminadoJuego(numIntentosConsumidos)) {
					System.out.println("Has perdido!");
					estadisticasMaquina++;
					System.out.println("Tú: "+estadisticasHumano+" punto/s vs Máquina: "+estadisticasMaquina+" punto/s");
					break;
				}
			}while (!haGanadoJugador(numLetrasAdivinadas) || !haTerminadoJuego(numIntentosConsumidos));
			numIntentosConsumidos=0;
			numLetrasAdivinadas=0;

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
		char[] palabra = new char[palabraSecreta.length()];
		palabraIntroducida = palabraIntroducida.toUpperCase();
		int contadorConsonantes=0;
		int contadorVocalesSeguidas=0;
		int contadorVocales=0;


		//Introducimos la palabra en el array para hacer las comprobaciones.
		for (int i = 0; i < 5; i++) {
			palabra[i] = palabraIntroducida.charAt(i);
		}

		//Comprobamos si tiene 3 o más consonantes y guardamos el resultado en la variable contadorConsonante.
		for (int i = 0; i < 5; i++) {
			if (palabra[i] != 'A' && palabra[i] != 'E' && palabra[i] != 'I' && palabra[i] != 'O' && palabra[i] != 'U') {
				contadorConsonantes+=1;
			} else if (contadorConsonantes>2) {
				break;
			}
			else {
				contadorConsonantes=0;
			}
		}

		//Comprobamos en la palabra si hay entre 2 y 3 vocales
		for (int i = 0; i < 5; i++) {
			if (palabra[i] == 'A' || palabra[i] == 'E' || palabra[i] == 'I' || palabra[i] == 'O' || palabra[i] == 'U') {
				contadorVocales+=1;
			}
		}
				for (int i = 0; i < palabraSecreta.length(); i++) {

					if (palabraIntroducida.length() != 5 || palabraIntroducida.charAt(i) < 'A' || palabraIntroducida.charAt(i) > 'Z' || contadorVocales>3
							|| contadorVocales<2|| contadorConsonantes>2 || palabra[4] == 'Q' || palabra[4] == 'W' || palabra[4] == 'X'
							|| palabra[0] == palabra[1] || palabra[1] == palabra[2] || palabra[2] == palabra[3] || palabra[3] == palabra[4]) {
						do {

							System.out.println("La palabra introducida no cumple los requisitos. Intentalo de nuevo.");
							palabraIntroducida = entrada.nextLine();
							palabraIntroducida = palabraIntroducida.toUpperCase();
							for (int j = 0; j < 5; j++) {
								palabra[j] = palabraIntroducida.charAt(j);
							}
							// Reiniciamos contadores de consonantes y vocales.
							contadorConsonantes=0;
							contadorVocales=0;
							for (int j = 1; j < 5; j++) {
								if (palabra[j] != 'A' && palabra[j] != 'E' && palabra[j] != 'I' && palabra[j] != 'O' && palabra[j] != 'U') {
									contadorConsonantes+=1;
								} else if (contadorConsonantes>2) {
									break;
								} else {
									contadorConsonantes=0;
								}
							}

							//Comprobamos en la palabra si hay entre 2 y 3 vocales
							for (int j = 0; j < 5; j++) {
								if (palabra[j] == 'A' || palabra[j] == 'E' || palabra[j] == 'I' || palabra[j] == 'O' || palabra[j] == 'U') {
									contadorVocales+=1;
								}
							}

						} while (palabraIntroducida.length() != 5 || palabraIntroducida.charAt(i) < 'A' || palabraIntroducida.charAt(i) > 'Z' || contadorVocales>3
								|| contadorVocales<2|| contadorConsonantes>2 || palabra[4] == 'Q' || palabra[4] == 'W' || palabra[4] == 'X'
								|| palabra[0] == palabra[1] || palabra[1] == palabra[2] || palabra[2] == palabra[3] || palabra[3] == palabra[4]);

					}
				}
				for (int i = 0; i < palabraSecreta.length(); i++) {
					char letraIntroducida = palabraIntroducida.charAt(i);

					if (palabraIntroducida.charAt(i) == palabraSecreta.charAt(i)) {
						comprobacion += palabraIntroducida.toUpperCase().charAt(i);
						numLetrasAdivinadas++;
					} else if (palabraSecreta.indexOf(letraIntroducida) != -1) {
						comprobacion += palabraIntroducida.toLowerCase().charAt(i);
					} else {
						comprobacion += "*";
					}
				}
				return comprobacion;
			}
	public static boolean haGanadoJugador (int letras) {
        return numLetrasAdivinadas>4;
    }
	public static boolean haTerminadoJuego (int numIntentosConsumidos) {
        return numIntentosConsumidos==6;
    }
}