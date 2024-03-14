package ejemplos;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import entrada.Teclado;

import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Filters.eq; 

public class ActualizarAmigo {

	public static void main(String[] args) {
		int codigo = Teclado.leerEntero("�C�digo? ");
		String titulo = Teclado.leerCadena("�T�tulo? ");
		String autor = Teclado.leerCadena("�Autor? ");
		int agno = Teclado.leerEntero("�A�o? ");
		String genero = Teclado.leerCadena("�G�nero? ");
		MongoClient cliente = new MongoClient();
		MongoDatabase bd = cliente.getDatabase("biblioteca");
		MongoCollection<Document> libros = bd.getCollection("libros");
		Bson filtro = eq("codigo", codigo);
		Bson modificaciones = combine(set("titulo", titulo),
		                              set("autor", autor),
		                              set("agno", agno),
		                              set("genero", genero));
		UpdateResult resultado = libros.updateOne(filtro, modificaciones);
		long librosActualizados = resultado.getModifiedCount();
		if (librosActualizados == 0) {
			System.out.println("No se ha encontrado ning�n libro con c�digo " + codigo + ".");
		}
		else {
			System.out.println("Se ha actualizado " + librosActualizados + " libro.");
		}
		cliente.close();
	}

}
