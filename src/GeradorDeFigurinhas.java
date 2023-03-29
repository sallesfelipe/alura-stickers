import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {

  public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

    // leitura da imagem

    BufferedImage imagemOriginal = ImageIO.read(inputStream);

    // cria nova imagem com transparencia
    int largura = imagemOriginal.getWidth();
    int altura = imagemOriginal.getHeight();
    int novaAltura = altura + 200;

    BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

    // copiar a imagem original pra nova img
    Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
    graphics.drawImage(imagemOriginal, 0, 0, null);

    // escrever na imagem
    var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 72);
    graphics.setFont(fonte);
    graphics.setColor(Color.YELLOW);
    graphics.drawString("TOPZERA", largura / 4, novaAltura - 100);

    // verificando se ja existe a pasta e criando caso nao exista
    var theDir = new File("saida");
    if (!theDir.exists()) {
      theDir.mkdirs();
    }

    // escrever a nova imagem em arquivo
    ImageIO.write(novaImagem, "png", new File(nomeArquivo));

  }
}
