package unidad1.ejercicio4x01;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú de opciones:");
            System.out.println("0) Salir del programa.");
            System.out.println("1) Insertar un escritor en el fichero binario.");
            System.out.println("2) Consultar todos los escritores del fichero binario.");
            System.out.println("3) Consultar un escritor por código.");
            System.out.println("4) Actualizar un escritor por código.");
            System.out.println("5) Eliminar un escritor por código.");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    insertarEscritor(scanner);
                    break;
                case 2:
                    consultarEscritores();
                    break;
                case 3:
                    consultarEscritorPorCodigo(scanner);
                    break;
                case 4:
                    actualizarEscritor(scanner);
                    break;
                case 5:
                    eliminarEscritor(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("La opción de menú debe estar comprendida entre 0 y 5.");
            }
        } while (opcion != 0);
    }

    private static void insertarEscritor(Scanner scanner) {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Fecha de nacimiento (dd/MM/yyyy): ");
        Date fechaNacimiento = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaNacimientoStr = scanner.nextLine();
        try {
            fechaNacimiento = sdf.parse(fechaNacimientoStr);
        } catch (ParseException e) {
            System.out.println("Error al convertir la fecha. Asegúrate de usar el formato dd/MM/yyyy.");
            return;
        }

        System.out.print("Nacionalidad: ");
        String nacionalidad = scanner.nextLine();

        Escritor escritor = new Escritor(0, nombre, fechaNacimiento, nacionalidad);
        AccesoEscritor.insertarEscritor(escritor);
        System.out.println("Se ha insertado un escritor en el fichero binario.");
    }

    private static void consultarEscritores() {
        AccesoEscritor.consultarEscritores();
    }

    private static void consultarEscritorPorCodigo(Scanner scanner) {
        System.out.print("Código del escritor a consultar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        Escritor escritor = AccesoEscritor.consultarEscritorPorCodigo(codigo);
        if (escritor != null) {
            System.out.println(escritor);
        } else {
            System.out.println("No existe ningún escritor con ese código en el fichero binario.");
        }
    }

    private static void actualizarEscritor(Scanner scanner) {
        System.out.print("Código del escritor a actualizar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        Escritor escritor = AccesoEscritor.consultarEscritorPorCodigo(codigo);
        if (escritor != null) {
            System.out.print("Nuevo nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Nueva fecha de nacimiento (dd/MM/yyyy): ");
            Date fechaNacimiento = null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fechaNacimientoStr = scanner.nextLine();
            try {
                fechaNacimiento = sdf.parse(fechaNacimientoStr);
            } catch (ParseException e) {
                System.out.println("Error al convertir la fecha. Asegúrate de usar el formato dd/MM/yyyy.");
                return;
            }

            System.out.print("Nueva nacionalidad: ");
            String nacionalidad = scanner.nextLine();

            escritor = new Escritor(codigo, nombre, fechaNacimiento, nacionalidad);
            
            AccesoEscritor.actualizarEscritor(escritor);
            System.out.println("Se ha actualizado un escritor del fichero binario.");
        } else {
            System.out.println("No existe ningún escritor con ese código en el fichero binario.");
        }
    }

    private static void eliminarEscritor(Scanner scanner) {
        System.out.print("Código del escritor a eliminar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        boolean result = AccesoEscritor.eliminarEscritor(codigo);
        if (result) {
            System.out.println("Se ha eliminado un escritor del fichero binario.");
        } else {
            System.out.println("No existe ningún escritor con ese código en el fichero binario.");
        }
    }
}
