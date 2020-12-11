package imageviewer.apps.swing;

import java.awt.PopupMenu;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
    public static void main(String[] args) {
        new Main().execute();
    }

    public Main() {
        this.setTitle("Image Viewer");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(imagePanel());
    }
    
    private void execute() {
        this.setVisible(true);
    }

    private JPanel imagePanel() {
        ImagePanel imagePanel = new ImagePanel();
        return imagePanel;
    }
}
