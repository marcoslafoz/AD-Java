package ejercicio1x02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        final String ARCHIVO_ENTRADA = "data/entrada.txt";
        final String ARCHIVO_SALIDA = "data/estadisticas.txt";
        
        File archivoEntrada = new File(ARCHIVO_ENTRADA);

        if (!archivoEntrada.exists()) {
            System.out.println("El archivo no existe en el sistema de archivos.");
            return;
        }

        if (!archivoEntrada.isFile()) {
            System.out.println("El archivo no es un fichero.");
            return;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(archivoEntrada));
            BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_SALIDA));

            String linea;
            int totalLineas = 0;
            int totalCaracteres = 0;
            int totalPalabras = 0;

            while ((linea = br.readLine()) != null) {
                totalLineas++;
                int caracteres = linea.length();
                int palabras = linea.split("\\s+").length;

                totalCaracteres += caracteres;
                totalPalabras += palabras;

                bw.write("Línea " + totalLineas + ": " + caracteres + " caracteres y " + palabras + " palabras");
                bw.newLine();
            }

            bw.write("Total: " + totalLineas + " líneas, " + totalCaracteres + " caracteres y " + totalPalabras + " palabras");

            br.close();
            bw.close();

            System.out.println("El archivoEntrada de estadísticas se ha creado con éxito.");
        } catch (IOException e) {
            System.err.println("Error al procesar el archivoEntrada: " + e.getMessage());
        }

        
    }
}
