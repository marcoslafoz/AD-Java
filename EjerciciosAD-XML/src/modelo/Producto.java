package modelo;

public class Producto {

	// atributos de un producto
	private int codigo;
	private String denominacion;
	private double precio;
	private int stockActual;
	private int stockMinimo;
	private int codigoZona;
	
	// Crea un producto a partir de 6 parámetros.
	public Producto(int codigo, String denominacion, double precio, 
	                int stockActual, int stockMinimo, int codigoZona) {
		this.codigo = codigo;
		this.denominacion = denominacion;
		this.precio = precio;
		this.stockActual = stockActual;
		this.stockMinimo = stockMinimo;
		this.codigoZona = codigoZona;
	}
	
	// Crea un producto a partir de 1 parámetro.
	public Producto(String elemento) {
		String texto = extraerTexto(elemento, "cod_prod");
		this.codigo = Integer.parseInt(texto);
		this.denominacion = extraerTexto(elemento, "denominacion");
		texto = extraerTexto(elemento, "precio");
		this.precio = Double.parseDouble(texto.replace(',', '.'));
		texto = extraerTexto(elemento, "stock_actual");
		this.stockActual = Integer.parseInt(texto);
		texto = extraerTexto(elemento, "stock_minimo");
		this.stockMinimo = Integer.parseInt(texto);
		texto = extraerTexto(elemento, "cod_zona");
		this.codigoZona = Integer.parseInt(texto);
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

	// Devuelve una cadena de caracteres con el estado del producto.
	@Override
	public String toString() {
		return 
			"Producto [Código = " + codigo + 
			", Denominación = " + denominacion + 
			", Precio = " + String.format("%.2f", precio) + 
			", StockActual = "+ stockActual + 
			", StockMínimo = " + stockMinimo + 
			", CódigoZona = " + codigoZona + 
			"]";
	}

	// Devuelve el código del producto.
	public int getCodigo() {
		return codigo;
	}

	// Devuelve la denominación del producto.
	public String getDenominacion() {
		return denominacion;
	}

	// Devuelve el precio del producto.
	public double getPrecio() {
		return precio;
	}

	// Devuelve el stock actual del producto.
	public int getStockActual() {
		return stockActual;
	}

	// Devuelve el stock mínimo del producto.
	public int getStockMinimo() {
		return stockMinimo;
	}

	// Devuelve el código de zona del producto.
	public int getCodigoZona() {
		return codigoZona;
	}
	
}
