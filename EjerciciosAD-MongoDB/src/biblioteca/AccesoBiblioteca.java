package biblioteca;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class AccesoBiblioteca {

	//////////////////// CONSULTAR TODOS LOS LIBROS //////////////////////
	public static List<Libro> consultarLibros() throws Exception {

		MongoClient cliente = null;
		List<Document> resultadosDocument = null;
		List<Libro> librosConsultados = new ArrayList<>();

		try {
			cliente = new MongoClient();
			MongoDatabase bd = cliente.getDatabase(ConfigDB.DB_NAME);
			MongoCollection<Document> libros = bd.getCollection("libros");
			resultadosDocument = libros.find().into(new ArrayList<Document>());

			for (Document d : resultadosDocument) {
				librosConsultados.add(new Libro(d.getInteger("codigo"), d.getString("titulo"), d.getInteger("agno"),
						d.getString("genero")));
			}

		} finally {
			cliente.close();
		}

		return librosConsultados;

	}

	//////////////////// INSERTAR LIBRO //////////////////////
	public static boolean insertarLibro(Libro libroInsertar) throws Exception {
		boolean libroInsertado = false;
		MongoClient cliente = null;

		try {
			cliente = new MongoClient();
			MongoDatabase bd = cliente.getDatabase(ConfigDB.DB_NAME);
			MongoCollection<Document> libros = bd.getCollection("libros");

			Libro libroEncontrado = consultarLibroPorCodigo(libroInsertar.getCodigo());

			if (libroEncontrado == null) {
				Document libroDocument = new Document();
				libroDocument.put("codigo", libroInsertar.getCodigo());
				libroDocument.put("titulo", libroInsertar.getTitulo());
				libroDocument.put("agno", libroInsertar.getAgno());
				libroDocument.put("genero", libroInsertar.getGenero());
				libros.insertOne(libroDocument);
				libroInsertado = true;
			}

		} finally {
			cliente.close();
		}

		return libroInsertado;
	}

	//////////////////// CONSULTAR LIBRO POR CODIGO //////////////////////
	public static Libro consultarLibroPorCodigo(int codigo) throws Exception {

		Libro libroConsultado;
		MongoClient cliente = null;

		try {
			cliente = new MongoClient();
			MongoDatabase bd = cliente.getDatabase(ConfigDB.DB_NAME);
			MongoCollection<Document> libros = bd.getCollection("libros");

			Document libroDoc = libros.find(eq("codigo", codigo)).first();

			libroConsultado = new Libro(libroDoc.getInteger("codigo"), libroDoc.getString("titulo"),
					libroDoc.getInteger("agno"), libroDoc.getString("genero"));

		} finally {
			cliente.close();
		}

		return libroConsultado;
	}

	//////////////////// ELIMINAR LIBRO POR CODIGO //////////////////////
	public static long eliminarLibroPorCodigo(int codigo) throws Exception {

		long nLibrosEliminado = 0;
		MongoClient cliente = null;

		try {
			cliente = new MongoClient();
			MongoDatabase bd = cliente.getDatabase(ConfigDB.DB_NAME);
			MongoCollection<Document> libros = bd.getCollection("libros");

			DeleteResult resultado = libros.deleteOne(eq("codigo", codigo));
			nLibrosEliminado = resultado.getDeletedCount();

		} finally {
			cliente.close();
		}

		return nLibrosEliminado;
	}

	//////////////////// ACTUALIZAR LIBRO //////////////////////
	public static long actualizarLibro(Libro libro) throws Exception {

		long nLibrosActualizados = 0;
		MongoClient cliente = null;

		try {
			cliente = new MongoClient();
			MongoDatabase bd = cliente.getDatabase(ConfigDB.DB_NAME);
			MongoCollection<Document> libros = bd.getCollection("libros");

			Bson filtro = eq("codigo", libro.getCodigo());
			
			Bson modificaciones = combine(set("titulo", libro.getTitulo()), set("agno", libro.getAgno()),
					set("genero", libro.getGenero()));
			
			UpdateResult resultado = libros.updateOne(filtro, modificaciones);
			nLibrosActualizados = resultado.getModifiedCount();

		} finally {
			cliente.close();
		}

		return nLibrosActualizados;
	}

	///////////////// MOSTRAR MENU //////////////////
	public static String mostrarMenu() {
		return "\r\n(0) Salir del programa.\r\n" + "(1) Insertar un libro en la base de datos.\r\n"
				+ "(2) Consultar todos los libros de la base de datos.\r\n"
				+ "(3) Consultar un libro, por código, de la base de datos.\r\n"
				+ "(4) Actualizar un libro, por código, de la base de datos.\r\n"
				+ "(5) Eliminar un libro, por código, de la base de datos.\r\n";
	}
}
