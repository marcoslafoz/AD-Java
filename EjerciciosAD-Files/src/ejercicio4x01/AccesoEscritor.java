package ejercicio4x01;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccesoEscritor {
    private static final String FILENAME = "data/escritores2.dat";

    public static void insertarEscritor(Escritor escritor) {
        List<Escritor> escritores = cargarEscritores();
        escritores.add(escritor);
        guardarEscritores(escritores);
    }

    public static List<Escritor> cargarEscritores() {
        List<Escritor> escritores = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            while (true) {
                Escritor escritor = (Escritor) ois.readObject();
                escritores.add(escritor);
            }
        } catch (EOFException ignored) {
            // No hacer nada cuando se alcanza el final del archivo
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return escritores;
    }

    private static void guardarEscritores(List<Escritor> escritores) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            for (Escritor escritor : escritores) {
                oos.writeObject(escritor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void consultarEscritores() {
        List<Escritor> escritores = cargarEscritores();
        if (escritores.isEmpty()) {
            System.out.println("El fichero binario no tiene ningún escritor.");
        } else {
            for (Escritor escritor : escritores) {
                System.out.println(escritor);
            }
            System.out.println("Se han consultado " + escritores.size() + " escritores del fichero binario.");
        }
    }

    public static Escritor consultarEscritorPorCodigo(int codigo) {
        List<Escritor> escritores = cargarEscritores();
        for (Escritor escritor : escritores) {
            if (escritor.getCodigo() == codigo) {
                return escritor;
            }
        }
        return null;
    }

    public static void actualizarEscritor(Escritor escritor) {
        List<Escritor> escritores = cargarEscritores();
        for (int i = 0; i < escritores.size(); i++) {
            Escritor current = escritores.get(i);
            if (current.getCodigo() == escritor.getCodigo()) {
                escritores.set(i, escritor);
                break;
            }
        }
        guardarEscritores(escritores);
    }

    public static boolean eliminarEscritor(int codigo) {
        List<Escritor> escritores = cargarEscritores();
        for (int i = 0; i < escritores.size(); i++) {
            Escritor escritor = escritores.get(i);
            if (escritor.getCodigo() == codigo) {
                escritores.remove(i);
                guardarEscritores(escritores);
                return true;
            }
        }
        return false;
    }
}
