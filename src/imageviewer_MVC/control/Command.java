package imageviewer_MVC.control;

public interface Command {
    void execute();

    public class Null implements Command{
        @Override
        public void execute() {
        }        
    }
}
