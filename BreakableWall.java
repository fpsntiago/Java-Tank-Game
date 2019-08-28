import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class BreakableWall extends TankObjects {
  private Shape blockShape;
  BufferedImage block;
  
  public BreakableWall(double x, double y, TankObjectID id) {
    super(x, y, id);
    theta = Math.toRadians(0);
    xxAxis = 32;
    yyAxis = 32;
    blockShape = new Rectangle2D.Double(x, y, xxAxis, yyAxis);
    
    TankGameImage loader = new TankGameImage();
    block = loader.loadImage("resources/breakable.png");
  }
  
  @Override
  public void clock() {
  
  }
  @Override
  public void create(Graphics graphics) {
    graphics.drawImage(block, (int)x, (int)y, xxAxis, yyAxis, null);
  }
  @Override
  public void createMini(Graphics graphics, int x, int y) {
    graphics.drawImage(block, (int)this.x / 8 + x, (int)this.y / 8 + y, xxAxis / 8, yyAxis / 8, null);
  }
  @Override
  public Shape getShape() {
    return blockShape;
  }
  @Override
  public Rectangle getRectangle() {
    return new Rectangle((int)x, (int)y, blockWidth, blockHeight);
  }
}
