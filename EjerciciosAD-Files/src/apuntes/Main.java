package apuntes;

public class Main {
	public static void main(String[] args)  {
		
		//Dividir cadena en partes
		
		String cadena = "   111;222    ";

		String[] partes = (cadena.trim()).split(";");
		
		int n1 = Integer.parseInt(partes[0]);
		int n2 = Integer.parseInt(partes[1]);
		
		System.out.println(n1+n2);
		
		//Char to String
		
		char caracter = 'a';
		String cadena2 = String.valueOf(caracter);
		System.out.println(cadena2 + caracter);
	}
}
