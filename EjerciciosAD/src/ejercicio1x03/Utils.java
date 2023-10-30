package ejercicio1x03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Utils {
    // Método para escribir frases en un archivo de texto
    public static void escribirFrasesEnFichero(String archivo, Scanner scanner) {
        try {
            // Abre un archivo en modo de adición
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true));

            // Solicita al usuario que escriba frases hasta que ingrese '***'
            System.out.println("Escribe frases en el fichero. Para detener la escritura, ingresa '***' en una línea nueva.");
            String frase;
            while (true) {
                frase = scanner.nextLine();
                if (frase.equals("***")) {
                    break;
                }
                // Escribe la frase en el archivo y agrega una nueva línea
                writer.write(frase);
                writer.newLine();
            }

            // Cierra el archivo y muestra un mensaje de éxito
            writer.close();
            System.out.println("Las frases se han escrito en el archivo.");
        } catch (IOException e) {
            // Manejo de errores de E/S
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    // Método para leer frases de un archivo de texto y mostrarlas en la consola
    public static void leerFrasesDelFichero(String archivo) {
        try {
            // Abre el archivo para lectura
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String linea;
            int numeroLinea = 1;

            // Lee y muestra cada línea del archivo
            while ((linea = reader.readLine()) != null) {
                System.out.println("Línea " + numeroLinea + ": " + linea);
                numeroLinea++;
            }

            // Cierra el archivo
            reader.close();
        } catch (IOException e) {
            // Manejo de errores de E/S
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Método para consultar estadísticas sobre un archivo de texto
    public static void consultarEstadisticas(String archivo) {
        try {
            // Abre el archivo para lectura
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            int numeroLineas = 0;
            int numeroPalabras = 0;

            String linea;
            while ((linea = reader.readLine()) != null) {
                numeroLineas++;
                // Divide la línea en palabras y suma el total de palabras
                numeroPalabras += linea.split("\\s+").length;
            }

            // Muestra el número de líneas y palabras
            System.out.println("Número de líneas: " + numeroLineas);
            System.out.println("Número de palabras: " + numeroPalabras);
            reader.close();
        } catch (IOException e) {
            // Manejo de errores de E/S
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Método para buscar una palabra en un archivo de texto
    public static void buscarPalabraEnFichero(String archivo, Scanner scanner) {
        System.out.print("Introduce una palabra para buscar en el archivo: ");
        String palabra = scanner.nextLine();

        // Verifica si la palabra contiene espacios en blanco
        if (palabra.contains(" ")) {
            System.out.println("La palabra no puede contener espacios en blanco.");
            return;
        }

        try {
            // Abre el archivo para lectura
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String linea;
            int numeroLinea = 1;
            boolean encontrada = false;

            while ((linea = reader.readLine()) != null) {
                if (linea.contains(palabra)) {
                    encontrada = true;
                    // Muestra la línea en la que se encontró la palabra y sale del bucle
                    System.out.println("La palabra está en la línea " + numeroLinea + " del fichero de texto.");
                    break;
                }
                numeroLinea++;
            }

            if (!encontrada) {
                System.out.println("La palabra no está en el fichero de texto.");
            }

            // Cierra el archivo
            reader.close();
        } catch (IOException e) {
            // Manejo de errores de E/S
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Método para convertir frases a mayúsculas y escribirlas en un nuevo archivo
    public static void convertirFrasesAMayusculas(String archivo) {
        try {
            // Abre el archivo para lectura y crea un nuevo archivo para escritura
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/mayusculas.txt"));

            String linea;
            while ((linea = reader.readLine()) != null) {
                // Convierte la línea a mayúsculas y la escribe en el nuevo archivo
                writer.write(linea.toUpperCase());
                writer.newLine();
            }

            // Cierra ambos archivos y muestra un mensaje de éxito
            reader.close();
            writer.close();
            System.out.println("Las frases en mayúsculas se han escrito en el archivo mayusculas.txt.");
        } catch (IOException e) {
            // Manejo de errores de E/S
            System.err.println("Error al convertir y escribir en el archivo mayusculas.txt: " + e.getMessage());
        }
    }

    // Método para convertir frases a minúsculas y escribirlas en un nuevo archivo
    public static void convertirFrasesAMinusculas(String archivo) {
        try {
            // Abre el archivo para lectura y crea un nuevo archivo para escritura
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/minusculas.txt"));

            String linea;
            while ((linea = reader.readLine()) != null) {
                // Convierte la línea a minúsculas y la escribe en el nuevo archivo
                writer.write(linea.toLowerCase());
                writer.newLine();
            }

            // Cierra ambos archivos y muestra un mensaje de éxito
            reader.close();
            writer.close();
            System.out.println("Las frases en minúsculas se han escrito en el archivo minusculas.txt.");
        } catch (IOException e) {
            // Manejo de errores de E/S
            System.err.println("Error al convertir y escribir en el archivo minusculas.txt: " + e.getMessage());
        }
    }

    // Método para mostrar un menú de opciones al usuario
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
