package unidad1.ejercicio3x01;

import java.io.Serializable;

public class Escritor implements Serializable {
    private int codigo;
    private String nombre;
    private String fechaNacimiento;
    private String nacionalidad;

    public Escritor(int codigo, String nombre, String fechaNacimiento, String nacionalidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return "Escritor [Código = " + codigo + ", Nombre = " + nombre + ", FechaNacimiento = " + fechaNacimiento
                + ", Nacionalidad = " + nacionalidad + "]";
    }

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
}
