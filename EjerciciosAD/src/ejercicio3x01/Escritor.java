package ejercicio3x01;

// código, el nombre, la fecha de nacimiento y la nacionalidad.

public class Escritor {
	
	private int code;
	private String name;
	private String nationality;
	
	public Escritor(int code, String name, String nationality) {
		this.code = code;
		this.name = name;
		this.nationality = nationality;
	}
	
	@Override
	public String toString() {
		return "code: " + code + " name: " + name + " nationality: " + nationality;
	}
	
	
}
