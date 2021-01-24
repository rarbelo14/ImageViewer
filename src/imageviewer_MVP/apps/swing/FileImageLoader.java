package imageviewer_MVP.apps.swing;

import imageviewer_MVP.model.Image;
import imageviewer_MVP.view.ImageLoader;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class FileImageLoader implements ImageLoader{

    private final File root;

    public FileImageLoader(File root) {
        this.root = root;
    }
    
    @Override
    public List<Image> load() {
        List<Image> result = new ArrayList<>();
        for (File file : root.listFiles(filter())) {
            result.add(new Image(file.getAbsolutePath()));
        }
        return result; 
    }
    
    private static final String[] imageExtensions = new String[] {"jpg", "png"};
    private FilenameFilter filter() {
        return new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                for (String extension : imageExtensions) 
                    if (name.endsWith(extension)) return true;
                return false;
            }
        };
    }
    
}
