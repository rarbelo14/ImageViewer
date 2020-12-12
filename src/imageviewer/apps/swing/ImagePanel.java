package imageviewer.apps.swing;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements ImageDisplay{

    private Image image;
    private BufferedImage data;

    public ImagePanel() {
        
    }
    
    @Override
    public void paint (Graphics g){
        Box box = new Box(data.getWidth(), data.getHeight(), this.getWidth(), this.getHeight());
        g.drawImage(data, box.x, box.y, box.width, box.height, null);
    }          

    @Override
    public void display(Image image) {
        this.image = image;
        this.data = read(new File(image.getName()));
        repaint();
    }

    @Override
    public Image currentImage() {
        return this.image;
    }

    private static BufferedImage read(File file) {
        try{
            return ImageIO.read(file);
        } catch (IOException e){
            return null;
        }        
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
