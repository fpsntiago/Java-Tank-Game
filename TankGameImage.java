import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class TankGameImage {
  private BufferedImage resource;
  
  public BufferedImage loadImage(String source) {
    try {
      resource = ImageIO.read(getClass().getResource(source));
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return resource;
  }
}