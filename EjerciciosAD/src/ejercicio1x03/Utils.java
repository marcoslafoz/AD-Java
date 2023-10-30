package ejercicio1x03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Utils {
	public static void escribirFrasesEnFichero(String archivo, Scanner scanner) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));

            System.out.println("Escribe frases en el fichero. Para detener la escritura, ingresa '***' en una línea nueva.");
            String frase;
            while (true) {
                frase = scanner.nextLine();
                if (frase.equals("***")) {
                    break;
                }
                writer.write(frase);
                writer.newLine();
            }

            writer.close();
            System.out.println("Las frases se han escrito en el archivo.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static void leerFrasesDelFichero(String archivo) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String linea;
            int numeroLinea = 1;

            while ((linea = reader.readLine()) != null) {
                System.out.println("Línea " + numeroLinea + ": " + linea);
                numeroLinea++;
            }

            reader.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void consultarEstadisticas(String archivo) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            int numeroLineas = 0;
            int numeroPalabras = 0;

            String linea;
            while ((linea = reader.readLine()) != null) {
                numeroLineas++;
                numeroPalabras += linea.split("\\s+").length;
            }

            System.out.println("Número de líneas: " + numeroLineas);
            System.out.println("Número de palabras: " + numeroPalabras);
            reader.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void buscarPalabraEnFichero(String archivo, Scanner scanner) {
        System.out.print("Introduce una palabra para buscar en el archivo: ");
        String palabra = scanner.nextLine();

        if (palabra.contains(" ")) {
            System.out.println("La palabra no puede contener espacios en blanco.");
            return;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String linea;
            int numeroLinea = 1;
            boolean encontrada = false;

            while ((linea = reader.readLine()) != null) {
                if (linea.contains(palabra)) {
                    encontrada = true;
                    System.out.println("La palabra está en la línea " + numeroLinea + " del fichero de texto.");
                    break;
                }
                numeroLinea++;
            }

            if (!encontrada) {
                System.out.println("La palabra no está en el fichero de texto.");
            }

            reader.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void convertirFrasesAMayusculas(String archivo) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/mayusculas.txt"));

            String linea;
            while ((linea = reader.readLine()) != null) {
                writer.write(linea.toUpperCase());
                writer.newLine();
            }

            reader.close();
            writer.close();
            System.out.println("Las frases en mayúsculas se han escrito en el archivo mayusculas.txt.");
        } catch (IOException e) {
            System.err.println("Error al convertir y escribir en el archivo mayusculas.txt: " + e.getMessage());
        }
    }

    public static void convertirFrasesAMinusculas(String archivo) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/minusculas.txt"));

            String linea;
            while ((linea = reader.readLine()) != null) {
                writer.write(linea.toLowerCase());
                writer.newLine();
            }

            reader.close();
            writer.close();
            System.out.println("Las frases en minúsculas se han escrito en el archivo minusculas.txt.");
        } catch (IOException e) {
            System.err.println("Error al convertir y escribir en el archivo minusculas.txt: " + e.getMessage());
        }
    }
    
    public static void mostrarOpciones() {
    	 System.out.println("Menú de opciones:");
         System.out.println("0) Salir del programa.");
         System.out.println("1) Escribir varias frases en el fichero de texto.");
         System.out.println("2) Leer todas las frases del fichero de texto.");
         System.out.println("3) Consultar el número de líneas y el número de palabras del fichero de texto.");
         System.out.println("4) Buscar una palabra en el fichero de texto.");
         System.out.println("5) Crear un fichero de texto con frases en mayúsculas.");
         System.out.println("6) Crear un fichero de texto con frases en minúsculas.");
         System.out.print("Elija una opción (0-6): ");
    }
}
