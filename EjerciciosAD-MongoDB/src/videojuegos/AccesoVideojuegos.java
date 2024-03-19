package videojuegos;

public class AccesoVideojuegos {

	///////////////// MOSTRAR MENU //////////////////
	public static String mostrarMenu() {
		return "\r\n(0) Salir del programa.\r\n"
				+ "(1) Insertar un videojuego en la base de datos.\r\n"
				+ "(2) Consultar todos los videojuegos de la base de datos.\r\n"
				+ "(3) Consultar un videojuego, por código, de la base de datos.\r\n"
				+ "(4) Actualizar un videojuego, por código, de la base de datos.\r\n"
				+ "(5) Eliminar un videojuego, por código, de la base de datos.";
	}
}
