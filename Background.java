import java.awt.Graphics2D;
import java.awt.image.*;

public class Background {
  BufferedImage BG;
  BufferedImage GameBG;
  Graphics2D graphics2D;

  Background(){
    TankGameImage load = new TankGameImage();
    GameBG = load.loadImage("resources/graybackground.jpg");
    BG = new BufferedImage(2000, 1000, BufferedImage.TYPE_INT_RGB);
    graphics2D = (Graphics2D)BG.getGraphics();
    dimensions();
  }

  public BufferedImage getBackground()
  {
    return BG;
  }

  public void dimensions(){
    for(int x = 0; x < 2000; x += 300){
      for(int y = 0; y < 1000; y += 240){
        graphics2D.drawImage(GameBG, x, y, null);
      }
    }
  }

}