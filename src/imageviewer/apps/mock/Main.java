package imageviewer.apps.mock;

import imageviewer.control.Command;
import imageviewer.control.ExitImageCommand;
import imageviewer.control.NextImageCommand;
import imageviewer.control.PrevImageCommand;
import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import imageviewer.view.ImageLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    
    private final Scanner scanner = new Scanner(System.in);
    private final Map<String, Command> commands = new HashMap();

    public static void main(String[] args) {
        new Main().execute();
    }

    public Main() {
        List<Image> images = new MockImageLoader().load();
        ImageDisplay imageDisplay = new MockImageDisplay();
        imageDisplay.display(images.get(0));
                
        commands.put("N", new NextImageCommand(images, imageDisplay));
        commands.put("P", new PrevImageCommand(images, imageDisplay));
        commands.put("Q", new ExitImageCommand());
    }

    private void execute() {
        while (true) commands.get(input()).execute();
    }
    
    private String input() {
        return scanner.next().toUpperCase();
    }
    
    public static class MockImageDisplay implements ImageDisplay{

        private Image image;

        @Override
        public void display(Image image) {
            this.image = image;
            System.out.println(image.getName());
        }

        @Override
        public Image currentImage() {
            return image;
        }
        
    }
    
    public static class MockImageLoader implements ImageLoader{

        @Override
        public List<Image> load() {
            List<Image> list = new ArrayList<>();
            list.add(new Image("hola"));
            list.add(new Image("mundo"));
            list.add(new Image("bienvenido"));
            return list;
        }
        
    }
    
}
