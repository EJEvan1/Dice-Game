import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

//this method should be used to track the varibles used
//First 1 dimension is used the Roll count example: dataRoll[0][0] = "Roll X" and dataRoll[0][1] = Actual data value
class whiteBoard {
  // String is set to a maxium 50 by 50 array size to avoid out of bounds
  public static String[][] dataRoll = new String[9999][2];
  private static final String BLACK = "\u001B[30m"; // BLACK
  private static final String RED = "\u001B[31m"; // RED
  private static final String GREEN = "\u001B[32m"; // GREEN
  private static final String YELLOW = "\u001B[33m"; // YELLOW
  private static final String BLUE = "\u001B[34m"; // BLUE
  private static final String PURPLE = "\u001B[35m"; // PURPLE
  private static final String CYAN = "\u001B[36m"; // CYAN
  private static final String WHITE = "\u001B[37m"; // WHITE
  private static final String ANSI_RESET = "\u001B[0m"; // RESET COLOR
  // ArrayList<int, String> test = new ArrayList<int, String>;
  // test.add()

  public static void main(String[] args) {
    String[][] test = new String[99][99];
    test[0][1] = "Test";
    // test[0][] = "2";
    test[1][0] = "Roll 2";
    test[1][1] = "23";
    int counter = 0;
    try {
      for (int i = 0; i < test.length; i++) {
        for (int j = 0; j < test.length; j++) {
          if (test[i][j] != null && counter < 2) {
            System.out.print(test[i][j] + " ");
            counter++;
          } else if (test[i][j] != null) {
            System.out.println("\n");
            System.out.print(test[i][j] + " ");
            counter = 0;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static int getValueInt(int a, int b) throws Exception {
    return Integer.valueOf(dataRoll[a][b]);
  }

  public static String getString(int a, int b) {
    return dataRoll[a][b];
  }

  public static void deleteAllValues() {
    dataRoll = new String[50][2];
    comUser.cnslWrt("All values were deleted");
  }

  public static void addRoll(int rollNumber, int value) {
    if (String.valueOf(rollNumber) == null || rollNumber == 0) {
      for (int i = 0; i < dataRoll.length; i++) {
        if (dataRoll[i][0] == null) {
          dataRoll[i][0] = "Roll " + (i + 1);
        }
      }
    } else {
      dataRoll[rollNumber - 1][0] = "Roll " + rollNumber;
    }
    dataRoll[rollNumber - 1][1] = String.valueOf(value);
  }

  public static String[][] getData(){
    String[][] dataRollCompressed = Arrays.copyOf(dataRoll, countTillNull());
    //this a primative way of doing it
    return dataRollCompressed;
  }
  public static int countTillNull(){
    for (int i = 0; i < dataRoll.length; i++){
      if (dataRoll[i][0] == null && dataRoll[i][1] == null){
        return i;
      }
    }
    return 0;
  }
  public static void addRoll(String rollNumber, int value) {
    int i = 0;
    if (rollNumber == null) {
      for (i = 0; i < dataRoll.length; i++) {
        if (dataRoll[i][0] == null) {
          dataRoll[i][0] = "Roll " + (i + 1);
        }
      }
      rollNumber = String.valueOf(i) + 1;
    } else {
      dataRoll[Integer.valueOf(rollNumber) - 1][0] = "Roll " + rollNumber;
    }
    dataRoll[i][1] = String.valueOf(value);
  }
  public static boolean checkIfAlreadyInUse(int toCheckfor){
    for (int i = 0; i < dataRoll.length; i++){
      for (int j = 0; j < dataRoll.length; j++){
        if (dataRoll[i][j] == String.valueOf(toCheckfor)){
          return true;
        }
      }
    }
    return false;
  }
}