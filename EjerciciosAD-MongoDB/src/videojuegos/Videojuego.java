package videojuegos;

public class Videojuego {

	// atributos de un videojuego
	private int codigo;
	private String titulo;
	private int agno;
	private String desarrollador;
	private double precio;

	// Crea un videojuego a partir de 5 parámetros.
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
		return "Videojuego [Código = " + codigo + ", Título = " + titulo + ", Año = " + agno + ", Desarrollador = "
				+ desarrollador + ", Precio = " + String.format("%.2f", precio) + "]";
	}

	// Devuelve el código del videojuego.
	public int getCodigo() {
		return codigo;
	}

	// Devuelve el título del videojuego.
	public String getTitulo() {
		return titulo;
	}

	// Devuelve el año del videojuego.
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
