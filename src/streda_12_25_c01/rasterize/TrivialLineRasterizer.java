package streda_12_25_c01.rasterize;

import streda_12_25_c01.model.Line;

import java.awt.*;

public class TrivialLineRasterizer extends LineRasterizer {

    public TrivialLineRasterizer(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(Line line) {
        int x1 = line.getX1();
        int y1 = line.getY1();
        int x2 = line.getX2();
        int y2 = line.getY2();
        Color color = new Color(line.getColor());
        rasterize(x1, y1, x2, y2, color);
    } //Převodník pro možnou práci se zadáním pomocí Line

    @Override
    public void rasterize(int x1a, int y1a, int x2a, int y2a, Color color) {


        double dx,dy,steps,x,y,k;
        double xc,yc;
        double x1=100,y1=20,x2=20,y2=20;
        dx=x2-x1;
        dy=y2-y1;
        if(Math.abs(dx)>Math.abs(dy))
            steps=Math.abs(dx);
        else
            steps=Math.abs(dy);
        xc=(dx/steps);
        yc=(dy/steps);
        x=x1;
        y=y1;
        for(k=1;k<=steps;k++)
        {
            x=x+xc;
            y=y+yc;
            raster.setPixel((int)x,(int)y,0xFF0000);
        }
    }

}
