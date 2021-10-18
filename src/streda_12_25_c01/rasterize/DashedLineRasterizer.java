package streda_12_25_c01.rasterize;

import streda_12_25_c01.model.Line;

import java.awt.*;

//Extendování (dědění) abstraktní třídy LineRasterizer
public class DashedLineRasterizer extends LineRasterizer {

    //Kontruktor třídy
    public DashedLineRasterizer(Raster raster) {
        super(raster);
    }

    // Metoda rasterizace pomocí linie (Převod na metodu bodovou z důvodu jednoduchosti další práce)
    @Override
    public void rasterize(Line line) {
        int x1 = line.getX1();
        int y1 = line.getY1();
        int x2 = line.getX2();
        int y2 = line.getY2();
        Color color = new Color(line.getColor());
        rasterize(x1, y1, x2, y2, color);

    }
    //Proměná, která vypočítává postuv lini
    int lineChecker = 0;
    //Stupeň výplně
    int lineDraw = 2;
    //Stupeň děravosti
    int lineHole = 6;
    @Override

    // Metoda pro rasterizování zadané linie
    public void rasterize(int x1, int y1, int x2, int y2, Color color) {
        //Deklarace proměných
        double dx,dy,steps,x,y,k;
        double xc,yc;
        dx=x2-x1;
        dy=y2-y1;

        //Zajištění správné funkčnosti napříč kvadranty
        if(Math.abs(dx)>Math.abs(dy))
            steps=Math.abs(dx);
        else
            steps=Math.abs(dy);

        //Výpočet pomocí rovnice
        xc=(dx/steps);
        yc=(dy/steps);
        x=x1;
        y=y1;

        //Loop pro projití celé přímky
        for(k=1;k<=steps;k++)
        {
            x=x+xc;
            y=y+yc;

            //Polovina pixelů, které by se měli zakreslit se nezakreslí.. tímto zpusovem je linie děravá
            if (lineChecker <=lineDraw){raster.setPixel((int)x,(int)y,color.getRGB());}lineChecker++;
            //Restart děrování
            if (lineChecker>=lineHole){lineChecker =0;}
            //Poslední 3 pixely linie budou vždy vykresleny //Bonus 2 (Rok 2020)
            if ((steps-k)<=3){raster.setPixel((int)x,(int)y,color.getRGB());}
        }
    }

}

//rasterovací mechanizmus je identický tomu, který využívá vyplněná linie