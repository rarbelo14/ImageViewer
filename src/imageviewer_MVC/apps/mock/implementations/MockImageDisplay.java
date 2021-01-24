package imageviewer_MVC.apps.mock.implementations;

import imageviewer_MVC.model.Image;
import imageviewer_MVC.view.ImageDisplay;

public class MockImageDisplay implements ImageDisplay{

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