import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class GeradoraDeFigurinhas {
    

    public void cria(InputStream inputStream, String nomeArquivo, String texto, InputStream InputStreamSobreposicao) throws IOException {

        // leitura da imagem 
        // InputStream inputStream = new FileInputStream(new File("alura-Stickers/Entrada/filme.jpg"));
        // InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();

        BufferedImage ImagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memória com transferência e com tamanho novo

        int largura = ImagemOriginal.getWidth();
        int altura = ImagemOriginal.getHeight();
        int NovaAltura = altura + 200;
        BufferedImage NovaImagem = new BufferedImage(largura, NovaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original pra novo imagem (em memória)

        Graphics2D graphics = (Graphics2D) NovaImagem.getGraphics();
        graphics.drawImage(ImagemOriginal, 0, 0, null );

        BufferedImage ImagemSobreposicao = ImageIO.read(InputStreamSobreposicao);
        int imageSeloY = NovaAltura - ImagemSobreposicao.getHeight();
        graphics.drawImage(ImagemSobreposicao, 0, imageSeloY, null );

        // configurar a fonte

        var fonte = new Font("Impact", Font.BOLD, 100);
        graphics.setColor(Color.BLACK);
        graphics.setFont(fonte);

        // escrever uma frase na nova imagem 
        

        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
        int larguraTexto = (int) retangulo.getWidth();

        int posicaoTextoX = (largura - larguraTexto) / 2;
        int posicaoTextoY = (NovaAltura - 100);

        graphics.drawString(texto, posicaoTextoX , NovaAltura - 100);


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


        // escrever a nova imagem em um arquivo

        ImageIO.write(NovaImagem, "png", new File(nomeArquivo));



    }

}
