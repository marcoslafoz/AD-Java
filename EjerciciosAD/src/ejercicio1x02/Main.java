package ejercicio1x02;

public class Main {
	public static void main(String[] args) {

		String[] linea = Utils.leerArchivo("data/entrada.txt");

		Utils.mostrarStats(linea);
	}
}