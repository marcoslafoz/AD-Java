package ejercicio3x01;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Escritor> escritores;
        escritores = AccesoEscritor.cargarEscritores();

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
            scanner.nextLine(); // Consumir la línea en blanco

            switch (opcion) {
                case 1:
                    insertarEscritor(escritores, scanner);
                    break;
                case 2:
                    consultarEscritores(escritores);
                    break;
                case 3:
                    consultarEscritorPorCodigo(escritores, scanner);
                    break;
                case 4:
                    actualizarEscritor(escritores, scanner);
                    break;
                case 5:
                    eliminarEscritor(escritores, scanner);
                    break;
                case 0:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("La opción de menú debe estar comprendida entre 0 y 5.");
            }
        } while (opcion != 0);

        AccesoEscritor.guardarEscritores(escritores);
    }

    private static void insertarEscritor(List<Escritor> escritores, Scanner scanner) {
        System.out.print("Código: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Consumir la línea en blanco

        // Verificar si el código ya existe
        for (Escritor escritor : escritores) {
            if (escritor.getCodigo() == codigo) {
                System.out.println("Ya existe otro escritor con ese código en el fichero binario.");
                return;
            }
        }

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Fecha de Nacimiento: ");
        String fechaNacimiento = scanner.nextLine();
        System.out.print("Nacionalidad: ");
        String nacionalidad = scanner.nextLine();

        Escritor nuevoEscritor = new Escritor(codigo, nombre, fechaNacimiento, nacionalidad);
        escritores.add(nuevoEscritor);
        System.out.println("Se ha insertado un escritor en el fichero binario.");
    }

    private static void consultarEscritores(List<Escritor> escritores) {
        if (escritores.isEmpty()) {
            System.out.println("El fichero binario no tiene ningún escritor.");
        } else {
            for (Escritor escritor : escritores) {
                System.out.println(escritor);
            }
            System.out.println("Se han consultado " + escritores.size() + " escritores del fichero binario.");
        }
    }

    private static void consultarEscritorPorCodigo(List<Escritor> escritores, Scanner scanner) {
        System.out.print("Código del escritor a consultar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Consumir la línea en blanco

        for (Escritor escritor : escritores) {
            if (escritor.getCodigo() == codigo) {
                System.out.println(escritor);
                return;
            }
        }
        System.out.println("No existe ningún escritor con ese código en el fichero binario.");
    }

    private static void actualizarEscritor(List<Escritor> escritores, Scanner scanner) {
        System.out.print("Código del escritor a actualizar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Consumir la línea en blanco

        Escritor escritorExistente = null;
        for (Escritor escritor : escritores) {
            if (escritor.getCodigo() == codigo) {
                escritorExistente = escritor;
                break;
            }
        }

        if (escritorExistente == null) {
            System.out.println("No existe ningún escritor con ese código en el fichero binario.");
        } else {
            System.out.print("Nuevo nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Nueva fecha de Nacimiento: ");
            String fechaNacimiento = scanner.nextLine();
            System.out.print("Nueva nacionalidad: ");
            String nacionalidad = scanner.nextLine();

            escritorExistente.setNombre(nombre);
            escritorExistente.setFechaNacimiento(fechaNacimiento);
            escritorExistente.setNacionalidad(nacionalidad);
            System.out.println("Se ha actualizado un escritor del fichero binario.");
        }
    }

    private static void eliminarEscritor(List<Escritor> escritores, Scanner scanner) {
        System.out.print("Código del escritor a eliminar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Consumir la línea en blanco

        Escritor escritorExistente = null;
        for (Escritor escritor : escritores) {
            if (escritor.getCodigo() == codigo) {
                escritorExistente = escritor;
                break;
            }
        }

        if (escritorExistente == null) {
            System.out.println("No existe ningún escritor con ese código en el fichero binario.");
        } else {
            // Verificar si hay libros referenciados a este escritor (debe implementarse en la práctica)
            // Si no hay libros referenciados, eliminar el escritor.
            escritores.remove(escritorExistente);
            System.out.println("Se ha eliminado un escritor del fichero binario.");
        }
    }
}
