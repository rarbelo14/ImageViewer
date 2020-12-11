package imageviewer.apps.swing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private BufferedImage image;

    public ImagePanel() {
        try{
            this.image = ImageIO.read(new File("fotos/canteras-1.jpg"));
        } catch (IOException e){
        }
    }
    
    @Override
    public void paint (Graphics g){
        Box box = new Box(image.getWidth(), image.getHeight(), this.getWidth(), this.getHeight());
        g.drawImage(image, box.x, box.y, box.width, box.height, null);
    }          

    private static class Box {
        final int x;
        final int y;
        final int width;
        final int height;
        private Box(double imageWidth, double imageHeight, double panelWidth, double panelHeight) {
            double imageRatio = imageWidth / imageHeight;
            double panelRatio = panelWidth / panelHeight;
            this.width = (int) (imageRatio >= panelRatio ? panelWidth : imageWidth * panelHeight / imageHeight);                     
            this.height = (int) (imageRatio <= panelRatio ? panelHeight : imageHeight * panelWidth / imageWidth);
            this.x = (int) ((panelWidth - this.width) / 2);
            this.y = (int) ((panelHeight - this.height) / 2);
        } 
    }
}
