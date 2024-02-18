package actividades2;

import javax.jdo.annotations.*;

@PersistenceCapable
public class Departamento {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
	private int codigo;

	@Persistent
	private String nombre;

	@Persistent
	private String ubicacion;

	@Override
	public String toString() {
		return "Departamento [Código = " + codigo + ", Nombre = " + nombre + ", Ubicacion = " + ubicacion + "]";
	}

	// Constructor y getters/setters

	public long getCodigo() {
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

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

}
