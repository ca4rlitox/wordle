package wordle;

import java.util.Random;
import java.util.Scanner;

public class juegoWordle {

	static String palabraSecreta;
	static int numIntentosConsumidos=0;
	static int numLetrasAdivinadas;
	static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {

		//Inicializamos variables que vamos a usar.
		String palabra="";
		char opcion;
		int estadisticasMaquina=0;
		int estadisticasHumano=0;

		do {
			System.out.println("Bienvenid@ al juego de Wordle.");
			System.out.println("El objetivo es descubrir la palabra oculta de 5 letras.");
			System.out.println("Introduce la palabra de 5 letras:");
			//Usamos la función generaPalabra() que no devuelve nada, pero aleatoriamente elige la palabra y se almacena en la variable global palabraSecreta.
			generaPalabra();
			do {
				numIntentosConsumidos++;
				System.out.print(">");
				palabra = entrada.nextLine();
				System.out.println(compruebaLetrasAcertadas(palabra));
				//Comprobamos si la función haGanadoJugador es verdadera para darle la victoria al jugador.
				if (haGanadoJugador(numLetrasAdivinadas)) {
					System.out.println("Has ganado!");
					estadisticasHumano++;
					System.out.println("Tú: "+estadisticasHumano+" punto/s vs Máquina: "+estadisticasMaquina+" punto/s");
					break;
				}
				//Comprobamos si la función haTerminadoJuego es verdadera para darle la victoria a la máquina.
				else if (haTerminadoJuego(numIntentosConsumidos)) {
					System.out.println("Has perdido!");
					estadisticasMaquina++;
					System.out.println("Tú: "+estadisticasHumano+" punto/s vs Máquina: "+estadisticasMaquina+" punto/s");
					break;
				}
				//Generamos bucle mientras las funciones haGanadoJugador y haTerminadoJuego sean falsas.
			}while (!haGanadoJugador(numLetrasAdivinadas) || !haTerminadoJuego(numIntentosConsumidos));
			//Reinicializamos la variable numIntentosConsumidos a 0 para la próxima partida.
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
		//Creamos el array con todas las palabras que vamos a poner en nuestro Wordle.
		String[] generaPalabras = {
				"BARCO", "TIGRE", "PIANO", "LIBRO", "SELVA",
				"MONTE", "PLUMA", "NOCHE", "FRUTA", "CLAVO",
				"MADRE", "PADRE", "GRITO", "PLAYA", "CIELO",
				"VUELO", "TRIGO", "BRAZO", "PUEBLO", "SUELO"
		};
		palabraSecreta = generaPalabras[numAle.nextInt(generaPalabras.length)];
	}

	public static String compruebaLetrasAcertadas(String palabraIntroducida) {
		//Creamos variable con el resultado que vamos a devolver
		String comprobacion = "";
		//Hacemos el array para despues almacenar en él todas las letras.
		char[] palabra = new char[palabraSecreta.length()];
		//Pasamos la palabra a mayusculas para hacer las comprobaciones
		palabraIntroducida = palabraIntroducida.toUpperCase();
		//Ponemos a 0 todas las variables que se van a usar.
		int contadorConsonantes=0;
		int contadorVocales=0;
		boolean contadorVocalesSeguidas=false;
		numLetrasAdivinadas=0;

		//Introducimos la palabra en el array, letra por letra, para hacer las comprobaciones.
		for (int i = 0; i < 5; i++) {
			palabra[i] = palabraIntroducida.charAt(i);
		}
		//Comprobamos si tiene 3 o más consonantes y guardamos el resultado en la variable contadorConsonante.
		for (int i = 0; i < 5; i++) {
			if (palabra[i] != 'A' && palabra[i] != 'E' && palabra[i] != 'I' && palabra[i] != 'O' && palabra[i] != 'U') {
				contadorConsonantes+=1;
			}
			//Si ya vemos que tiene mas de 2 consonantes, ponemos un break para que no ponga el contador a 0.
			else if (contadorConsonantes>2) {
				break;
			}
			else {
				contadorConsonantes=0;
			}
		}
		//Aqui vamos a comprobar si tiene dos vocales iguales seguidas
		for (int i = 0; i < palabra.length; i++) {
			char[]AEUIO= {'A','E','I','O','U'};
				if (palabra[i] == AEUIO[i] && palabra[i+1] == AEUIO[i]){
				contadorVocalesSeguidas=true;
		}
				}
		//Comprobamos en la palabra si hay entre 2 y 3 vocales
		for (int i = 0; i < 5; i++) {
			if (palabra[i] == 'A' || palabra[i] == 'E' || palabra[i] == 'I' || palabra[i] == 'O' || palabra[i] == 'U') {
				contadorVocales+=1;
			}
		}
		//Comprobamos en este for si la palabra cumple los requisitos, si no los cumple, entra en bucle hasta que cumpla los requisitos.
				for (int i = 0; i < palabraSecreta.length(); i++) {
					if (palabraIntroducida.length() != 5 || palabraIntroducida.charAt(i) < 'A' || palabraIntroducida.charAt(i) > 'Z' || contadorVocales>3
							|| contadorVocales<2|| contadorConsonantes>2 ||palabraIntroducida.charAt(4) == 'X' || palabraIntroducida.charAt(4) == 'W'
							|| palabraIntroducida.charAt(4) == 'Q' || contadorVocalesSeguidas) {
						do {

							System.out.println("La palabra introducida no cumple los requisitos. Intentalo de nuevo.");
							System.out.print(">");
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

							//Comprobamos en este array que la palabra no tenga dos vocales iguales seguidas para pasar la variable a false.
							for (int j = 0; j < palabra.length; j++) {
								char[]AEUIO= {'A','E','I','O','U'};
								if (palabra[j] == AEUIO[j] && palabra[j+1] != AEUIO[j]){
									contadorVocalesSeguidas=false;
								}
							}

						} while (palabraIntroducida.length() != 5 || palabraIntroducida.charAt(i) < 'A' || palabraIntroducida.charAt(i) > 'Z' || contadorVocales>3
								|| contadorVocales<2|| contadorConsonantes>2 ||palabraIntroducida.charAt(4) == 'X' || palabraIntroducida.charAt(4) == 'W'
								|| palabraIntroducida.charAt(4) == 'Q' || contadorVocalesSeguidas);

					}
				}
				//Hacemos un for para comprobar la palabra introducida con la palabra secreta.
				for (int i = 0; i < palabraSecreta.length(); i++) {
					char letraIntroducida = palabraIntroducida.charAt(i);
					//Si el caracter en esa posicion es el mismo en la palabra introducida como en la palabra secreta, lo añadimos a mayúsculas en la cadena comprobación y sumamos 1 a numLetrasAdivinadas.
					if (palabraIntroducida.charAt(i) == palabraSecreta.charAt(i)) {
						comprobacion += palabraIntroducida.toUpperCase().charAt(i);
						numLetrasAdivinadas++;
					}
					//Si el caracter introducido no está en esa posición pero está en la palabra secreta, la pasamos a minúsculas y la añadimos a la cadena comprobación.
					else if (palabraSecreta.indexOf(letraIntroducida) != -1) {
						comprobacion += palabraIntroducida.toLowerCase().charAt(i);
					}
					//Si el caracter introducido no está en palabraSecreta, ponemos asterisco y lo añadimos a la cadena.
					else {
						comprobacion += "*";
					}
				}
				//Devolvemos el String con las comprobaciones hechas.
				return comprobacion;
			}
	public static boolean haGanadoJugador (int letras) {
		//Pasamos la función a true si el numero de letras adivinadas es mayor a 4.
        return numLetrasAdivinadas>4;
    }
	public static boolean haTerminadoJuego (int numIntentosConsumidos) {
		//Pasamos la función a true si el número de intentos consumidos es 6.
        return numIntentosConsumidos==6;
    }
}