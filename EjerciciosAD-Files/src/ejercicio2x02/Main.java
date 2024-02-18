package ejercicio2x02;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Empleado> empleados;
        try {
            empleados = AccesoEmpleado.cargarEmpleados();
            
        } catch (IOException e) {
            System.err.println("Error al cargar los empleados: " + e.getMessage());
            empleados = new ArrayList<>();
        }

        int opcion;
        do {
            System.out.println("Menú de opciones:");
            System.out.println("0) Salir del programa.");
            System.out.println("1) Insertar un empleado en el fichero de texto.");
            System.out.println("2) Consultar todos los empleados del fichero de texto.");
            System.out.println("3) Consultar un empleado por código.");
            System.out.println("4) Actualizar un empleado por código.");
            System.out.println("5) Eliminar un empleado por código.");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la línea en blanco

            switch (opcion) {
                case 1:
                    insertarEmpleado(empleados, scanner);
                    break;
                case 2:
                    consultarEmpleados(empleados);
                    break;
                case 3:
                    consultarEmpleadoPorCodigo(empleados, scanner);
                    break;
                case 4:
                    actualizarEmpleado(empleados, scanner);
                    break;
                case 5:
                    eliminarEmpleado(empleados, scanner);
                    break;
                case 0:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("La opción de menú debe estar comprendida entre 0 y 5.");
            }
        } while (opcion != 0);

        try {
            AccesoEmpleado.guardarEmpleados(empleados);
        } catch (IOException e) {
            System.err.println("Error al guardar los empleados: " + e.getMessage());
        }
    }

    private static void insertarEmpleado(List<Empleado> empleados, Scanner scanner) {
        System.out.println("Insertar un empleado en el fichero de texto:");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        System.out.print("Código del empleado: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Consumir la línea en blanco

        System.out.print("Código del departamento: ");
        int codigoDepartamento = scanner.nextInt();
        scanner.nextLine(); // Consumir la línea en blanco

        System.out.print("Nombre del empleado: ");
        String nombre = scanner.nextLine();

        System.out.print("Fecha de alta (dd/MM/yyyy): ");
        String fechaAltaStr = scanner.nextLine();
        Date fechaAlta;
        try {
            fechaAlta = dateFormat.parse(fechaAltaStr);
        } catch (ParseException e) {
            System.err.println("Fecha de alta no válida. El empleado no se ha insertado.");
            return;
        }

        System.out.print("Salario del empleado: ");
        double salario = scanner.nextDouble();

        Empleado nuevoEmpleado = new Empleado(codigo, codigoDepartamento, nombre, fechaAlta, salario);
        empleados.add(nuevoEmpleado);
        System.out.println("Se ha insertado un empleado en el fichero de texto.");
    }

    private static void consultarEmpleados(List<Empleado> empleados) {
        System.out.println("Consultar todos los empleados del fichero de texto:");
        
        if (empleados.isEmpty()) {
            System.out.println("El fichero de texto no tiene ningún empleado.");
        } else {
            for (Empleado empleado : empleados) {
                System.out.println(empleado);
            }
            System.out.println("Se han consultado " + empleados.size() + " empleados del fichero de texto.");
        }
    }

    private static void consultarEmpleadoPorCodigo(List<Empleado> empleados, Scanner scanner) {
        System.out.println("Consultar un empleado por código:");
        
        System.out.print("Introduce el código del empleado: ");
        int codigoEmpleado = scanner.nextInt();

        boolean encontrado = false;
        for (Empleado empleado : empleados) {
            if (empleado.getCodigo() == codigoEmpleado) {
                System.out.println(empleado);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No existe ningún empleado con ese código en el fichero de texto.");
        }
    }

    private static void actualizarEmpleado(List<Empleado> empleados, Scanner scanner) {
        System.out.println("Actualizar un empleado por código:");

        System.out.print("Introduce el código del empleado a actualizar: ");
        int codigoEmpleado = scanner.nextInt();

        boolean encontrado = false;
        for (Empleado empleado : empleados) {
            if (empleado.getCodigo() == codigoEmpleado) {
                System.out.println("Introduce los nuevos datos del empleado:");

                System.out.print("Código del departamento: ");
                int codigoDepartamento = scanner.nextInt();
                scanner.nextLine(); // Consumir la línea en blanco

                System.out.print("Nombre del empleado: ");
                String nombre = scanner.nextLine();

                System.out.print("Fecha de alta (dd/MM/yyyy): ");
                String fechaAltaStr = scanner.nextLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaAlta;
                try {
                    fechaAlta = dateFormat.parse(fechaAltaStr);
                } catch (ParseException e) {
                    System.err.println("Fecha de alta no válida. El empleado no se ha actualizado.");
                    return;
                }

                System.out.print("Salario del empleado: ");
                double salario = scanner.nextDouble();

                empleado.setCodigoDepartamento(codigoDepartamento);
                empleado.setNombre(nombre);
                empleado.setFechaAlta(fechaAlta);
                empleado.setSalario(salario);

                System.out.println("Se ha actualizado un empleado del fichero de texto.");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No existe ningún empleado con ese código en el fichero de texto.");
        }
    }

    private static void eliminarEmpleado(List<Empleado> empleados, Scanner scanner) {
        System.out.println("Eliminar un empleado por código:");

        System.out.print("Introduce el código del empleado a eliminar: ");
        int codigoEmpleado = scanner.nextInt();

        boolean encontrado = false;
        for (Empleado empleado : empleados) {
            if (empleado.getCodigo() == codigoEmpleado) {
                empleados.remove(empleado);
                System.out.println("Se ha eliminado un empleado del fichero de texto.");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No existe ningún empleado con ese código en el fichero de texto.");
        }
    }
}
