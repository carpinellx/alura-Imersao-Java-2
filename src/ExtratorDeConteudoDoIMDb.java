import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDoIMDb {
    
    public List<conteudo> extraiConteudos(String json) {
        // Extrair só os dados que interessam (titulo, poster, classificação) 
        
        var parser = new JsonParser();
        List<Map<String, String>> ListaDeAtributos = parser.parse(json); 

        List<conteudo> conteudos = new ArrayList<>();

        // popular a lista de conteudos
        for (Map<String, String> atributos : ListaDeAtributos) {

            String tituloCompleto = atributos.get("fullTitle");
            String urlImagem = atributos.get("image");
            var conteudo = new conteudo(tituloCompleto, urlImagem);

            conteudos.add(conteudo);
        }

        return conteudos;

    }
    
}
