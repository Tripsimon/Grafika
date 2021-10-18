package streda_12_25_c01.rasterize;

import streda_12_25_c01.model.Line;

import java.awt.*;

//Abstraktní třída pro možnost využití více implementací
public abstract class LineRasterizer {

    //Deklarace proměných
    Raster raster;
    Color color;

    //Abstraktní předepsání konstruktoru
    public LineRasterizer(Raster raster) {
        this.raster = raster;
    }

    //Možnost využití dvou verzí rasterizace (Linie nebo body)
    public abstract void rasterize(Line line);

    public abstract void rasterize(int x1, int y1, int x2, int y2, Color color);

}
