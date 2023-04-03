import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "https://raw.githubusercontent.com/franklin-albuquerque/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var cliente = HttpClient.newHttpClient();
        var solicitar = HttpRequest.newBuilder(endereco).GET().build();
        var resposta = cliente.send(solicitar, BodyHandlers.ofString());
        String corpo = resposta.body();

        var parser = new JsonParser();
        List<Map<String, String>> filmes = parser.parse(corpo);

        for(Map<String, String> filme : filmes) {
            System.out.printf("Título: %s%n", filme.get("title"));
            System.out.printf("Poster: %s%n", filme.get("image"));
            System.out.printf("Pontuação: %s%n", filme.get("imDbRating"));
            System.out.println();
        }
    }
}
