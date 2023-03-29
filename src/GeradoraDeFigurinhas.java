import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class GeradoraDeFigurinhas {
    

    public void cria(InputStream inputStream, String nomeArquivo) throws IOException {

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

        // configurar a fonte

        var fonte = new Font("SANS_SERIF", Font.BOLD, 100);
        graphics.setColor(Color.RED);
        graphics.setFont(fonte);

        // escrever uma frase na nova imagem 

        graphics.drawString("Maneiro", 100 , NovaAltura - 100);

        // escrever a nova imagem em um arquivo

        ImageIO.write(NovaImagem, "png", new File(nomeArquivo));



    }

}
