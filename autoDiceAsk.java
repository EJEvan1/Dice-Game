import javax.swing.*;

class autoDiceAsk{
  public static int askSide(){
    JFrame f = new JFrame();
    String e = JOptionPane.showInputDialog(f,"Enter # of Sides");
    return Integer.valueOf(e);
  }  
}