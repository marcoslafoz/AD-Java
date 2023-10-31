package unidad1.ejercicio4x01;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Escritor implements Serializable {
    private static final long serialVersionUID = 2713965969541462011L; // Agrega este serialVersionUID

    private int codigo;
    private String nombre;
    private Date fechaNacimiento;
    private String nacionalidad;

    public Escritor(int codigo, String nombre, Date fechaNacimiento, String nacionalidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Escritor [Código = " + codigo + ", Nombre = " + nombre + ", FechaNacimiento = " + sdf.format(fechaNacimiento) + ", Nacionalidad = " + nacionalidad + "]";
    }
}
