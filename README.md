# Imersão Java 2 | 27/03/2023

## Aula 1 - CONSUMINDO UMA API DE FILMES COM JAVA

Utilizando Java no VS Code para acessar e consumir API do IMDB/alura (Top 10 Filmes).

[App](https://github.com/carpinellx/alura-Imersao-Java-2/blob/main/src/App.java) - Aplicação principal, acessando e exibindo os dados da API.

[Parser](https://github.com/carpinellx/alura-Imersao-Java-2/blob/main/src/JsonParser.java) - Classe para "parsear", filtrando as informações do arquivo Json

### Desafios Aula 1

1 - Consumir o endpoint de filmes mais populares da API do IMDB.

    String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";


2 - Configurando a estética da apresentação das informações.

    System.out.println("\u001b[1;44m Titulo: \u001b[m" + "\u001b[1;4;44m" + filme.get("fullTitle") + "\u001b[m");
    System.out.println("\u001b[1;41m Nota IMDb: \u001b[m" +"\u001b[1;4;41m " + filme.get("imDbRating") + " \u001b[m");
    double classificacao = Double.parseDouble(filme.get("imDbRating"));
    int numeroEstrelas = (int) classificacao;
    for (int n = 1; n <= numeroEstrelas ; n++) {
       System.out.print("⭐️");
    }

          System.out.println("\n");


3 - Utilizar a variavel de ambiente para esconder chave de acesso.

    No PowerShell:
    $env:IMDB_KEY = "Minha chave"                                         

    String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies/" + IMBD_KEY;  

## Resutado:
<p align="center">
  <img src="https://raw.githubusercontent.com/carpinellx/alura-Imersao-Java-2/main/Desafios/Aula1-Desafio/Aula1-Desafio.PNG" width="500" height="250"/>
</p>


## Aula 2 - GERANDO FIGURINHAS PARA WHATSAPP | 28/03/2023

[Gerador de Stickers](https://github.com/carpinellx/alura-Imersao-Java-2/blob/main/src/GeradoraDeFigurinhas.java) - Classe geradora de figurinhas para WhatsApp.

### Desafios Aula 2

1 - Criar diretório de saída das imagens, se ainda não existir.

    var diretorio = new File("Figurinhas/");
    diretorio.mkdir();                                 


2 - Centralizando o texto na nova imagem.

    String texto = "Mídia";
    FontMetrics fontMetrics = graphics.getFontMetrics();
    Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
    int larguraTexto = (int) retangulo.getWidth();
    
    int posicaoTextoX = (largura - larguraTexto) / 2;
    int posicaoTextoY = (NovaAltura - 100);

    graphics.drawString(texto, posicaoTextoX , NovaAltura - 100);


3 - Colocar outra fonte como a Comic Sans ou a Impact, a fonte usada em memes.

    var fonte = new Font("Impact", Font.BOLD, 100);


4 - Colocar contorno (outline) no texto da imagem.


    FontRenderContext  fontRenderContext = graphics.getFontRenderContext();
    var textLayout = new TextLayout(texto, fonte, fontRenderContext);

    Shape outline = textLayout.getOutline(null);
    AffineTransform transform = graphics.getTransform();
    transform.translate(posicaoTextoX, posicaoTextoY);
    graphics.setTransform(transform);
    
    var outlineStroke = new BasicStroke(largura * 0.004f);
    graphics.setStroke(outlineStroke);

    graphics.setColor(Color.WHITE);
    graphics.draw(outline);
    graphics.setClip(outline);
        
        
5 - Modificando texto de acordo com o Rating.

    if (catalogacao >= 9.0) {
        textoFigurinha = "Esse é Mídia";
            
    } else {
        textoFigurinha = "< CSA x Ipatinga";
    }


    String nomeArquivo = "Figurinhas/" + fulltitle.replaceAll(":", "") + ".png";


    public void cria(InputStream inputStream, String nomeArquivo, String texto, InputStream InputStreamSobreposicao) 
    throws IOException {
      
    if (catalogacao >= 8.9) {
        textoFigurinha = "Mídia";
        ImagemSelo = new FileInputStream(new File("selos/aprovado.png"));
    } else {
        textoFigurinha = "Fraco";
        ImagemSelo = new FileInputStream(new File("selos/reprovado.png"));
    }


    Graphics2D graphics = (Graphics2D) NovaImagem.getGraphics();
    graphics.drawImage(ImagemOriginal, 0, 0, null );
        
    BufferedImage ImagemSobreposicao = ImageIO.read(InputStreamSobreposicao);
    int imageSeloY = NovaAltura - ImagemSobreposicao.getHeight();

    graphics.drawImage(ImagemSobreposicao, 0, imageSeloY, null );


## Resultados

<p align="center">
  <img src="https://raw.githubusercontent.com/carpinellx/alura-Imersao-Java-2/main/Desafios/Aula2-Desafios/PulpFiction(1994).png" width="500" height="450"/>
  <img src="https://raw.githubusercontent.com/carpinellx/alura-Imersao-Java-2/main/Desafios/Aula2-Desafios/TheLordOfTheRings(2001).png" width="500" height="450"/>
</p>


## Aula 3 | 29/03/2023

Refatoração do [App](https://github.com/carpinellx/alura-Imersao-Java-2/blob/main/src/App.java), em classes.

Criando classes para:

- [Conteudo](https://github.com/carpinellx/alura-Imersao-Java-2/blob/main/src/conteudo.java)

- [ClienteHttp](https://github.com/carpinellx/alura-Imersao-Java-2/blob/main/src/ClienteHTTP.java)

Criando uma [interface](https://github.com/carpinellx/alura-Imersao-Java-2/blob/main/src/ExtratorDeConteudo.java) para as novas classes extratoras:

- [ExtratorConteudoIMDB](https://github.com/carpinellx/alura-Imersao-Java-2/blob/main/src/ExtratorDeConteudoDoIMDb.java)

- [ExtratorConteudoNasa](https://github.com/carpinellx/alura-Imersao-Java-2/blob/main/src/ExtratorDeConteudoDaNasa.java)

### Desafios Aula 3


1 - Transformar a classe que representa os conteúdos em um Record.

    public record Conteudo(String titulo, String urlImagem){}
    
    
2 - Criar as suas próprias exceções e usá-las na classe que implementa o [ClienteHttpException](https://github.com/carpinellx/alura-Imersao-Java-2/blob/main/src/ClienteHttpException.java).


    public class ClienteHttpException  extends RuntimeException {

        public ClienteHttpException(String message) {
        super(message);
        }

    }

Alteração do [ClienteHttp](https://github.com/carpinellx/alura-Imersao-Java-2/blob/main/src/ClienteHTTP.java).


    catch (IOException | InterruptedException ex) {
        throw new ClienteHttpException("Erro ao consultar a URL.");

    }


3 - Mapear uma lista em uma outra.
 
 
    return ListaDeAtributos.stream()
    .map(atributos -> new conteudo(atributos.get("title"), atributos.get("url")))
    .toList();
 

4 - Criar uma Enum que une, como configurações, a URL da [API](https://github.com/carpinellx/alura-Imersao-Java-2/blob/main/src/API.java) e o extrator utilizado

    public enum API {
    IMDB_TOP_MOVEIS("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json",
    new ExtratorDeConteudoDoIMDb()),
    IMDB_MOST_POPULAR_TVS("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json",
    new ExtratorDeConteudoDoIMDb()),
    NASA("https://api.nasa.gov/planetary/apod?api_key=76hhyMbykQvFKN9SiygrkdHf56LZgg6zDo8HZaay&start_date=2022-06-12&end_date=2022-06-14",
    new ExtratorDeConteudoDaNasa());
    


    private String url;
    private ExtratorDeConteudo extrator;

    API(String url, ExtratorDeConteudo extrator) {
        this.url = url;
        this.extrator = extrator;
    }

    public String getUrl() {
        return url;
    }

    public ExtratorDeConteudo getExtrator() {
        return extrator;
    }

    }

## Resultados
<p align="center">
  <img src="https://github.com/carpinellx/alura-Imersao-Java-2/blob/main/Desafios/Aula3-Desafios/Find%20the%20Man%20in%20the%20Moon.png?raw=true).png" width="500" height="450"/>
  <img src="https://github.com/carpinellx/alura-Imersao-Java-2/blob/main/Desafios/Aula3-Desafios/The%20Boys%20(2019).png?raw=true" width="500" height="450"/>
  <img src="https://github.com/carpinellx/alura-Imersao-Java-2/blob/main/Desafios/Aula3-Desafios/The%20Dark%20Knight%20(2008).png?raw=true" width="500" height="450"/>
</p>

## Aula 4 | 30/03/2023

## Aula 5 | 31/03/2023
