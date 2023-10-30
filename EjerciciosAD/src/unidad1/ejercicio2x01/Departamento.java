package unidad1.ejercicio2x01;

public class Departamento {
    private int codigo;
    private String nombre;
    private String ubicacion;

    public Departamento(int codigo, String nombre, String ubicacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public Departamento(String linea) {
        String[] partes = linea.split(";");
        this.codigo = Integer.parseInt(partes[0]);
        this.nombre = partes[1];
        this.ubicacion = partes[2];
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return "Departamento [Código = " + codigo + ", Nombre = " + nombre + ", Ubicación = " + ubicacion + "]";
    }

    public String toStringWithSeparators() {
        return codigo + ";" + nombre + ";" + ubicacion;
    }
}
