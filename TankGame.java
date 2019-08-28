import java.awt.*;
import java.awt.Canvas;
import java.awt.image.*;
public class TankGame extends Canvas implements Runnable {
  private boolean isRunning = false;
  boolean GameOver = false;
  private Thread clk;
  private TankDriver driver;
  //private TankDriver driver2;
  
  private GameCam Screen1;
  private GameCam Screen2;

  private BufferedImage splitscreen;
  private BufferedImage stage;
  private BufferedImage life;
  private BufferedImage panel;

  private Background backgroundObject;
  private BufferedImage FetchedImg;

  public static int p1Health;
  public static int p2Health;
  public static int p1Lives;
  public static int p2Lives;
  public static int xxx = 1000;
  public static int yyy = 570;


  String line;

  public static void main(String args[]) {
    new TankGame();
  }

  public TankGame() {
    new Panel(xxx, yyy, "TankGame", this);
    start();
    
    driver = new TankDriver();
    //driver2 = new TankDriver();
    Screen1 = new GameCam(0, 0, xxx, yyy);
    Screen2 = new GameCam(0, 0, xxx, yyy);
    this.addKeyListener(new TankControls(driver));
    this.addKeyListener(new TankControls(driver));
    TankGameImage loader = new TankGameImage();
    stage = loader.loadImage("resources/Map.png");
    splitscreen = loader.loadImage("resources/blackborder.jpg");
    life = loader.loadImage("resources/mushroom1.png");
    backgroundObject = new Background();
    panel = backgroundObject.getBackground();
    FetchedImg = panel.getSubimage(0, 0, xxx, yyy);
    
    load(stage);
  }
  public void load(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();

    for (int xAxis = 0; xAxis < width; xAxis++) {
      for (int yAxis = 0; yAxis < height; yAxis++) {
        int pixel = image.getRGB(xAxis, yAxis);

        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        int green1 = (pixel >> 8) & 0xff;

        if (blue == 255) {
          driver.addObject(new Tank1(xAxis * 32, yAxis * 32, 180, TankObjectID.Player1, driver));
        }
        if (green == 255) {
          driver.addObject(new Tank2(xAxis * 32, yAxis * 32, 0, TankObjectID.Player2, driver));
        }
        if (red == 255) {
          driver.addObject(new BreakableWall(xAxis * 32, yAxis * 32, TankObjectID.UnbreakableWall));
        }
        if (red == 170) {
          driver.addObject(new UnbreakableWall(xAxis * 32, yAxis * 32, TankObjectID.BreakableWall));
        }
        if (green1 == 100) {
          driver.addObject(new PowerUp1(xAxis * 32, yAxis * 32, TankObjectID.PowerUp1));
        }
        if (green1 == 100) {
          driver.addObject(new PowerUp2(xAxis * 32, yAxis * 32, TankObjectID.PowerUp2));
        }

      }
    }
  }

  private void start() {
    isRunning = true;
    clk= new Thread(this);
    clk.start();
  }
  
  private void stop() {
    isRunning = false;
    
    try {
      clk.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  

  public void clock() {
    

    for (int i = 0; i < driver.obj.size(); i++) {
      if (driver.obj.get(i).fetchObject() == TankObjectID.Player1) {
        Screen1.updatePlayer1(driver.obj.get(i));
        p1Health = driver.obj.get(i).getHealth();
        p1Lives = driver.obj.get(i).getLives();

        if (p1Health <= 0 && p1Lives == 0) {
          GameOver = true;
          line = "Game Over P2 Wins";

        }
      }

      if (driver.obj.get(i).fetchObject() == TankObjectID.Player2) {
        Screen2.updatePlayer2(driver.obj.get(i));
        p2Health = driver.obj.get(i).getHealth();
        p2Lives = driver.obj.get(i).getLives();

        if (p2Health <= 0 && p2Lives == 0) {
          GameOver = true;
          line = " Game Over P1 Wins";

        }
      }
    }

    driver.clock();
  }

  public void create() {
    BufferStrategy buffStrat = this.getBufferStrategy();
    
    if (buffStrat == null) {
      this.createBufferStrategy(3);
      return;
    }

    Graphics graphics = buffStrat.getDrawGraphics();
    Graphics2D graphics2D = (Graphics2D) graphics;
    //background
    graphics.setColor(Color.black);
    graphics.fillRect(0, 0, xxx, yyy);
    graphics.drawImage(panel, 0, 0, null);
    //cameras

    graphics2D.translate(-Screen1.xCoordinate(), -Screen1.yCoordinate());
    driver.spawnPlayer1(graphics, Screen1);
    graphics2D.translate(Screen1.xCoordinate(), Screen1.yCoordinate());

    graphics2D.translate(-Screen2.xCoordinate(), -Screen2.yCoordinate());
    driver.spawnPlayer2(graphics, Screen2);
    graphics2D.translate(Screen2.xCoordinate(), Screen2.yCoordinate());

    //split screen line
    for (int line = 0; line < yyy; line += 5) {
      graphics.drawImage(splitscreen, xxx / 2 - 25, line, 5, 5, null);
    }
    
    //map
    graphics.drawImage(FetchedImg, 380, 330, xxx / 5, xxx/ 5, null);
    driver.createMap(graphics, 380, 330);
    
    //positioning of health and life
    for (int i = 0; i < p1Lives; i++){
      graphics.drawImage(life, 120 + 30 * i, 510, 23, 23, null);
    }
    for (int i = 0; i < p2Lives; i++){
      graphics.drawImage(life, 810 + 30 * i, 510, 23, 23, null);
    }
    graphics.setColor(Color.gray);
    graphics.fillRect(10, 510, 100, 20);
    graphics.setColor(Color.red);
    graphics.fillRect(10, 510, p1Health, 20);
    graphics.setColor(Color.black);
    graphics.drawRect(10, 510, 100, 20);
    graphics.setColor(Color.gray);
    graphics.fillRect(870, 510, 100, 20);
    graphics.setColor(Color.red);
    graphics.fillRect(870, 510, p2Health, 20);
    graphics.setColor(Color.black);
    graphics.drawRect(870, 510, 100, 20);



    if (GameOver) {
      Font gameOverFont = new Font("Courier", Font.BOLD, 80);
      
      FontMetrics metrics = graphics.getFontMetrics(gameOverFont);
      int xPos = (xxx - metrics.stringWidth(line)) / 2;
      int yPos = (yyy - metrics.getHeight()) / 2 + metrics.getAscent();
      graphics.setFont(gameOverFont);
      
      graphics.setColor(Color.black);
      graphics.fillRect(0, 0, xxx, yyy);
      graphics.setColor(Color.white);
      graphics.drawString(line, xPos, yPos - 15);
    }
    
    graphics.dispose();
    buffStrat.show();
  }
  public void run() {
    this.requestFocus();
    long recent = System.nanoTime();
    long clk = System.currentTimeMillis();
    double cycles = 60.0;
    double time = 1000000000/cycles;
    double update = 0;
    float fps = 0;
    
    while (isRunning) {
      long current = System.nanoTime();
      update += (current-recent)/time;
      recent = current;
      while (update >= 1) {
        clock();
        update--;
      }
      create();
      fps++;
      if (System.currentTimeMillis() - clk > 1000) {
        clk += 1000;
        fps = 0;
      }
    }
    stop();
  }



}