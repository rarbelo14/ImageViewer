package imageviewer_MVC.view;

import imageviewer_MVC.model.Image;

public interface ImageDisplay {
    void display(Image image);

    public Image currentImage();
}
