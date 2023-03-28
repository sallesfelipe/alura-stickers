import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {
  
    public void cria() throws Exception{

      //leitura da imagem
      BufferedImage imagemOriginal = ImageIO.read(new File("entrada/filme.jpg"));
      
      //cria nova imagem com transparencia
      int largura = imagemOriginal.getWidth();
      int altura = imagemOriginal.getHeight();
      int novaAltura = altura + 200;

      BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

      //copiar a imagem original pra nova img
      Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
      graphics.drawImage(imagemOriginal, 0, 0, null);

      //verificando se ja existe a pasta e criando caso nao exista
      var theDir = new File("saida");
      if (!theDir.exists()) {
      theDir.mkdirs();
      }

      //escrever a nova imagem em arquivo
      ImageIO.write(novaImagem, "png",  new File("saida/figurinha.png"));      
    
    }

    public static void main(String[] args) throws Exception {
      GeradorDeFigurinhas geradora = new GeradorDeFigurinhas();
      geradora.cria();
    }
  
}
