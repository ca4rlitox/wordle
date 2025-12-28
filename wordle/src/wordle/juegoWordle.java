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
				numIntentosConsumidos++;
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
		boolean vocalesSeguidas=false;
		boolean consonantesSeguidas=false;
		palabraIntroducida = palabraIntroducida.toUpperCase();

		for (int i = 0; i < palabraSecreta.length(); i++) {

			int contadorVocales=0;
			int contadorVocalesSeguidas=0;
			int contadorConsonantesSeguidas=0;

			if (palabraIntroducida.length() != 5 || palabraIntroducida.charAt(i) < 'A' || palabraIntroducida.charAt(i) > 'Z'
					|| palabraIntroducida.lastIndexOf('Q') == 4 || palabraIntroducida.lastIndexOf('W') == 4 || palabraIntroducida.lastIndexOf('X') == 4
					|| palabraIntroducida.charAt(0) == ('A') && palabraIntroducida.charAt(1) == ('A') || palabraIntroducida.charAt(1) == ('A')
					&& palabraIntroducida.charAt(2) == ('A') || palabraIntroducida.charAt(3) == ('A') && palabraIntroducida.charAt(4) == ('A')||
					palabraIntroducida.charAt(0) == ('E') && palabraIntroducida.charAt(1) == ('E') || palabraIntroducida.charAt(1) == ('E')
					&& palabraIntroducida.charAt(2) == ('E') || palabraIntroducida.charAt(3) == ('E') && palabraIntroducida.charAt(4) == ('E')
					|| palabraIntroducida.charAt(0) == ('I') && palabraIntroducida.charAt(1) == ('I') || palabraIntroducida.charAt(1) == ('I')
					&& palabraIntroducida.charAt(2) == ('I') || palabraIntroducida.charAt(3) == ('I') && palabraIntroducida.charAt(4) == ('I')
					|| palabraIntroducida.charAt(0) == ('O') && palabraIntroducida.charAt(1) == ('O') || palabraIntroducida.charAt(1) == ('O')
					&& palabraIntroducida.charAt(2) == ('O') || palabraIntroducida.charAt(3) == ('O') && palabraIntroducida.charAt(4) == ('O')
					|| palabraIntroducida.charAt(0) == ('U') && palabraIntroducida.charAt(1) == ('U') || palabraIntroducida.charAt(1) == ('U')
					&& palabraIntroducida.charAt(2) == ('U') || palabraIntroducida.charAt(3) == ('U') && palabraIntroducida.charAt(4) == ('U')) {
				do {

					System.out.println("La palabra introducida no cumple los requisitos. Intentalo de nuevo.");
					palabraIntroducida = entrada.nextLine();
					palabraIntroducida = palabraIntroducida.toUpperCase();

				} while (palabraIntroducida.length() != 5 || palabraIntroducida.charAt(i) < 'A' || palabraIntroducida.charAt(i) > 'Z'
						|| palabraIntroducida.lastIndexOf('Q') == 4 || palabraIntroducida.lastIndexOf('W') == 4 || palabraIntroducida.lastIndexOf('X') == 4
						|| palabraIntroducida.charAt(0) == ('A') && palabraIntroducida.charAt(1) == ('A') || palabraIntroducida.charAt(1) == ('A')
						&& palabraIntroducida.charAt(2) == ('A') || palabraIntroducida.charAt(3) == ('A') && palabraIntroducida.charAt(4) == ('A')||
						palabraIntroducida.charAt(0) == ('E') && palabraIntroducida.charAt(1) == ('E') || palabraIntroducida.charAt(1) == ('E')
						&& palabraIntroducida.charAt(2) == ('E') || palabraIntroducida.charAt(3) == ('E') && palabraIntroducida.charAt(4) == ('E')
						|| palabraIntroducida.charAt(0) == ('I') && palabraIntroducida.charAt(1) == ('I') || palabraIntroducida.charAt(1) == ('I')
						&& palabraIntroducida.charAt(2) == ('I') || palabraIntroducida.charAt(3) == ('I') && palabraIntroducida.charAt(4) == ('I')
						|| palabraIntroducida.charAt(0) == ('O') && palabraIntroducida.charAt(1) == ('O') || palabraIntroducida.charAt(1) == ('O')
						&& palabraIntroducida.charAt(2) == ('O') || palabraIntroducida.charAt(3) == ('O') && palabraIntroducida.charAt(4) == ('O')
						|| palabraIntroducida.charAt(0) == ('U') && palabraIntroducida.charAt(1) == ('U') || palabraIntroducida.charAt(1) == ('U')
						&& palabraIntroducida.charAt(2) == ('U') || palabraIntroducida.charAt(3) == ('U') && palabraIntroducida.charAt(4) == ('U'));

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
        return numLetrasAdivinadas==5;
    }
	public static boolean haTerminadoJuego (int numIntentosConsumidos) {
        return numIntentosConsumidos==6;
    }
}