package unidad1.ejercicio3x02;

import java.io.Serializable;

public class Libro implements Serializable {
    private int codigo;
    private int codigoEscritor;
    private String titulo;
    private int añoPublicacion;
    private double precio;

    public Libro(int codigo, int codigoEscritor, String titulo, int añoPublicacion, double precio) {
        this.codigo = codigo;
        this.codigoEscritor = codigoEscritor;
        this.titulo = titulo;
        this.añoPublicacion = añoPublicacion;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Libro [Código = " + codigo + ", CódigoEscritor = " + codigoEscritor + ", Título = " + titulo +
               ", AñoPublicación = " + añoPublicacion + ", Precio = " + String.format("%.2f", precio) + " €]";
    }

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigoEscritor() {
		return codigoEscritor;
	}

	public void setCodigoEscritor(int codigoEscritor) {
		this.codigoEscritor = codigoEscritor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAñoPublicacion() {
		return añoPublicacion;
	}

	public void setAñoPublicacion(int añoPublicacion) {
		this.añoPublicacion = añoPublicacion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
}