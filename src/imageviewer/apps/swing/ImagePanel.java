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
        g.drawImage(image, 0, 0, null);
    }          
}
