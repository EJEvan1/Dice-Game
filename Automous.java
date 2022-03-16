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
  private static JMenuItem m1,m2,m3,m4,g1,g2, gs1, gs2,v1,v2;
  private static JButton rollButton, startGame;
  private static JLabel diceNum;
  private static boolean imageDis=true, winCond=false;
  public static void autoPlay() throws Exception{
  //Runtime.getRuntime().traceMethodCalls(true);
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
    m2 = new JMenuItem("Debug");
    m3 = new JMenuItem("Exit to Desktop");
    m4 = new JMenuItem("Exit to Main");
    gs1 = new JMenuItem("0.5x");
    gs2 = new JMenuItem("1.0x");
    v1 = new JMenuItem("Disable Images");
    v2 = new JMenuItem("Enable Images");
    //add them to menu hierarchy 
    menu.add(m1); menu.add(m2); menu.add(m4); menu.add(m3);
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
    diceNum.setText("Win Condition: " + String.valueOf(winCond));

    //set the bounds
    diceNum.setBounds(300, 5, 500, 50);
  }

  
  private static void intalizeAction() throws Exception{
    m1.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        comUser.cnslWrt("Board was reset");
        whiteBoard.deleteAllValues();
        tableRenew();
        countingRoll = 1;
       // JOptionPane.showMessageDialog(null, "Game was reset. All varibles were cleared.");
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
        //JOptionPane.showMessageDialog(null, "Override feature not avaliable yet.");
        comUser.lanuch();
        comUser.cnslWrt("Command console was lanuched");
      }
    });
  //exit to main menu script
    m4.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
       try{ autoBoard.dispose();
        comUser.cnslExit();
        rolls.dispose();
        GUI.mainMenu();
         }
        catch (Exception t){
          GUI.mainMenu();
        }
      }   
    });
    //start button action script
    startGame.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
       // System.out.println("Host Controller: This should start the game");
        autoBoard.remove(startGame);
        autoBoard.add(diceNum);
        autoBoard.add(rollButton);
        table();
        winCond=true;
        winCondRenew();
        whiteBoard.addRoll(1, diceRoll(6) + 1);
        tableRenew();
        try{diceDisplay(6);}catch(Exception w){}
        comUser.cnslWrt("Board and Varibles were reset");
        autoBoard.repaint();
      }
    });
    //hide images will modify a varible to enable and disable images
    v1.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        comUser.cnslWrt("Images Disabled");
        imageDis = false; winCond=false;
        visual.remove(v1);
        visual.add(v2);
        autoBoard.repaint();
      }
    });
    //re-enable images
    v2.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        comUser.cnslWrt("Images Enabled");
        imageDis = true;
        visual.remove(v2);
        visual.add(v1);
        autoBoard.repaint();
      }
    });
    rollButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
       // System.out.println("Host Controller: Rolling the dice");
        int temp = diceRoll(6) + 1;
        whiteBoard.addRoll(countingRoll, temp);
       try{ diceDisplay(temp);} catch (Exception t){}
        countingRoll++;
        tableRenew();
        comUser.cnslWrt("User rolled a " + temp);
      }
    });
  }

  //Method to display different dice rolls
  public static void diceDisplay(int roll) throws IOException{
    Resize t = new Resize();
    if (roll == 1 && imageDis){
      try{ diceNum.setIcon(t.imageIconConvert(t.resize("fileImages/diceTextures/face1.jpg" , 0.10)));
      diceNum.setText(null);
      diceNum.setBounds(325, 0, 500, 100);
      windRenew();
      comUser.cnslWrt("Corresponding Image Generated");
      }
      catch(Exception e ){}
    }
    else if (roll == 2 && imageDis){
      try{      diceNum.setIcon(t.imageIconConvert(t.resize("fileImages/diceTextures/face2.jpg" , 0.10)));
      diceNum.setText(null);
      diceNum.setBounds(325, 0, 500, 100);
      windRenew();
      comUser.cnslWrt("Corresponding Image Generated");
      }
      catch(Exception e ){}
    }
    else if (roll == 3 && imageDis){
      try{    diceNum.setIcon(t.imageIconConvert(t.resize("fileImages/diceTextures/face3.jpg" , 0.10)));
      diceNum.setText(null);
      diceNum.setBounds(325, 0, 500, 100);
      windRenew();
      comUser.cnslWrt("Corresponding Image Generated");
      }
      catch(Exception e ){}
    }
    else if (roll == 4 && imageDis){
      try{  diceNum.setIcon(t.imageIconConvert(t.resize("fileImages/diceTextures/face4.jpg" , 0.10)));
      diceNum.setText(null);
      diceNum.setBounds(325, 0, 500, 100);
      windRenew();
      comUser.cnslWrt("Corresponding Image Generated"); 
      }
      catch(Exception e ){}
    }
    else if (roll == 5 && imageDis){
      try{
      diceNum.setIcon(t.imageIconConvert(t.resize("fileImages/diceTextures/face5.jpg" , 0.10)));
      diceNum.setText(null);
      diceNum.setBounds(325, 0, 500, 100);
      windRenew();
      comUser.cnslWrt("Corresponding Image Generated");
      }
      catch(Exception e ){}
    }
    else if (roll == 6 && imageDis){
      try{
      diceNum.setIcon(t.imageIconConvert(t.resize("fileImages/diceTextures/face6.jpg" , 0.13)));
      diceNum.setText(null);
      diceNum.setBounds(325, 0, 500, 100);
      comUser.cnslWrt("Corresponding Image Generated");
      windRenew();
      }
      catch(Exception e ){}
    }
    else if (imageDis){
      comUser.cnslWrt("Impossible value was parsed to diceDisplay method");
    }
    else if (!imageDis){
      diceNum.setIcon(null);
      diceNum.setText("Num Rolled: " + roll);
      diceNum.setBounds(325,5,500,50);
      windRenew();
    }
  }

  public static void table(){
   //String[][] columnNames = new String[2][2];
  String[][] dataTypes = whiteBoard.getData();
    String[] values = {"Roll", "Value"};
    rolls = new JFrame("Rolls");
    table = new JTable(dataTypes, values);
    rolls.setSize(150, 300);
    rolls.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    scrollPane = new JScrollPane(table);
    rolls.add(scrollPane);
    rolls.setVisible(true);
  }
  public static void tableRenew(){
    String[][] dataTypes = whiteBoard.getData();
    String[] columnNames = {"Roll", "Value"};
    table = new JTable(dataTypes, columnNames);
    rolls.remove(scrollPane);
    scrollPane = new JScrollPane(table);
    rolls.add(scrollPane);
    rolls.repaint();
  }
  private static void winCondRenew(){
    diceNum.setText("Win Condition: " + String.valueOf(winCond));
    diceNum.setIcon(null);
    autoBoard.repaint();
  }
  private static void windRenew(){
    autoBoard.repaint();
  }
}