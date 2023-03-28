import java.io.FileInputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws Exception {
        // String url =
        // "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream("./properties/conf.properties");
        prop.load(file);
        String url = prop.getProperty("url");

        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        String ANSI_GREEN = "\u001B[32m";
        String ANSI_WHITE = "\u001B[37m";
        String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
        String ANSI_BLUE_BACKGROUND = "\u001B[44m";
        String ANSI_RESET = "\u001B[0m";

        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println(ANSI_GREEN +
                    ANSI_BLUE_BACKGROUND +
                    "Título: " +
                    filme.get("title") +
                    ANSI_RESET);
            System.out.println(
                    ANSI_PURPLE_BACKGROUND +
                            ANSI_WHITE +
                            "Classificação: " +
                            filme.get("imDbRating") + ANSI_RESET);
            System.out.println("Poster: " + filme.get("image"));

        }
    }
}
