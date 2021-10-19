package streda_12_25_c01.rasterize;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RasterBufferedImage implements Raster {

    private final BufferedImage img;
    private int clearColor;

    public RasterBufferedImage(int width, int height) {
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public void repaint(Graphics graphics) {
        graphics.drawImage(img, 0, 0, null);
        // pro zájemce - co dělá observer - https://stackoverflow.com/a/1684476
    }

    public void draw(RasterBufferedImage raster) {
        Graphics g = getGraphics();
        g.setColor(new Color(clearColor));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(raster.img, 0, 0, null);
    }

    public BufferedImage getImg() {
        return img;
    }

    public Graphics getGraphics() {
        return img.getGraphics();
    }

    @Override
    public int getPixel(int x, int y) {
        return img.getRGB(x, y);
    }

    @Override
    public void setPixel(int x, int y, int color) {
        if (x >= 0 && x< img.getWidth() && y >= 0 && y< img.getHeight()){
        img.setRGB(x, y, color);
        }
    }

    @Override
    public void clear() {
        Graphics g = getGraphics();
        g.setColor(new Color(clearColor));
        g.fillRect(0, 0, img.getWidth(), img.getHeight());
    }

    @Override
    public void setClearColor(int clearColor) {
        this.clearColor = clearColor;
    }

    @Override
    public int getWidth() {
        return img.getWidth();
    }

    @Override
    public int getHeight() {
        return img.getHeight();
    }

}
