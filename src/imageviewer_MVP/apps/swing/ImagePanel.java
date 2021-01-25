package imageviewer_MVP.apps.swing;

import imageviewer_MVP.model.Image;
import imageviewer_MVP.view.ImageDisplay;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

class ImagePanel extends JPanel implements ImageDisplay{

    private BufferedImage bitmap;
    private BufferedImage bitmap2;
    private Image image;
    private Shift shift;
    private int offset;        


    public ImagePanel() {
        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }
    
    @Override
    public void paint(Graphics g) {
        if (bitmap != null){
            Box box = new Box(bitmap.getWidth(), bitmap.getHeight(), this.getWidth(), this.getHeight());
            g.drawImage(bitmap, offset, box.y, box.width, box.height, null);
        }
        if (bitmap2 != null && offset != 0){
            Box box = new Box(bitmap2.getWidth(), bitmap2.getHeight(), this.getWidth(), this.getHeight());
            g.drawImage(bitmap2, offset < 0 ? bitmap.getWidth()+offset : offset-bitmap2.getWidth(), box.y, box.width, box.height, null);
        }
    }
    
    private static class Box {
        final int x;
        final int y;
        final int width;
        final int height;
        private Box(double imageWidth, double imageHeight, double panelWidth, double panelHeight) {
            double imageRatio = imageWidth / imageHeight;
            double panelRatio = panelWidth / panelHeight;
            this.width = (int) (imageRatio >= panelRatio ? panelWidth : imageWidth * panelHeight / imageHeight);                     
            this.height = (int) (imageRatio <= panelRatio ? panelHeight : imageHeight * panelWidth / imageWidth);
            this.x = (int) ((panelWidth - this.width) / 2);
            this.y = (int) ((panelHeight - this.height) / 2);
        } 
    }

    @Override
    public void display(Image image) {
        this.image = image;
        this.bitmap = loadBitmap(image);
        repaint();
    }
    
    private static BufferedImage loadBitmap(Image image){
        try{
            return ImageIO.read(new File(image.getName()));
        } catch (IOException e){
            return null;
        }
    }
    
    private Image imageAt (int shift){
        if(shift > 0) return this.shift.left();
        if(shift < 0) return this.shift.right();
        return null;
    }

    @Override
    public Image currentImage() {
        return image;
    }

    @Override
    public void on(Shift shift) {
        this.shift = shift;
    }

    private class MouseHandler implements MouseListener, MouseMotionListener{

        private int initial;

        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            this.initial = event.getX();
        }

        @Override
        public void mouseReleased(MouseEvent event) {
            int shift = shift(event.getX());
            if(Math.abs(shift) > getWidth()/2){ 
                image = imageAt(shift);
                bitmap = loadBitmap(image);
            }
            offset = 0;
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent event) {
        }

        @Override
        public void mouseExited(MouseEvent event) {
        }

        @Override
        public void mouseDragged(MouseEvent event) {
            int shift = shift(event.getX());
            if (shift == 0) bitmap2 = null;
            else if(offset/shift <= 0) bitmap2 = loadBitmap(imageAt(shift));
            offset = shift;
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent event) {
        }

        private int shift(int x) {
            return x - initial;
        }
    }
    
}
