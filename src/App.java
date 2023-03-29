import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
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
        
        var diretorio = new File("Figurinhas/");
        diretorio.mkdir();

        // Exibir e manipular os dados

        var geradora = new GeradoraDeFigurinhas();
        for (Map<String,String> filme : ListaDeFilmes) {

            String urlImagem = filme.get("image");
            String fulltitle = filme.get("fullTitle");


            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "Figurinhas/" + fulltitle.replaceAll(":", "") + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println("\u001b[1;31m Titulo: \u001b[m" + filme.get("fullTitle"));

            System.out.println();
        }

    }

}