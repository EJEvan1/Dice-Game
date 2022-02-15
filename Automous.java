import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Automous {
  Resize image = new Resize();
  private static int countingRoll = 2;
  public static double gameSpeed = 1.0;
  public static JFrame autoBoard, rolls;
  private static JMenu menu, game, visual, gameSub;
  private static JScrollPane scrollPane;
  private static JTable table;
  private static JMenuItem m1,m2,m3,g1,g2, gs1, gs2,v1,v2;
  private static JButton rollButton, startGame;
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
    autoBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    rollButton = new JButton("Roll");
    //roll should have a image of a dice as its image. Might have to do some trouble shooting for this though.
    //add tool tip text
    startGame.setToolTipText("Starts the game!");
    rollButton.setToolTipText("Roll the dice");
    //set Bounds
    rollButton.setBounds(182,90,75,50);
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
        whiteBoard.deleteAllValues();
        tableRenew();
        countingRoll = 1;
        JOptionPane.showMessageDialog(null, "Game was reset. All varibles were cleared.");
      }
    });
    m3.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        System.out.println("Host Controller: System was forced to exit by user");
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
        System.out.println("Host Controller: This should start the game");
        autoBoard.remove(startGame);
        autoBoard.add(diceNum);
        autoBoard.add(rollButton);
        table();
        whiteBoard.addRoll(1, diceRoll(6) + 1);
        tableRenew();
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
    rollButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        System.out.println("Host Controller: Rolling the dice");
        int temp = diceRoll(6) + 1;
        whiteBoard.addRoll(countingRoll, temp);
        diceDisplay(temp);
        countingRoll++;
        tableRenew();
      }
    });
  }

  //Method to display different dice rolls
  private static void diceDisplay(int roll){
    if (roll == 1 && imageDis){
      System.out.println("An image of a dice with 1 would be displayed");
    }
    else if (roll == 2 && imageDis){
      System.out.println("An image of a dice with 2 would be displayed");
    }
    else if (roll == 3 && imageDis){
      System.out.println("An image of a dice with 3 would be displayed");
    }
    else if (roll == 4 && imageDis){
      System.out.println("An image of a dice with 4 would be displayed");
    }
    else if (roll == 5 && imageDis){
      System.out.println("An image of a dice with 5 would be displayed");
    }
    else if (roll == 6 && imageDis){
      System.out.println("An image of a dice with 6 would be displayed");
    }
    else {
      System.out.println("Host Controller: Error occured in diceDisplay Method or images have been disabled");
    }
  }

  public static void table(){
   //String[][] columnNames = new String[2][2];
  String[][] dataTypes = whiteBoard.getData();
    String[] values = {"Roll", "Value"};
    rolls = new JFrame("Rolls");
    table = new JTable(dataTypes, values);
    rolls.setSize(150, 100);
    rolls.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    scrollPane = new JScrollPane(table);
    rolls.add(scrollPane);
    rolls.setVisible(true);
  }
  private static void tableRenew(){
    String[][] dataTypes = whiteBoard.getData();
    String[] columnNames = {"Roll", "Value"};
    table = new JTable(dataTypes, columnNames);
    rolls.remove(scrollPane);
    scrollPane = new JScrollPane(table);
    rolls.add(scrollPane);
    rolls.repaint();
  }
}