package unidad1.ejercicio1x03;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String archivoFichero = "data/frases.txt";

        while (true) {
           
        	Utils.mostrarOpciones();

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    return;

                case 1:
                	Utils.escribirFrasesEnFichero(archivoFichero, scanner);
                    break;

                case 2:
                	Utils.leerFrasesDelFichero(archivoFichero);
                    break;

                case 3:
                	Utils.consultarEstadisticas(archivoFichero);
                    break;

                case 4:
                    Utils.buscarPalabraEnFichero(archivoFichero, scanner);
                    break;

                case 5:
                    Utils.convertirFrasesAMayusculas(archivoFichero);
                    break;

                case 6:
                    Utils.convertirFrasesAMinusculas(archivoFichero);
                    break;

                default:
                    System.out.println("La opción de menú debe estar comprendida entre 0 y 6.");
            }
        }
    }


}
