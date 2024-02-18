package ejercicio2x02;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Empleado {
    private int codigo;
    private int codigoDepartamento;
    private String nombre;
    private Date fechaAlta;
    private double salario;

    public Empleado(int codigo, int codigoDepartamento, String nombre, Date fechaAlta, double salario) {
        this.codigo = codigo;
        this.codigoDepartamento = codigoDepartamento;
        this.nombre = nombre;
        this.fechaAlta = fechaAlta;
        this.salario = salario;
    }


    
    public Empleado(String linea) {
        String[] partes = linea.split(";");
        this.codigo = Integer.parseInt(partes[0]);
        this.codigoDepartamento = Integer.parseInt(partes[1]);
        this.nombre = partes[2];

        try {
            this.fechaAlta = new SimpleDateFormat("dd/MM/yyyy").parse(partes[3]);
            this.salario = new DecimalFormat("#,##0.00").parse(partes[4]).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return "Empleado [Código = " + codigo + ", CódigoDepartamento = " + codigoDepartamento
                + ", Nombre = " + nombre + ", FechaAlta = " + new SimpleDateFormat("dd/MM/yyyy").format(fechaAlta)
                + ", Salario = " + df.format(salario) + " €]";
    }

    public String toStringWithSeparators() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return codigo + ";" + codigoDepartamento + ";" + nombre + ";" + dateFormat.format(fechaAlta)
                + ";" + df.format(salario).replace(",", ".");
    }



	public int getCodigo() {
		return codigo;
	}



	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}



	public void setCodigoDepartamento(int codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}



	public void setSalario(double salario) {
		this.salario = salario;
	}
}
