import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Shape;

public abstract class TankObjects {
  public final int blockWidth = 32;
  public final int blockHeight = 32;
  
  double x, y, theta;
  int xxAxis;
  int yyAxis;
  int lifepoints;
  int mushrooms;
  private TankObjectID id;
  
  public TankObjects(double x, double y, TankObjectID id) {
    this.x = x;
    this.y = y;
    this.id = id;
  }

  public abstract void clock();
  //public abstract void clock1();
  public abstract void create(Graphics graphics);
  public abstract void createMini(Graphics graphics, int x, int y);
  public abstract Rectangle getRectangle();
  public abstract Shape getShape();
  public int getHealth() {
    return lifepoints;
  }
  public int getLives() {
    return mushrooms;
  }
  public double xCoordinate() {
    return x;
  }
  public double yCoordinate() {
    return y;
  }
  public void setHealth(int h) {
    this.lifepoints = h;
  }
  public TankObjectID fetchObject() {
    return id;
  }
}