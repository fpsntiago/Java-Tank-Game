import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankControls extends KeyAdapter {
  TankDriver handler;

  //TankDriver handler2;
  
  public TankControls(TankDriver handler) {
    this.handler = handler;
    //this.handler = handler2;
  }
 // public KeyInput2 (TankDriver handler2) {this.handler2 = handler2;}
  
  public void keyPressed(KeyEvent event) {
    int key = event.getKeyCode();
    
    for (int i = 0; i < handler.obj.size(); i++) {
      TankObjects tmpObj = handler.obj.get(i);

      //tank 1
      if (tmpObj.fetchObject() == TankObjectID.Player1) {
        if (key == KeyEvent.VK_W)
          handler.setForwardPlayer1(true);
        if (key == KeyEvent.VK_S)
          handler.setBackwardPlayer1(true);
        if (key == KeyEvent.VK_A)
          handler.setLeftPlayer1(true);
        if (key == KeyEvent.VK_D)
          handler.setRightPlayer1(true);
        if (key == KeyEvent.VK_SPACE)
          handler.setShootPlayer1(true);
      }
      //tank 2
      if (tmpObj.fetchObject() == TankObjectID.Player2) {
        if (key == KeyEvent.VK_O)
          handler.setForwardPlayer2(true);
        if (key == KeyEvent.VK_L)
          handler.setBackwardPlayer2(true);
        if (key == KeyEvent.VK_K)
          handler.setLeftPlayer2(true);
        if (key == KeyEvent.VK_SEMICOLON)
          handler.setRightPlayer2(true);
        if (key == KeyEvent.VK_SHIFT)
          handler.setShootPlayer2(true);
      }
    }
  }
  
  public void keyReleased(KeyEvent event) {
    int key = event.getKeyCode();
    
    for (int i = 0; i < handler.obj.size(); i++) {
      TankObjects tmpObj = handler.obj.get(i);
      
      //tank 1
      if (tmpObj.fetchObject() == TankObjectID.Player1) {
        if (key == KeyEvent.VK_W)
          handler.setForwardPlayer1(false);
        if (key == KeyEvent.VK_S)
          handler.setBackwardPlayer1(false);
        if (key == KeyEvent.VK_A)
          handler.setLeftPlayer1(false);
        if (key == KeyEvent.VK_D)
          handler.setRightPlayer1(false);
        if (key == KeyEvent.VK_SPACE)
          handler.setShootPlayer1(false);
      }
      //tank 2
      if (tmpObj.fetchObject() == TankObjectID.Player2) {
        if (key == KeyEvent.VK_O)
          handler.setForwardPlayer2(false);
        if (key == KeyEvent.VK_L)
          handler.setBackwardPlayer2(false);
        if (key == KeyEvent.VK_K)
          handler.setLeftPlayer2(false);
        if (key == KeyEvent.VK_SEMICOLON)
          handler.setRightPlayer2(false);
        if (key == KeyEvent.VK_SHIFT)
          handler.setShootPlayer2(false);
      }
    }
  }
}
