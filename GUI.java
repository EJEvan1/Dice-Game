import javax.swing.*;
import java.awt.event.*;

public class GUI{
 
  public static void mainMenu(){
  //This is a placeholder UI until the real one is created
  //Create Menu Interface
  // Allows user to select what mode they want (automated or player-controlled)
    JFrame menu = new JFrame("Menu");
    menu.setSize(375, 250);
    menu.setLayout(null);

  //Create Title cards for the panel
    JLabel titleCard = new JLabel("Dice Game");
    titleCard.setBounds(120, -40, 100, 100);
    JLabel subTitleCard = new JLabel("Inspired by AP Java C Period");
    subTitleCard.setBounds(60, 0, 500, 50);
    JLabel credits = new JLabel("Created by Evan Reidy Class of 2023");
    credits.setBounds(40, 175, 500, 50);
    

    JButton automatedButton = new JButton();
    automatedButton.setText("Auto Play");
    automatedButton.setToolTipText("The game will follow the instruction sheet!");
    automatedButton.setBounds(75, 60, 170, 30);
    automatedButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        menu.setVisible(false);
        Automous.autoPlay();
      }
    });

    JButton playerControlled = new JButton();
    playerControlled.setText("Player Controlled");
    playerControlled.setToolTipText("Manually Control the Game!");
    playerControlled.setBounds(75, 120, 170, 30);
    playerControlled.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
       // JOptionPane.showMessageDialog(null, "Test Message");
        menu.setVisible(false);
       try{ RemainderScreen.remainder();}
        catch (Exception a){/*Nothing Here*/}
      }
      });

    menu.add(titleCard);
    menu.add(subTitleCard);
    menu.add(automatedButton);
    menu.add(playerControlled);
    menu.add(credits);

    menu.setVisible(true);
    
}
}