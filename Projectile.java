import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Projectile extends TankObjects {
  private int Speed;
  private BufferedImage shell;
  private int panel;
  private double xaxis;
  private double yaxis;
  TankDriver driver;
  TankObjectID enemy;
  
  TankGameSounds soundPlayer;
  private String hit = "resources/hit.wav";
  
  public Projectile(double x, double y, double a, int speed, TankObjectID id, TankObjectID origin, TankDriver driver) {
    super(x, y, id);
    this.driver = driver;
    Speed = speed;
    xxAxis = 17;
    yyAxis = 17;
    theta = a;

    soundPlayer = new TankGameSounds();

    xaxis = Math.cos(theta) * Speed;
    yaxis = Math.sin(theta) * Speed;

    TankGameImage loader = new TankGameImage();
    BufferedImage projectile = loader.loadImage("resources/ProjectileStrip.png");
    panel = projectile.getWidth() / 60;
    shell = projectile.getSubimage(30 * panel, 0, panel, panel);
    
    if (origin == TankObjectID.Player1) {
      enemy = TankObjectID.Player2;
    } else {
      enemy = TankObjectID.Player1;
    }
  }
  @Override
  public void create(Graphics models) {
    Graphics2D graphics2D = (Graphics2D) models;

    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    AffineTransform update = graphics2D.getTransform();

    graphics2D.rotate(theta, x + xxAxis / 3, y + yyAxis / 3);
    graphics2D.drawImage(shell, (int)x, (int)y, xxAxis, yyAxis, null);
    graphics2D.setTransform(update);
  }
  @Override
  public void createMini(Graphics graphics, int x, int y) {
  }
  @Override
  public Shape getShape() {
    return new Rectangle2D.Double(x, y, xxAxis, yyAxis);
  }
  @Override
  public Rectangle getRectangle() {
    return new Rectangle((int)x, (int)y, xxAxis, yyAxis);
  }
  public void value_set(int speed){
    Speed = speed;
  }
  @Override
  public void clock() {
    x += xaxis;
    y += yaxis;
    
    for (int i = 0; i < driver.obj.size(); i++) {
      TankObjects tmpObj = driver.obj.get(i);
      
      //collision
      if (tmpObj.fetchObject() == TankObjectID.UnbreakableWall || tmpObj.fetchObject() == TankObjectID.BreakableWall ||
              tmpObj.fetchObject() == enemy) {
        if (getRectangle().intersects(tmpObj.getRectangle())) {
          driver.removeObject(this);

          if (tmpObj.fetchObject() == TankObjectID.BreakableWall) {
            driver.removeObject(tmpObj);
            soundPlayer.WAV(hit);
          }
          if (tmpObj.fetchObject() == TankObjectID.UnbreakableWall) {

            soundPlayer.WAV(hit);
          }
          if (tmpObj.fetchObject() == enemy) {
            tmpObj.setHealth(tmpObj.getHealth() - 20);
            soundPlayer.WAV(hit);
          }
        }
      }  
      
    }
    
  }




}