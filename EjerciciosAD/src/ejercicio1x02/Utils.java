package ejercicio1x02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {

	// Método para leer un fichero y meterlo en un vector
	public static String[] leerArchivo(String nombreArchivo) {
		String[] vLinea = new String[5];

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

	// Método para sacar las estadísticas a un fichero
	public static void mostrarStats(String[] vector) {
		try {
			String nombreFichero = "data/estadisticas.txt";
			FileWriter fw = new FileWriter(nombreFichero);
			BufferedWriter bw = new BufferedWriter(fw);

			int contCaracteres = 0;
			int contPalabras = 0;

			for (int i = 0; i < vector.length; i++) {

				String[] palabras = vector[i].split("\\s+");
				int cantidadPalabras = palabras.length;

				bw.write("L" + i + " > " + vector[i].length() + " caracteres | " + cantidadPalabras + " Palabras");
				bw.newLine();

				contPalabras = contPalabras + cantidadPalabras;
				contCaracteres = contCaracteres + vector[i].length();
			}

			bw.write("");
			bw.newLine();
			bw.write("Lineas> " + vector.length + " Caracteres> " + contCaracteres + " Palabras> " + contPalabras);

			bw.close();
			System.out.println("Vector escrito en el archivo " + nombreFichero);

		} catch (FileNotFoundException e) {
			System.err.println("El archivo no se encontró: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
