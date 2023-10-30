package ejercicio2x01;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccesoDepartamento accesoDepartamento = new AccesoDepartamento("data/departamentos.txt");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menú de opciones:");
            System.out.println("0) Salir del programa.");
            System.out.println("1) Insertar un departamento en el fichero de texto.");
            System.out.println("2) Consultar todos los departamentos del fichero de texto.");
            System.out.println("3) Consultar un departamento por código.");
            System.out.println("4) Actualizar un departamento por código.");
            System.out.println("5) Eliminar un departamento por código.");

            int opcion = -1;
            try {
                opcion = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("La opción de menú debe ser un número.");
                scanner.nextLine(); // Limpiar el búfer de entrada
            }

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del programa.");
                    return;
                case 1:
                    System.out.println("Introduce el código, nombre y ubicación del departamento:");
                    int codigo = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el búfer de entrada
                    String nombre = scanner.nextLine();
                    String ubicacion = scanner.nextLine();
                    accesoDepartamento.insertarDepartamento(new Departamento(codigo, nombre, ubicacion));
                    break;
                case 2:
                    List<Departamento> departamentos = accesoDepartamento.consultarDepartamentos();
                    if (departamentos.isEmpty()) {
                        System.out.println("El fichero de texto no tiene ningún departamento.");
                    } else {
                        for (Departamento departamento : departamentos) {
                            System.out.println(departamento.toString());
                        }
                        System.out.println("Se han consultado " + departamentos.size() + " departamentos del fichero de texto.");
                    }
                    break;
                case 3:
                    System.out.println("Introduce el código del departamento a consultar:");
                    int codigoConsulta = scanner.nextInt();
                    Departamento departamentoConsulta = accesoDepartamento.consultarDepartamentoPorCodigo(codigoConsulta);
                    if (departamentoConsulta != null) {
                        System.out.println(departamentoConsulta.toString());
                    } else {
                        System.out.println("No existe ningún departamento con ese código en el fichero de texto.");
                    }
                    break;
                case 4:
                    System.out.println("Introduce el código del departamento a actualizar:");
                    int codigoActualizacion = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el búfer de entrada
                    System.out.println("Introduce el nuevo nombre:");
                    String nuevoNombre = scanner.nextLine();
                    System.out.println("Introduce la nueva ubicación:");
                    String nuevaUbicacion = scanner.nextLine();
                    accesoDepartamento.actualizarDepartamento(codigoActualizacion, nuevoNombre, nuevaUbicacion);
                    break;
                case 5:
                    System.out.println("Introduce el código del departamento a eliminar:");
                    int codigoEliminacion = scanner.nextInt();
                    accesoDepartamento.eliminarDepartamento(codigoEliminacion, "empleados.txt");
                    break;
                default:
                    System.out.println("La opción de menú debe estar comprendida entre 0 y 5.");
            }
        }
    }
}
