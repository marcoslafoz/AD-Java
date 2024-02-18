package ejercicio3x02;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccesoLibro {
    private static final String FILENAME = "data/libros.dat";

    public static List<Libro> cargarLibros() {
        List<Libro> libros = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            while (true) {
                Libro libro = (Libro) ois.readObject();
                libros.add(libro);
            }
        } catch (EOFException e) {
            return libros;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return libros;
        }
    }

    public static void guardarLibro(List<Libro> libros) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            for (Libro libro : libros) {
                oos.writeObject(libro);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
