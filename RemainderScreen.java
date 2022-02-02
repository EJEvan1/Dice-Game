import javax.swing.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class RemainderScreen{
  static Resize resize = new Resize();
  private static int countDown = 20;
  private static JLabel autoCloseMess;
  private static JFrame displayRemainder;
  public static void remainder() throws Exception, IOException{
    //PlaceHolder display screen
    displayRemainder = new JFrame("Grab Dice");
    displayRemainder.setSize(350, 350);
    displayRemainder.setLayout(null);

    JLabel warningUpper = new JLabel("GRAB SIX SIDED DICE");
    warningUpper.setBounds(90, 20, 500, 50);
    System.out.println("creating image");
  BufferedImage dice = ImageIO.read(new File("fileImages/smokey.jpg"));
   dice = resize.resize(dice, dice.getWidth() /2, dice.getHeight() /2);
    System.out.println("Image created");
   ImageIcon image = new ImageIcon(dice);
    autoCloseMess = new JLabel("This message will close in " + countDown + " seconds");
    autoCloseMess.setBounds(40, 170, 500, 50);
    JLabel imageBounds = new JLabel();
   imageBounds.setBounds(90, 80, dice.getWidth(), dice.getHeight());
    JButton closeButton = new JButton("OK");
    closeButton.setBounds(115, 260, 100, 25);
    closeButton.setToolTipText("Close this Box");
    closeButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        timerFunc(false);
        displayRemainder.setVisible(false);
        Automous.autoPlay();
      }
    });
    
    timerFunc(true);
    
    displayRemainder.add(warningUpper);
    displayRemainder.add(autoCloseMess);
    displayRemainder.add(closeButton);
    imageBounds.setIcon(image);
    displayRemainder.add(imageBounds);
    displayRemainder.setVisible(true);

  }
 private static void timerFunc(boolean toRunOrNotToRun){
   Thread timerThread = new Thread(){
     public void run(){
       while (countDown >= 0){
         autoCloseMess.setText("This message will close in " + countDown + " seconds");
         try{
           Thread.sleep(1000);
         }
         catch(Exception e){
           System.out.println(e);
         }
         countDown-=1;
       }
       displayRemainder.setVisible(false);
     }
   };
   if (toRunOrNotToRun){
     timerThread.start();
   }
   else if (!toRunOrNotToRun){
     timerThread.interrupt();
   }
 } 
}