package imageviewer_MVC.apps.mock;

import imageviewer_MVC.apps.mock.implementations.MockImageDisplay;
import imageviewer_MVC.apps.mock.implementations.MockImageLoader;
import imageviewer_MVC.control.Command;
import imageviewer_MVC.control.InitCommand;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private final static Command nullCommand = new Command.Null();    
    private final Scanner scanner = new Scanner(System.in);
    private final Map<String, Command> commands = new HashMap();

    public static void main(String[] args) {
        new Main().execute();
    }

    public Main() {
        InitCommand init = new InitCommand(new MockImageLoader().load(), new MockImageDisplay());
        init.execute();
        commands.putAll(init.getCommands());
    }

    private void execute() {
        while (true) commands.getOrDefault(input(), nullCommand).execute();
    }
    
    private String input() {
        return scanner.next().toUpperCase();
    }
   
}
