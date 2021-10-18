package streda_12_25_c01.rasterize;

import streda_12_25_c01.model.Line;

import java.awt.*;

public class DashedLineRasterizer extends LineRasterizer {

    public DashedLineRasterizer(Raster raster) {
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

    }
    //Deklarace proměné, díky které bude linie děratá
    int lineChecker = 0;
    @Override
    public void rasterize(int x1, int y1, int x2, int y2, Color color) {
        double dx,dy,steps,x,y,k;
        double xc,yc;


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
            if (lineChecker <=2){raster.setPixel((int)x,(int)y,color.getRGB());}lineChecker++; //Polovina pixelů, které by se měli zakreslit se nezakreslí.. tímto zpusovem je linie děravá
            if (lineChecker>=6){lineChecker =0;}
            if ((steps-k)<=3){raster.setPixel((int)x,(int)y,color.getRGB());} //Poslední 3 pixely linie budou vždy vykresleny //Bonus 2 (Rok 2020)
        }
    }

}

//rasterovací mechanizmus je identický tomu, který využívá vyplněná linie