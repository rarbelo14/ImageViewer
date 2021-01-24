package imageviewer_MVC.apps.swing;

import imageviewer_MVC.control.Command;
import imageviewer_MVC.control.NextImageCommand;
import imageviewer_MVC.control.PrevImageCommand;
import imageviewer_MVC.model.Image;
import imageviewer_MVC.view.ImageDisplay;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class Main extends JFrame {
    
    public static void main(String[] args) {
        new Main().execute();
    }
    
    private List<Image> images;
    private ImageDisplay imageDisplay;
    private Map<String, Command> commands = new HashMap<>();

    public Main() {
        this.setTitle("Image Viewer");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(imagePanel());
        this.add(toolbar(), BorderLayout.SOUTH);
    }
    
    private void execute() {
        this.images = new FileImageLoader(new File("fotos")).load();
        this.imageDisplay.display(images.get(0));
        this.commands.put("<", new PrevImageCommand(images, imageDisplay));
        this.commands.put(">", new NextImageCommand(images, imageDisplay));
        this.setVisible(true);
    }

    private JPanel imagePanel() {
        ImagePanel imagePanel = new ImagePanel();
        this.imageDisplay = imagePanel;
        return imagePanel;
    }

    private JMenuBar toolbar() {        
        JMenuBar toolBar = new JMenuBar();
        toolBar.add(button("<"));
        toolBar.add(button(">"));
        return toolBar;
    }

    private JButton button(String string) {
        JButton button = new JButton(string);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get(string).execute();
            }
        });
        return button;
    }
}
