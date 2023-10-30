package ejercicio1x01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        final String ARCHIVO_ENTRADA = "data/entrada.txt";
        final String ARCHIVO_SALIDA = "data/salida.txt";

        try {
            // Abre el archivo de entrada para lectura
            BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_ENTRADA));
            
            // Abre el archivo de salida para escritura
            BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_SALIDA));
            
            String linea;
            while ((linea = br.readLine()) != null) {
                // Lee una línea del archivo de entrada y la escribe en el archivo de salida
                bw.write(linea);
                bw.newLine(); // Agrega un salto de línea en el archivo de salida
            }
            
            // Cierra los archivos
            br.close();
            bw.close();
            
            System.out.println("El archivo de salida se ha creado con las líneas en orden inverso.");
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }
    }
}
