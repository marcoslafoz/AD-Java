package videojuegos;

public class Videojuego {

	// atributos de un videojuego
	private int codigo;
	private String titulo;
	private int agno;
	private String desarrollador;
	private double precio;

	// Crea un videojuego a partir de 5 par�metros.
	public Videojuego(int codigo, String titulo, int agno, String desarrollador, double precio) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.agno = agno;
		this.desarrollador = desarrollador;
		this.precio = precio;
	}

	// Devuelve una cadena de caracteres con el estado del videojuego.
	@Override
	public String toString() {
		return "Videojuego [C�digo = " + codigo + ", T�tulo = " + titulo + ", A�o = " + agno + ", Desarrollador = "
				+ desarrollador + ", Precio = " + String.format("%.2f", precio) + "]";
	}

	// Devuelve el c�digo del videojuego.
	public int getCodigo() {
		return codigo;
	}

	// Devuelve el t�tulo del videojuego.
	public String getTitulo() {
		return titulo;
	}

	// Devuelve el a�o del videojuego.
	public int getAgno() {
		return agno;
	}

	// Devuelve el desarrollador del videojuego.
	public String getDesarrollador() {
		return desarrollador;
	}

	// Devuelve el precio del videojuego.
	public double getPrecio() {
		return precio;
	}

}
