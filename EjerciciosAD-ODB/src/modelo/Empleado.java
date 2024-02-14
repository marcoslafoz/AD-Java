package modelo;

import javax.jdo.annotations.*;

@PersistenceCapable
public class Empleado {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	private int codigo;

	@Persistent
	private String nombre;

	@Persistent
	private String fechaAlta;

	@Persistent
	private double salario;

	@Persistent
	private Departamento departamento;

	@Override
	public String toString() {
		return "Empleado [Código = " + codigo + ", Nombre = " + nombre + ", Fecha alta = " + fechaAlta 
				+ ", Salario = " + salario + ", " + departamento.toString() + "]";
	}

	// Constructor y getters/setters

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

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
}
