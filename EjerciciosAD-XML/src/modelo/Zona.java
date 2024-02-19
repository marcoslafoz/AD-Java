package modelo;

public class Zona {

	// atributos de una zona
	private int codigo;
	private String nombre;
	private String director;
	
	// Crea una zona a partir de 3 parámetros.
	public Zona(int codigo, String nombre, String director) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.director = director;
	}
	
	// Crea una zona a partir de 1 parámetro.
	public Zona(String elemento) {
		String texto = extraerTexto(elemento, "codigo");
		this.codigo = Integer.parseInt(texto);
		this.nombre = extraerTexto(elemento, "nombre");
		this.director = extraerTexto(elemento, "director");
	}
	
	// Extrae el texto correspondiente a una etiqueta de un elemento XML. 
	private static String extraerTexto(String elemento, String etiqueta) {
		String marcaInicio = "<" + etiqueta + ">";
		String marcaFin = "</" + etiqueta + ">";
		int posicionInicio = elemento.indexOf(marcaInicio) + marcaInicio.length();
		int posicionFin = elemento.indexOf(marcaFin);
		String texto = elemento.substring(posicionInicio, posicionFin);
		return texto;
	}
	
	// Devuelve una cadena de caracteres con el estado de la zona.
	@Override
	public String toString() {
		return 
			"Zona [Código = " + codigo + 
			", Nombre = " + nombre + 
			", Director = " + director +
			"]";
	}

	// Devuelve el código de la zona.
	public int getCodigo() {
		return codigo;
	}

	// Devuelve el nombre de la zona.
	public String getNombre() {
		return nombre;
	}

	// Devuelve el director de la zona.
	public String getDirector() {
		return director;
	}
	
}
