import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        API api = API.IMDB_MOST_POPULAR_TVS;       
        
        String url = api.getUrl();
        ExtratorDeConteudo extrator = api.getExtrator();

        var http = new ClienteHTTP();
        String json = http.buscaDados(url);
        
        // Exibir e manipular os dados

        List<conteudo> conteudos = extrator.extraiConteudos(json);
        
        var geradora = new GeradoraDeFigurinhas();

        var diretorio = new File("Figurinhas/");
        diretorio.mkdir();

        for (int i = 0; i < 3; i++) {

            conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = "Figurinhas/" + conteudo.titulo().replace(":","-" ) + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.titulo());

            System.out.println();
        }

    }

}