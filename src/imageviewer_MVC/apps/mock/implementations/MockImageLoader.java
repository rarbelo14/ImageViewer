package imageviewer_MVC.apps.mock.implementations;

import imageviewer_MVC.model.Image;
import imageviewer_MVC.view.ImageLoader;
import java.util.ArrayList;
import java.util.List;

public class MockImageLoader implements ImageLoader{

    @Override
    public List<Image> load() {
        List<Image> list = new ArrayList<>();
        list.add(new Image("hola"));
        list.add(new Image("mundo"));
        list.add(new Image("bienvenido"));
        return list;
    }

}
