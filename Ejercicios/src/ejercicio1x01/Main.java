package ejercicio1x01;

public class Main {

	//Método que leer un fichero
    public static void main(String[] args) {
        
        String[] linea = Services.leerArchivo("data/entrada.txt"); 
        Services.escribirArchivo(linea, "data/salida.txt");
        
    }
}
