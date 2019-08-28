import java.awt.Dimension;
import javax.swing.JFrame;


class Panel {
  Panel(int xx, int yy, String title, TankGame game) {
    JFrame frame = new JFrame(title);
    
    Dimension set = new Dimension(xx, yy);
    frame.setPreferredSize(set);
    frame.setMaximumSize(set);
    frame.setMinimumSize(set);
    
    frame.add(game);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}