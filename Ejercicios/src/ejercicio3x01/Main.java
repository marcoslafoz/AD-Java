package ejercicio3x01;

public class Main {
	public static void main(String[] args) {
		
		boolean menu = true;
		int option;
		
		while(menu == true) {
			
			Utils.mostrarMenu();
			
			option = 1;
			
			switch(option) {
			case 0: //Salir
				break;
			default: 
				break;
			}
			
		}
		
		
		Escritor escritor1 = new Escritor(1, "Marcos", "España");
		System.out.println(escritor1.toString());
		
	}
}
