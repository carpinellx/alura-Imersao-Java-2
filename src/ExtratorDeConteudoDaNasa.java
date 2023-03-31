import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDaNasa implements ExtratorDeConteudo {
    
    public List<conteudo> extraiConteudos(String json) {

        // Extrair só os dados que interessam (titulo, poster, classificação) 
        var parser = new JsonParser();
        List<Map<String, String>> ListaDeAtributos = parser.parse(json); 

        return ListaDeAtributos.stream()
            .map(atributos ->new conteudo(atributos.get("title"), atributos.get("url")))
            .toList();
    }

}