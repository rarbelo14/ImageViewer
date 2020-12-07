package imageviewer.control;

import imageviewer.apps.mock.Main;
import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitCommand implements Command{

    private final Map<String, Command> commands = new HashMap<>(); 
    
    @Override
    public void execute() {
        List<Image> images = new Main.MockImageLoader().load();
        ImageDisplay imageDisplay = new Main.MockImageDisplay();
        imageDisplay.display(images.get(0));
                
        commands.put("N", new NextImageCommand(images, imageDisplay));
        commands.put("P", new PrevImageCommand(images, imageDisplay));
        commands.put("Q", new ExitImageCommand());        
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
    
}
