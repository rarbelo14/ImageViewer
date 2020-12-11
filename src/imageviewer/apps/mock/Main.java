package imageviewer.apps.mock;

import imageviewer.apps.mock.implementations.MockImageDisplay;
import imageviewer.apps.mock.implementations.MockImageLoader;
import imageviewer.control.Command;
import imageviewer.control.InitCommand;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    //53:41
    // Practicas en aula par grupo 2
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
