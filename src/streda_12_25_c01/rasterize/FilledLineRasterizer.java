package streda_12_25_c01.rasterize;

import streda_12_25_c01.model.Line;

import java.awt.*;

//Extendování (dědění) abstraktní třídy LineRasterizer
public class FilledLineRasterizer extends LineRasterizer {

    //Kontruktor třídy
    public FilledLineRasterizer(Raster raster) {
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

    // Metoda pro rasterizování zadané linie
    @Override
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
            raster.setPixel((int)x,(int)y,color.getRGB());
        }

    }
}

//Vybraný pro realizaci byl triviální algoritmus
//Jeho výhoda je například využitelnosti těžších křivek při použití správných podmínek
//Nevýhodou je neefektivnost při práci s desetinou čárkou a nutnost zaokrouhlování