package videojuegos;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public class AccesoVideojuegos {

	///////////////// MOSTRAR MENU //////////////////
	public static String mostrarMenu() {
		return "\r\n(0) Salir del programa.\r\n" + "(1) Insertar un videojuego en la base de datos.\r\n"
				+ "(2) Consultar todos los videojuegos de la base de datos.\r\n"
				+ "(3) Consultar un videojuego, por c�digo, de la base de datos.\r\n"
				+ "(4) Actualizar un videojuego, por c�digo, de la base de datos.\r\n"
				+ "(5) Eliminar un videojuego, por c�digo, de la base de datos.";
	}

	//////////////////// CONSULTAR TODOS //////////////////////
	public static List<Videojuego> consultarTodos() throws Exception {

		MongoClient cliente = null;
		List<Document> resultadosDoc = null;
		List<Videojuego> resultados = new ArrayList<>();

		try {
			cliente = new MongoClient();
			MongoDatabase bd = cliente.getDatabase(ConfigDB.DB_NAME);
			MongoCollection<Document> collection = bd.getCollection(ConfigDB.COLLECTION_NAME_1);
			resultadosDoc = collection.find().into(new ArrayList<Document>());

			for (Document d : resultadosDoc) {
				resultados.add(new Videojuego(d.getInteger("codigo"), d.getString("titulo"), d.getInteger("agno"),
						d.getString("desarrollador"), d.getDouble("precio")));
			}

		} finally {
			if (cliente != null) {
				cliente.close();
			}
		}

		return resultados;

	}

	//////////////////// CONSULTAR ELEMENTO POR CODIGO //////////////////////
	public static Videojuego consultarElementoPorCodigo(int codigo) throws Exception {
		Videojuego elemento = null;
		MongoClient cliente = null;

		try {
			cliente = new MongoClient();
			MongoDatabase bd = cliente.getDatabase(ConfigDB.DB_NAME);
			MongoCollection<Document> collection = bd.getCollection(ConfigDB.COLLECTION_NAME_1);

			Document elementoDoc = collection.find(eq("codigo", codigo)).first();

			if (elementoDoc != null) {
				elemento = new Videojuego(elementoDoc.getInteger("codigo"), elementoDoc.getString("titulo"),
						elementoDoc.getInteger("agno"), elementoDoc.getString("desarrollador"),
						elementoDoc.getDouble("precio"));
			}
		} finally {
			if (cliente != null) {
				cliente.close();
			}
		}

		return elemento;
	}

	//////////////////// INSERTAR UNO //////////////////////
	public static boolean insertarElemento(Videojuego elementoInsertar) throws Exception {
		boolean esInsertado = false;
		MongoClient cliente = null;

		try {
			cliente = new MongoClient();
			MongoDatabase bd = cliente.getDatabase(ConfigDB.DB_NAME);
			MongoCollection<Document> collection = bd.getCollection(ConfigDB.COLLECTION_NAME_1);

			Videojuego elementoEncontrado = consultarElementoPorCodigo(elementoInsertar.getCodigo());

			if (elementoEncontrado == null) {
				Document elementoDoc = new Document();
				elementoDoc.put("codigo", elementoInsertar.getCodigo());
				elementoDoc.put("titulo", elementoInsertar.getTitulo());
				elementoDoc.put("agno", elementoInsertar.getAgno());
				elementoDoc.put("desarrollador", elementoInsertar.getDesarrollador());
				elementoDoc.put("precio", elementoInsertar.getPrecio());

				collection.insertOne(elementoDoc);
				esInsertado = true;
			}

		} finally {
			if (cliente != null) {
				cliente.close();
			}
		}

		return esInsertado;
	}

	//////////////////// ELIMINAR ELEMENTO POR CODIGO //////////////////////
	public static long eliminarElementoPorCodigo(int codigo) throws Exception {

		long nElementosEliminados = 0;
		MongoClient cliente = null;

		try {
			cliente = new MongoClient();
			MongoDatabase bd = cliente.getDatabase(ConfigDB.DB_NAME);
			MongoCollection<Document> collection = bd.getCollection(ConfigDB.COLLECTION_NAME_1);

			DeleteResult resultado = collection.deleteOne(eq("codigo", codigo));
			nElementosEliminados = resultado.getDeletedCount();

		} finally {
			if (cliente != null) {
				cliente.close();
			}
		}

		return nElementosEliminados;
	}

	//////////////////// ACTUALIZAR ELEMENTO //////////////////////
	public static long actualizarElemento(Videojuego elementoActualizar) throws Exception {

		long nElementosActualizados = 0;
		MongoClient cliente = null;

		try {
			cliente = new MongoClient();
			MongoDatabase bd = cliente.getDatabase(ConfigDB.DB_NAME);
			MongoCollection<Document> collection = bd.getCollection(ConfigDB.COLLECTION_NAME_1);

			Bson filtro = eq("codigo", elementoActualizar.getCodigo());

			Bson modificaciones = combine(set("titulo", elementoActualizar.getTitulo()),
					set("agno", elementoActualizar.getAgno()),
					set("desarrollador", elementoActualizar.getDesarrollador()),
					set("precio", elementoActualizar.getPrecio()));

			UpdateResult resultado = collection.updateOne(filtro, modificaciones);
			nElementosActualizados = resultado.getModifiedCount();

		} finally {
			if (cliente != null) {
				cliente.close();
			}
		}

		return nElementosActualizados;
	}

	//////////////////// CONSULTAR CODIGO M�XIMO //////////////////////
	public static int consultarCodigoMaximo() throws Exception {
		MongoClient cliente = null;
		int codigoMaximo = 0;

		try {
			cliente = new MongoClient();
			MongoDatabase bd = cliente.getDatabase(ConfigDB.DB_NAME);
			MongoCollection<Document> collection = bd.getCollection(ConfigDB.COLLECTION_NAME_1);

			Document maxCodigoDoc = collection.find().sort(descending("codigo")).first();
			if (maxCodigoDoc != null) {
				codigoMaximo = maxCodigoDoc.getInteger("codigo");
			}

		} finally {
			if (cliente != null) {
				cliente.close();
			}
		}

		return codigoMaximo;
	}

	//////////////////// CONSULTAR ELEMENTOS FILTRADOS ////////////////////
	public static List<Videojuego> consultarElementosFiltrados(double minPrecio, double maxPrecio) throws Exception {
		MongoClient cliente = null;
		List<Videojuego> resultados = new ArrayList<>();

		try {
			cliente = new MongoClient();
			MongoDatabase bd = cliente.getDatabase(ConfigDB.DB_NAME);
			MongoCollection<Document> collection = bd.getCollection(ConfigDB.COLLECTION_NAME_1);

			Bson filtroPrecio = and(gt("precio", minPrecio), lt("precio", maxPrecio));
			MongoCursor<Document> cursor = collection.find(filtroPrecio).iterator();

			while (cursor.hasNext()) {
				Document elementoDoc = cursor.next();
				Videojuego elemento = new Videojuego(elementoDoc.getInteger("codigo"), elementoDoc.getString("titulo"),
						elementoDoc.getInteger("agno"), elementoDoc.getString("desarrollador"),
						elementoDoc.getDouble("precio"));
				resultados.add(elemento);
			}

		} finally {
			if (cliente != null) {
				cliente.close();
			}
		}

		return resultados;
	}

	//////////////////// FILTRAR ELEMENTOS POR A�O //////////////////////
	public static List<Videojuego> consultarPorAgno(int agno) throws Exception {
		MongoClient cliente = null;
		List<Videojuego> resultados = new ArrayList<>();

		try {
			cliente = new MongoClient();
			MongoDatabase bd = cliente.getDatabase(ConfigDB.DB_NAME);
			MongoCollection<Document> collection = bd.getCollection(ConfigDB.COLLECTION_NAME_1);

			Bson filtro = eq("agno", agno);
			MongoCursor<Document> cursor = collection.find(filtro).iterator();

			while (cursor.hasNext()) {
				Document elementoDoc = cursor.next();

				resultados.add(new Videojuego(elementoDoc.getInteger("codigo"), elementoDoc.getString("titulo"),
						elementoDoc.getInteger("agno"), elementoDoc.getString("desarrollador"),
						elementoDoc.getDouble("precio")));
			}

		} finally {
			if (cliente != null) {
				cliente.close();
			}
		}

		return resultados;
	}
}
