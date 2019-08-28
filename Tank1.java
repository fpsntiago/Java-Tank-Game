import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Tank1 extends TankObjects {
  private double Rotation;
  private BufferedImage tank;
  private double horizontal = 0;
  private double  vertical= 0;
  private int panel;
  private int FireRate;
  TankDriver driver;
  //TankDriver driver2;
  
  TankGameSounds sounds;
  private String shotFired = "resources/pew pew.wav";
 // private String crash = "resources/crash.wav";

  @Override
  public void createMini(Graphics graphics, int x, int y) {
    graphics.drawImage(tank, (int)this.x / 8 + x, (int)this.y / 8 + y, xxAxis / 5, yyAxis / 5, null);
  }
  @Override
  public Rectangle getRectangle() {
    return new Rectangle((int)x, (int)y, xxAxis, yyAxis);
  }
  @Override
  public Shape getShape() {
    return new Rectangle2D.Double(x, y, xxAxis, yyAxis);
  }

  public Tank1(double x, double y, int a, TankObjectID id, TankDriver driver) {
    super(x, y, id);
    theta = Math.toRadians(a);
    xxAxis = 64;
    yyAxis = 64;
    Rotation = a;
    lifepoints = 100;
    mushrooms = 2;
    sounds = new TankGameSounds();
    
    TankGameImage loader = new TankGameImage();
    BufferedImage Sprite = loader.loadImage("resources/tankstrip1.png");
    panel = Sprite.getWidth() / 60;
    tank = Sprite.getSubimage(0, 0, panel, panel);

    FireRate = 0;
    this.driver = driver;

  }
  private void collision() {
    for (int i = 0; i < driver.obj.size(); i++) {
      TankObjects tmpObj = driver.obj.get(i);

      if (tmpObj.fetchObject() == TankObjectID.BreakableWall ||tmpObj.fetchObject() == TankObjectID.UnbreakableWall ||
              tmpObj.fetchObject() == TankObjectID.Player2 ) {
        if (getRectangle().intersects(tmpObj.getRectangle())) {
          //sounds.WAV(crash);
          x += horizontal * -2;
          y += vertical * -2;
        }
      }
      if(tmpObj.fetchObject() == TankObjectID.PowerUp1 || tmpObj.fetchObject() == TankObjectID.PowerUp2){
        if(getRectangle().intersects(tmpObj.getRectangle()))
          driver.removeObject(tmpObj);
        //x += horizontal * 2;
        //y += vertical * 2;

      }
    }
  }
  @Override
  public void clock() {
    theta = Math.toRadians(Rotation);
    
    if (lifepoints <= 0 && mushrooms > 0){
          mushrooms--;
          if(lifepoints != 0){
          lifepoints = 100;
          }else{
        driver.removeObject(this);
      }
    }
    //speed
    x += horizontal * 5;
    y += vertical * 5;
    collision();

    if (driver.isFiringPlayer()) {
      if (FireRate == 0) {
        driver.addObject(new Projectile(x + xxAxis / 2, y + yyAxis / 2, theta, 8, TankObjectID.Bullet, this.fetchObject(), driver));
        sounds.WAV(shotFired);

        FireRate = 90;
      }else {
        FireRate -= 1;
      }
    }
    else if (!driver.isFiringPlayer()) {
      if (FireRate > 0) {
        FireRate -= 1;
      }

    if (driver.isForwardPlayer()) {
      horizontal = Math.cos(theta);
      vertical = Math.sin(theta);
    } else if (!driver.isBackwardPlayer()) {
      horizontal = 0;
      vertical = 0;
    }
    if (driver.isBackwardPlayer()) {
      horizontal = -(Math.cos(theta));
      vertical = -(Math.sin(theta));
    } else if (!driver.isForwardPlayer()) {
      horizontal = 0;
      vertical = 0;
    }
    
    if (driver.isRightPlayer()) {
      Rotation += 2;
    } else if (!driver.isLeftPlayer()) {
      Rotation += 0;
    }

    if (driver.isLeftPlayer()) {
      Rotation -= 2;
    } else if (!driver.isRightPlayer()) {
      Rotation -= 0;
    }

    }

  }
  @Override
  public void create(Graphics graphics) {
    Graphics2D graphics2D = (Graphics2D) graphics;
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    AffineTransform old = graphics2D.getTransform();
    graphics2D.rotate(theta, x + xxAxis / 2, y + yyAxis / 2);
    graphics2D.drawImage(tank, (int)x, (int)y, xxAxis, yyAxis, null);
    graphics2D.setTransform(old);
  }
}