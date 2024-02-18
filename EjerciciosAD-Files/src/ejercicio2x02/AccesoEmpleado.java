package ejercicio2x02;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccesoEmpleado {
    private static final String FILENAME = "data/empleados.txt";

    static File archivo = new File(FILENAME);
    
    public static List<Empleado> cargarEmpleados() throws IOException {
        List<Empleado> empleados = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                empleados.add(new Empleado(line));
            }
        }
        return empleados;
    }

    public static void guardarEmpleados(List<Empleado> empleados) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Empleado empleado : empleados) {
                writer.write(empleado.toStringWithSeparators());
                writer.newLine();
            }
        }
    }
}
