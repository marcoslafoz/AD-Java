package ejercicio2x01;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccesoDepartamento {
    private String nombreArchivo;

    public AccesoDepartamento(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void insertarDepartamento(Departamento departamento) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo, true))) {
            Scanner scanner = new Scanner(new File(nombreArchivo));
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                Departamento existing = new Departamento(linea);
                if (existing.getCodigo() == departamento.getCodigo()) {
                    System.out.println("Ya existe otro departamento con ese código en el fichero de texto.");
                    return;
                }
            }
            writer.println(departamento.toStringWithSeparators());
            System.out.println("Se ha insertado un departamento en el fichero de texto.");
        } catch (IOException e) {
            System.out.println("Error al insertar el departamento: " + e.getMessage());
        }
    }

    public List<Departamento> consultarDepartamentos() {
        List<Departamento> departamentos = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(nombreArchivo))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                Departamento departamento = new Departamento(linea);
                departamentos.add(departamento);
            }
        } catch (IOException e) {
            System.out.println("Error al consultar los departamentos: " + e.getMessage());
        }
        return departamentos;
    }

    public Departamento consultarDepartamentoPorCodigo(int codigo) {
        try (Scanner scanner = new Scanner(new File(nombreArchivo))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                Departamento departamento = new Departamento(linea);
                if (departamento.getCodigo() == codigo) {
                    return departamento;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al consultar el departamento: " + e.getMessage());
        }
        return null;
    }

    public void actualizarDepartamento(int codigo, String nuevoNombre, String nuevaUbicacion) {
        List<Departamento> departamentos = new ArrayList<>();
        boolean encontrado = false;
        try (Scanner scanner = new Scanner(new File(nombreArchivo))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                Departamento departamento = new Departamento(linea);
                if (departamento.getCodigo() == codigo) {
                    departamento = new Departamento(codigo, nuevoNombre, nuevaUbicacion);
                    encontrado = true;
                }
                departamentos.add(departamento);
            }
        } catch (IOException e) {
            System.out.println("Error al actualizar el departamento: " + e.getMessage());
        }

        if (!encontrado) {
            System.out.println("No existe ningún departamento con ese código en el fichero de texto.");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (Departamento departamento : departamentos) {
                writer.println(departamento.toStringWithSeparators());
            }
            System.out.println("Se ha actualizado un departamento del fichero de texto.");
        } catch (IOException e) {
            System.out.println("Error al actualizar el departamento: " + e.getMessage());
        }
    }

    public void eliminarDepartamento(int codigo, String empleadosFile) {
        List<Departamento> departamentos = new ArrayList<>();
        boolean encontrado = false;
        try (Scanner scanner = new Scanner(new File(nombreArchivo))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                Departamento departamento = new Departamento(linea);
                if (departamento.getCodigo() == codigo) {
                    encontrado = true;
                } else {
                    departamentos.add(departamento);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al eliminar el departamento: " + e.getMessage());
        }
	
        if (!encontrado) {
            System.out.println("No existe ningún departamento con ese código en el fichero de texto.");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (Departamento departamento : departamentos) {
                writer.println(departamento.toStringWithSeparators());
            }
            System.out.println("Se ha eliminado un departamento del fichero de texto.");
        } catch (IOException e) {
            System.out.println("Error al eliminar el departamento: " + e.getMessage());
        }
    }
}

