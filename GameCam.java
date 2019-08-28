public class GameCam {
  private int xx_axis;
  private int yy_axis;
  private float x_axis, y_axis;
  
  public GameCam(float x_axis, float y_axis, int xx_axis, int yy_axis) {
    this.x_axis = x_axis;
    this.y_axis = y_axis;
    this.xx_axis = xx_axis;
    this.yy_axis = yy_axis;
  }
  
  public void updatePlayer1(TankObjects obj) {
    x_axis += ((obj.xCoordinate()-x_axis)-xx_axis/4) * 0.05f;
    y_axis += ((obj.yCoordinate()-y_axis)-yy_axis/2) * 0.05f;
    
    if (x_axis <= 0)
      x_axis = 0;
    if (x_axis >= 1100)
      x_axis = 1100;
    if (y_axis <= 0)
      y_axis = 0;
    if (y_axis >= 1090)
      y_axis = 1060;
  }
  
  public void updatePlayer2(TankObjects obj) {
    x_axis += ((obj.xCoordinate()-x_axis)-3*xx_axis/4)*0.05f;
    y_axis += ((obj.yCoordinate()-y_axis)-yy_axis/2)*0.05f;
    
    if (x_axis <= -510)
      x_axis = -510;
    if (x_axis >= 600)
      x_axis = 600;
    if (y_axis <= 0)
      y_axis = 0;
    if (y_axis >= 1065)
      y_axis = 1065;
  }
  
  public float xCoordinate() {
    return x_axis;
  }
  public float yCoordinate() {
    return y_axis;
  }

}
