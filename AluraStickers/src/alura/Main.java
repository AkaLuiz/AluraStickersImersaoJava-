package alura;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws Exception{
		
		// fazer uma conexão HTTP e buscar os top 250 filmes
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
		String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
		URI endereco = URI.create(url);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();
		
		// extrair só os dados que interessam (titulo, poster, classificação)
		JsonParser parser = new JsonParser();
		List<Map<String, String>> listaDeFilmes = parser.parse(body);
		
		 // exibir e manipular os dados 
		 for (Map<String,String> filme : listaDeFilmes) {
			 	float estrelas = Float.parseFloat(filme.get("imDbRating"));
			 	int numEstrela = (int)estrelas;
			 	for(int i = 0; i < numEstrela; i++) {
			 		System.out.print("★");
			 	}
			 	System.out.println("");
	            System.out.println(filme.get("title"));
	            System.out.println(filme.get("image"));
	            System.out.println(filme.get("imDbRating"));
	            System.out.println();
	    }
	}
}
