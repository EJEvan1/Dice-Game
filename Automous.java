import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

class Automous {
  public static double gameSpeed = 1.0;
  public static JFrame autoBoard;
  private static JMenu menu, game, visual, gameSub;
  private static JMenuItem m1,m2,m3,g1,g2, gs1, gs2,v1,v2;
  private static JButton roll, startGame;
  private static JLabel diceNum;
  private static boolean imageDis=true, test;
  public static void autoPlay(){
  autoBoard = new JFrame("Automous");
  int numOfSides = 6;
   autoBoard.setSize(450,300);
    menuItems();
    buttons();
    labels();
    intalizeAction();
    autoBoard.setLayout(null);
    autoBoard.setVisible(true);
    diceDisplay(diceRoll(numOfSides));
  }
  private static int diceRoll(int numOfSides){
   Random rad = new Random();
   return rad.nextInt(numOfSides);
  }
  private static void menuItems(){
    JMenuBar mb = new JMenuBar();
    menu = new JMenu("Menu");
    game = new JMenu("Game");
    visual = new JMenu("Visuals");
    gameSub = new JMenu("Game Speed");
    //create menu items for functions
    m1 = new JMenuItem("Reset");
    m2 = new JMenuItem("Override");
    m3 = new JMenuItem("Exit");
    gs1 = new JMenuItem("0.5x");
    gs2 = new JMenuItem("1.0x");
    v1 = new JMenuItem("Disable Images");
    v2 = new JMenuItem("Enable Images");
    //add them to menu hierarchy 
    menu.add(m1); menu.add(m2); menu.add(m3);
    visual.add(v1);
    gameSub.add(gs1); gameSub.add(gs2);
    game.add(gameSub);
    mb.add(menu);
    mb.add(game);
    mb.add(visual);
    autoBoard.setJMenuBar(mb);
  }
  private static void buttons(){
    //create buttons
    startGame = new JButton("Start");
    //roll should have a image of a dice as its image. Might have to do some trouble shooting for this though.
    roll = new JButton();
    //add tool tip text
    startGame.setToolTipText("Starts the game!");
    //set Bounds
    startGame.setBounds(182, 90, 75, 50);

    //add to board
    autoBoard.add(startGame);
  }

  private static void labels(){
    //the dice number text sould remain at the right side of the screen
    diceNum = new JLabel();
    //set the text
    diceNum.setText("Test Message");

    //set the bounds
    diceNum.setBounds(340, 5, 500, 50);
  }

  
  private static void intalizeAction(){
    m1.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        System.out.println("Function should reset game board");
        JOptionPane.showMessageDialog(null, "Game was reset. All varibles were cleared.");
      }
    });
    m3.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        System.out.println("System was forced to exit by user");
        System.exit(1);
      }
    });
    m2.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        JOptionPane.showMessageDialog(null, "Override feature not avaliable yet.");
      }
    });

    //start button action script
    startGame.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        System.out.println("This should start the game");
        autoBoard.remove(startGame);
        autoBoard.add(diceNum);
        autoBoard.repaint();
      }
    });
    //hide images will modify a varible to enable and disable images
    v1.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        System.out.println("Disabling Images from the board");
        imageDis = false;
        visual.remove(v1);
        visual.add(v2);
        autoBoard.repaint();
      }
    });
    //re-enable images
    v2.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
       imageDis = true;
        visual.remove(v2);
        visual.add(v1);
        autoBoard.repaint();
      }
    });
  }

  //Method to display different dice rolls
  private static void diceDisplay(int roll){
    if (roll == 1){
      System.out.println("An image of a dice with 1 would be displayed");
    }
    else if (roll == 2){
      System.out.println("An image of a dice with 2 would be displayed");
    }
    else if (roll == 3){
      System.out.println("An image of a dice with 3 would be displayed");
    }
    else if (roll == 4){
      System.out.println("An image of a dice with 4 would be displayed");
    }
    else if (roll == 5){
      System.out.println("An image of a dice with 5 would be displayed");
    }
    else if (roll == 6){
      System.out.println("An image of a dice with 6 would be displayed");
    }
    else {
      System.out.println("Error occured in diceDisplay Method");
    }
  }

  public static void table(){
   // String[] columnNames = {"Roll #", "Value"};
    
  }
}