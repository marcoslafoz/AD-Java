package biblioteca;

public class Libro {

	// atributos de un videojuego
	private int codigo;
	private String titulo;
	private int agno;
	private String genero;

	// Crea un libro a partir de 4 paramentros.
	public Libro(int codigo, String titulo, int agno, String genero) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.agno = agno;
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Libro [Código = " + codigo + ", Título = " + titulo + ", Año = " + agno + ", Género = " + genero + "]";
	}

	public int getCodigo() {
		return codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public int getAgno() {
		return agno;
	}

	public String getGenero() {
		return genero;
	}

}