package modelo;
// Generated 14 nov 2023 10:53:13 by Hibernate Tools 6.1.3.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Departamento generated by hbm2java
 */
public class Departamento implements java.io.Serializable {

	private Short codigo;
	private String nombre;
	private String ubicacion;
	private Set empleados = new HashSet(0);

	public Departamento() {
	}

	public Departamento(String nombre, String ubicacion) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
	}

	public Departamento(String nombre, String ubicacion, Set empleados) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.empleados = empleados;
	}

	public Short getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Set getEmpleados() {
		return this.empleados;
	}

	public void setEmpleados(Set empleados) {
		this.empleados = empleados;
	}

	@Override
	public String toString() {
		return 
			"Departamento [C�digo = " + codigo + 
			", Nombre = " + nombre + 
			", Ubicaci�n = " + ubicacion + 
			"]";
	}
	
}
