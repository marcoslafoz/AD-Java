package ejercicio1x01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Services {

	// Método para leer archivo
	public static String[] leerArchivo(String nombreArchivo) {
		String[] vLinea = null;

		try {
			FileReader fr = new FileReader(nombreArchivo);
			BufferedReader br = new BufferedReader(fr);

			String linea;
			int contador = 0;

			while ((linea = br.readLine()) != null) {
				vLinea[contador] = linea;
				contador++;
			}

			br.close();
		} catch (FileNotFoundException e) {
			System.err.println("El archivo no se encontró: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return vLinea;
	}

	// Método para escribir el contenido en un fichero
	public static void escribirArchivo(String[] vector, String nombreFichero) {
		try {
			FileWriter fw = new FileWriter(nombreFichero);
			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = vector.length - 1; i >= 0; i--) {
				bw.write(vector[i]);
				bw.newLine();
			}

			bw.close();
			System.out.println("Vector escrito en el archivo " + nombreFichero);
		} catch (FileNotFoundException e) {
			System.err.println("El archivo no se encontró: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
