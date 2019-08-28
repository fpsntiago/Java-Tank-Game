import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class UnbreakableWall extends TankObjects {
  private Shape newShape = null;
  BufferedImage Buffered;
  
  public UnbreakableWall(double x, double y, TankObjectID id) {
    super(x, y, id);
    theta = Math.toRadians(0);
    xxAxis = 32;
    yyAxis = 32;
    newShape = new Rectangle2D.Double(x, y, xxAxis, yyAxis);
    TankGameImage loader = new TankGameImage();
    Buffered = loader.loadImage("resources/wall.png");
  }
  
  @Override
  public void clock(){}
  @Override
  public void create(Graphics graphics) {
    graphics.drawImage(Buffered, (int)x, (int)y, xxAxis, yyAxis, null);
  }
  @Override
  public void createMini(Graphics graphics, int x, int y) {graphics.drawImage(Buffered, (int)this.x / 8 + x, (int)this.y / 8 + y, xxAxis / 8, yyAxis / 8, null);}
  @Override
  public Rectangle getRectangle() {
    return new Rectangle((int)x, (int)y, blockWidth, blockHeight);
  }
  @Override
  public Shape getShape() {
    return newShape;
  }
}