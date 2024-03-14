package ejemplos;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entrada.Teclado;

import static com.mongodb.client.model.Filters.eq;

public class InsertarLibro2 {

	public static void main(String[] args) {
		int codigo = Teclado.leerEntero("¿Código? ");
		MongoClient cliente = new MongoClient();
		MongoDatabase bd = cliente.getDatabase("biblioteca");
		MongoCollection<Document> libros = bd.getCollection("libros");
		Document libroEncontrado = libros.find(eq("codigo", codigo))
		                                 .first();
		if (libroEncontrado == null) {
			String titulo = Teclado.leerCadena("¿Título? ");
			String autor = Teclado.leerCadena("¿Autor? ");
			int agno = Teclado.leerEntero("¿Año? ");
			String genero = Teclado.leerCadena("¿Género? ");
			Document libro = new Document();
			libro.put("codigo", codigo);
			libro.put("titulo", titulo);
			libro.put("autor", autor);
			libro.put("agno", agno);
			libro.put("genero", genero);
			libros.insertOne(libro);
			System.out.println("Se ha insertado un libro con código " + codigo + ".");
		}
		else {
			System.out.println("Se ha encontrado un libro con código " + codigo + ".");
		}
		cliente.close();
	}

}
