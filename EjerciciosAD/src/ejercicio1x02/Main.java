package ejercicio1x02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        // Definición de los nombres de archivo de entrada y salida
        final String ARCHIVO_ENTRADA = "data/entrada.txt";
        final String ARCHIVO_SALIDA = "data/estadisticas.txt";

        // Crear objetos File para los archivos de entrada y salida
        File archivoEntrada = new File(ARCHIVO_ENTRADA);
        File archivoSalida = new File(ARCHIVO_SALIDA);

        // Verificar si el archivo de entrada existe en el sistema de archivos
        if (!archivoEntrada.exists()) {
            System.out.println("El archivo no existe en el sistema de archivos.");
            return;
        }

        // Verificar si el archivo de entrada es un fichero
        if (!archivoEntrada.isFile()) {
            System.out.println("El archivo no es un fichero.");
            return;
        }

        try {
            // Crear objetos para lectura y escritura de archivos
            BufferedReader br = new BufferedReader(new FileReader(archivoEntrada));
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida));

            String linea;
            int totalLineas = 0;
            int totalCaracteres = 0;
            int totalPalabras = 0;

            // Leer líneas del archivo de entrada y calcular estadísticas
            while ((linea = br.readLine()) != null) {
                totalLineas++;
                int caracteres = linea.length();
                int palabras = linea.split("\\s+").length;

                totalCaracteres += caracteres;
                totalPalabras += palabras;

                // Escribir estadísticas en el archivo de salida
                bw.write("Línea " + totalLineas + ": " + caracteres + " caracteres y " + palabras + " palabras");
                bw.newLine();
            }

            // Escribir estadísticas totales en el archivo de salida
            bw.write("Total: " + totalLineas + " líneas, " + totalCaracteres + " caracteres y " + totalPalabras + " palabras");

            // Cerrar los objetos de lectura y escritura de archivos
            br.close();
            bw.close();

            System.out.println("El archivo de estadísticas se ha creado con éxito.");
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo de entrada: " + e.getMessage());
        }
    }
}
