package imageviewer_MVP.apps.swing;

import imageviewer_MVP.control.ImagePresenter;
import imageviewer_MVP.model.Image;
import imageviewer_MVP.view.ImageDisplay;
import java.io.File;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {

    public static void main(String[] args){
        new Main().execute();
    }
    
    private ImagePanel imageDisplay;
    private ImagePresenter imagePresenter;

    public Main() {
        this.setTitle("Image Viewer");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(imagePanel());
        List<Image> images = new FileImageLoader(new File("fotos")).load();
        this.imageDisplay.display(images.get(0));
        this.imagePresenter = new ImagePresenter(images, imageDisplay);
    }

    private void execute() {
        this.setVisible(true);
    }
    
    private JPanel imagePanel() {
        ImagePanel imagePanel = new ImagePanel();
        this.imageDisplay = imagePanel;
        return imagePanel;
    }
    
}
