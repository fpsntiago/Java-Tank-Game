import java.util.ArrayList;
import java.awt.Graphics;

public class TankDriver {
  ArrayList<TankObjects> obj = new ArrayList<TankObjects>();
  
  private boolean goForwardP1 = false;
  private boolean goBackwardP1 = false;
  private boolean spinRightP1 = false;
  private boolean spinLeftP1 = false;
  private boolean isFiringP1 = false;
  
  private boolean goForwardP2 = false;
  private boolean goBackwardP2 = false;
  private boolean spinRightP2 = false;
  private boolean spinLeftP2 = false;
  private boolean isFiringP2 = false;
  
  public void clock() {
    for (int i = 0; i < obj.size(); i++) {
      TankObjects tmpObj = obj.get(i);
      tmpObj.clock();
    }
  }
  public void spawnPlayer1(Graphics graphics, GameCam camera) {
    for (int i = 0; i < obj.size(); i++) {
      TankObjects tmpObj = obj.get(i);
      
      if (tmpObj.xCoordinate() < camera.xCoordinate() + 481) {
        tmpObj.create(graphics);
      }
    }
  }
  
  public void spawnPlayer2(Graphics graphics, GameCam camera) {
    for (int i = 0; i < obj.size(); i++) {
      TankObjects tmpObj = obj.get(i);
      
      if (tmpObj.xCoordinate() > camera.xCoordinate() + 480) {
        tmpObj.create(graphics);
      }
    }
  }
  
  public void createMap(Graphics graphics, int x, int y) {
    for (int i = 0; i < obj.size(); i++) {
      TankObjects tmpObj = obj.get(i);
      tmpObj.createMini(graphics, x, y);
    }
  }

  public void addObject(TankObjects tmpObj) {
    obj.add(tmpObj);
  }
  public void removeObject(TankObjects tmpObj) {
    obj.remove(tmpObj);
  }
  
  //movement definitions player 1
  public boolean isForwardPlayer() {
    return goForwardP1;
  }
  public boolean isBackwardPlayer() {
    return goBackwardP1;
  }
  public boolean isRightPlayer() {
    return spinRightP1;
  }
  public boolean isLeftPlayer() {
    return spinLeftP1;
  }
  public boolean isFiringPlayer() {
    return isFiringP1;
  }

  public void setForwardPlayer1(boolean goForward) {
    this.goForwardP1 = goForward;
  }
  public void setBackwardPlayer1(boolean goBackward) {
    this.goBackwardP1 = goBackward;
  }
  public void setRightPlayer1(boolean goRight) {
    this.spinRightP1 = goRight;
  }
  public void setLeftPlayer1(boolean goLeft) {
    this.spinLeftP1 = goLeft;
  }
  public void setShootPlayer1(boolean fire) {
    this.isFiringP1 = fire;
  }
  
  //movement definitions for player 2
  public boolean isForwardPlayer2(){return goForwardP2;}
  public boolean isBackwardPlayer2() {
    return goBackwardP2;
  }
  public boolean isRightPlayer2() {
    return spinRightP2;
  }
  public boolean isLeftPlayer2() {
    return spinLeftP2;
  }
  public boolean isFiringPlayer2() {
    return isFiringP2;
  }

  public void setForwardPlayer2(boolean goForward) {
    this.goForwardP2 = goForward;
  }
  public void setBackwardPlayer2(boolean goBackward) {
    this.goBackwardP2 = goBackward;
  }
  public void setRightPlayer2(boolean goRight) {
    this.spinRightP2 = goRight;
  }
  public void setLeftPlayer2(boolean goLeft) {
    this.spinLeftP2 = goLeft;
  }
  public void setShootPlayer2(boolean fire) {
    this.isFiringP2 = fire;
  }
}