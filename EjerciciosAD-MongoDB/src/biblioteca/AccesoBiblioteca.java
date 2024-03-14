package biblioteca;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class AccesoBiblioteca {

	public static List<Libro> consultarLibros() {

		MongoClient cliente = null;
		List<Document> resultados = null;
		List<Libro> librosConsultados = new ArrayList<>();

		try {
			cliente = new MongoClient();
			MongoDatabase bd = cliente.getDatabase(ConfigDB.DB_NAME);
			MongoCollection<Document> libros = bd.getCollection("libros");
			resultados = libros.find().into(new ArrayList<Document>());
			
			for (Document d : resultados) {
				librosConsultados.add(new Libro(
						d.getInteger("codigo"),
						d.getString("titulo"),
						d.getInteger("agno"),
						d.getString("genero")
						));
			}

		} finally {
			cliente.close();
		}

		return librosConsultados;

	}

	///////////////// MOSTRAR MENU //////////////////
	public static String mostrarMenu() {
		return "(0) Salir del programa.\r\n" + "(1) Insertar un libro en la base de datos.\r\n"
				+ "(2) Consultar todos los libros de la base de datos.\r\n"
				+ "(3) Consultar un libro, por código, de la base de datos.\r\n"
				+ "(4) Actualizar un libro, por código, de la base de datos.\r\n"
				+ "(5) Eliminar un libro, por código, de la base de datos.\r\n";
	}
}
