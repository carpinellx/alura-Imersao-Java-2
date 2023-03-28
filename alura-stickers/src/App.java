import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // Fazer uma conexão HTTP e buscar os top 250 filmes

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
    

        // Extrair só os dados que interessam (titulo, poster, classificação) 

        var parser = new JsonParser();
        List<Map<String, String>> ListaDeFilmes = parser.parse(body);
        

        // Exibir e manipular os dados

        for (Map<String,String> filme : ListaDeFilmes) {

            System.out.println("\u001b[1;44m Titulo: \u001b[m" + "\u001b[1;4;44m" + filme.get("fullTitle") + "\u001b[m");

            System.out.println("\u001b[1;42m Url do Poster: \u001b[m" + "\u001b[1;4;42m" + filme.get("image") + "\u001b[m");

            System.out.println("\u001b[1;41m Nota IMDb: \u001b[m" +"\u001b[1;4;41m " + filme.get("imDbRating") + " \u001b[m");

            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numeroEstrelas = (int) classificacao;
            for (int n = 1; n <= numeroEstrelas ; n++) {
                System.out.print("⭐️");
            }


            System.out.println("\n");
        }

    }

}