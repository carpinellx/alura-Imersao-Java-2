import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // Fazer uma conexão HTTP e buscar os top 250 filmes


        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        ExtratorDeConteudoDoIMDb extrator = new ExtratorDeConteudoDoIMDb();
        


        // String url = "https://api.nasa.gov/planetary/apod?api_key=76hhyMbykQvFKN9SiygrkdHf56LZgg6zDo8HZaay&start_date=2022-06-12&end_date=2022-06-14";
        // ExtratorDeConteudoDaNasa extrator = new ExtratorDeConteudoDaNasa();


        var http = new ClienteHTTP();
        String json = http.buscaDados(url);
        
        var diretorio = new File("Figurinhas/");
        diretorio.mkdir();

        // Exibir e manipular os dados
        List<conteudo> conteudos = extrator.extraiConteudos(json);
        

        var geradora = new GeradoraDeFigurinhas();
        for (int i = 0; i < 3; i++) {

            conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "Figurinhas/" + conteudo.getTitulo().replace(":","-" ) + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println(conteudo.getUrlImagem());

            System.out.println();
        }

    }

}