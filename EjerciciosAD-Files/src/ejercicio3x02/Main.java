package ejercicio3x02;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Libro> libros;
        libros = AccesoLibro.cargarLibros();

        int opcion;
        do {
            System.out.println("Menú de opciones:");
            System.out.println("0) Salir del programa.");
            System.out.println("1) Insertar un libro en el fichero binario.");
            System.out.println("2) Consultar todos los libros del fichero binario.");
            System.out.println("3) Consultar un libro por código.");
            System.out.println("4) Actualizar un libro por código.");
            System.out.println("5) Eliminar un libro por código.");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    insertarLibro(libros, scanner);
                    break;
                case 2:
                    consultarLibros(libros);
                    break;
                case 3:
                    consultarLibroPorCodigo(libros, scanner);
                    break;
                case 4:
                    actualizarLibro(libros, scanner);
                    break;
                case 5:
                    eliminarLibro(libros, scanner);
                    break;
                case 0:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("La opción de menú debe estar comprendida entre 0 y 5.");
            }
        } while (opcion != 0);

        
		AccesoLibro.guardarLibro(libros);
		
    }

    private static void insertarLibro(List<Libro> libros, Scanner scanner) {
        System.out.print("Código: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        // Verificar si el código ya existe
        for (Libro libro : libros) {
            if (libro.getCodigo() == codigo) {
                System.out.println("Ya existe otro libro con ese código en el fichero binario.");
                return;
            }
        }

        System.out.print("Código de escritor: ");
        int codigoEscritor = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Año de Publicación: ");
        int añoPublicacion = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();

        Libro nuevoLibro = new Libro(codigo, codigoEscritor, titulo, añoPublicacion, precio);
        libros.add(nuevoLibro);
        System.out.println("Se ha insertado un libro en el fichero binario.");
    }

    private static void consultarLibros(List<Libro> libros) {
        if (libros.isEmpty()) {
            System.out.println("El fichero binario no tiene ningún libro.");
        } else {
            for (Libro libro : libros) {
                System.out.println(libro);
            }
            System.out.println("Se han consultado " + libros.size() + " libros del fichero binario.");
        }
    }

    private static void consultarLibroPorCodigo(List<Libro> libros, Scanner scanner) {
        System.out.print("Código del libro a consultar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        for (Libro libro : libros) {
            if (libro.getCodigo() == codigo) {
                System.out.println(libro);
                return;
            }
        }
        System.out.println("No existe ningún libro con ese código en el fichero binario.");
    }

    private static void actualizarLibro(List<Libro> libros, Scanner scanner) {
        System.out.print("Código del libro a actualizar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        Libro libroExistente = null;
        for (Libro libro : libros) {
            if (libro.getCodigo() == codigo) {
                libroExistente = libro;
                break;
            }
        }

        if (libroExistente == null) {
            System.out.println("No existe ningún libro con ese código en el fichero binario.");
        } else {
            System.out.print("Nuevo código de escritor: ");
            int codigoEscritor = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nuevo título: ");
            String titulo = scanner.nextLine();

            System.out.print("Nuevo año de Publicación: ");
            int añoPublicacion = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nuevo precio: ");
            double precio = scanner.nextDouble();
            scanner.nextLine();

            libroExistente.setCodigoEscritor(codigoEscritor);
            libroExistente.setTitulo(titulo);
            libroExistente.setAñoPublicacion(añoPublicacion);
            libroExistente.setPrecio(precio);

            System.out.println("Se ha actualizado un libro del fichero binario.");
        }
    }

    private static void eliminarLibro(List<Libro> libros, Scanner scanner) {
        System.out.print("Código del libro a eliminar: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        Libro libroExistente = null;
        for (Libro libro : libros) {
            if (libro.getCodigo() == codigo) {
                libroExistente = libro;
                break;
            }
        }

        if (libroExistente == null) {
            System.out.println("No existe ningún libro con ese código en el fichero binario.");
        } else {
            libros.remove(libroExistente);
            System.out.println("Se ha eliminado un libro del fichero binario.");
        }
    }
}
