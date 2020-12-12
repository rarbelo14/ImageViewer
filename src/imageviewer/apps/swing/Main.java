package imageviewer.apps.swing;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.awt.PopupMenu;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
    //24:08 Video(2)
    public static void main(String[] args) {
        new Main().execute();
    }
    
    private List<Image> images;
    private ImageDisplay imageDisplay;

    public Main() {
        this.setTitle("Image Viewer");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(imagePanel());
    }
    
    private void execute() {
        this.images = new FileImageLoader(new File("fotos")).load();
        this.imageDisplay.display(images.get(0));
        this.setVisible(true);
    }

    private JPanel imagePanel() {
        ImagePanel imagePanel = new ImagePanel();
        this.imageDisplay = imagePanel;
        return imagePanel;
    }
}
