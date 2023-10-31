package unidad1.ejercicio3x01;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccesoEscritor {
    private static final String FILENAME = "data/escritores.dat";

    public static List<Escritor> cargarEscritores() {
        List<Escritor> escritores = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            while (true) {
                Escritor escritor = (Escritor) ois.readObject();
                escritores.add(escritor);
            }
        } catch (EOFException e) {
            // Se lanza una EOFException al llegar al final del archivo
            return escritores;
        } catch (IOException | ClassNotFoundException e) {
            // Manejar excepciones de lectura o deserialización
            e.printStackTrace();
            return escritores;
        }
    }

    public static void guardarEscritores(List<Escritor> escritores) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            for (Escritor escritor : escritores) {
                oos.writeObject(escritor);
            }
        } catch (IOException e) {
            // Manejar excepciones de escritura
            e.printStackTrace();
        }
    }
}
