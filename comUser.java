import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class comUser{
  /*frames and other junk*/
  private static JFrame cnsl = new JFrame("Console");
  private static JLabel sys = new JLabel("Default Message");
  private static JTextField input = new JTextField("Input a Command");
  private static JButton comEnt = new JButton("");
  private static Border border;
  private static boolean isOpen;
  private static InputMap im = comEnt.getInputMap();

  //threaded thing here

 public static void userConsole(){
      cnsl.setSize(400, 100);
      cnsl.setLayout(null);
      cnsl.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

      //sys label thingy
      border = BorderFactory.createLineBorder(Color.black);
      sys.setBounds(5, 05, 390, 12);
      sys.setBackground(Color.GRAY);
      sys.setForeground(Color.WHITE);
      sys.setOpaque(true);
      sys.setBorder(border);
      inputSys();
      cnsl.add(comEnt);
      cnsl.add(input);
      cnsl.add(sys);
      actionSystem();
      
      //setVisible thing
      isOpen = true;
      cnsl.setVisible(true);
    }
  
  
  public static void lanuch(){
   userConsole();
  }

  public static void cnslWrt(String mes){
    sys.setText("Host Controller: " + mes);
    cnsl.repaint();
  }

  //action commands for the Console
  public static void inputSys(){
   input.setBounds(25, 40, 350, 20);
  input.setBorder(border);
    comEnt.setBounds(360, 45, 30, 20);
    comEnt.setOpaque(false);
    comEnt.setContentAreaFilled(false);
    comEnt.setBorderPainted(false);
  }
  //making a renewing panel message
  public static void renew(){
    cnsl.repaint();
  }

  public static void cnslExit(){
    if (isOpen){
    cnsl.setVisible(false);
    isOpen = false;
    }
  }
  
  private static void actionSystem(){
    try{
    cnsl.getRootPane().setDefaultButton(comEnt);
    im.put(KeyStroke.getKeyStroke("ENTER"), "pressed");
    im.put(KeyStroke.getKeyStroke("released ENTER"), "released");

    comEnt.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        String inputField = input.getText();
        if (inputField.equalsIgnoreCase("exit")){
          cnslWrt("Shuting Down");
          System.exit(69);
        }
        else if(inputField.equalsIgnoreCase("fDiceNum") || inputField.contains("fDiceNum")){
          System.out.println("Parsed through if statement");
          String[] temp = inputField.split(" ");
          if (temp.length >= 2){
            int tempInt = Integer.valueOf(temp[1]);
            if (inputField.contains("disImg")){
             try{ 
               Automous.diceDisplay(tempInt);
               whiteBoard.addRoll(1,tempInt);
               Automous.tableRenew();
               }
              catch (Exception w){
                cnslWrt("An Error has Occured");
              }
            }
            else{
              whiteBoard.addRoll(1,tempInt);
              Automous.tableRenew();
            }
          }
          else{
            cnslWrt("fDiceNum # disImg");
          }
        }
      }
    });
  }
    catch (Exception y){
      cnslWrt("An AWT Error Occured");
      y.printStackTrace();
      }
    }
}