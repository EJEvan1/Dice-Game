import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

class Resize {

  public BufferedImage resize(BufferedImage img, int scaledWidth, int scaledHeight) /*throws IOException*/ {
    System.out.println("Accepted Image");
   try{ BufferedImage outImg = new BufferedImage(scaledWidth, scaledHeight, img.getType());
    
  System.out.println("Creating Graphics");
    Graphics2D g2d = outImg.createGraphics();
    g2d.drawImage(img, 0, 0, scaledWidth, scaledHeight, null);
    g2d.dispose();
    return outImg;
     }
    catch (Exception e){
      e.printStackTrace();
      return img;
    }
  }
  public BufferedImage resize(BufferedImage img, double percent) throws IOException {
    int scaledWidth = (int) (img.getWidth() * percent);
    int scaledHeight = (int) (img.getHeight() * percent);
    BufferedImage outImg = resize(img, scaledWidth, scaledHeight);
    return outImg;
  }
  public BufferedImage resize(String inputImagePath, int scaledWidth, int scaledHeight) throws IOException {
    BufferedImage img = ImageIO.read(new File(inputImagePath));
    return resize(img, scaledWidth, scaledHeight);
  }
  public BufferedImage resize(String inputImagePath, double percent) throws IOException {
    BufferedImage img = ImageIO.read(new File(inputImagePath));
    return resize(img, percent);
  }

  public ImageIcon imageIconConvert(String fileName) throws IOException{
    BufferedImage img = ImageIO.read(new File(fileName));
    return new ImageIcon(img);
  }
  public ImageIcon imageIconConvert(BufferedImage img){
    return new ImageIcon(img);
  }
}