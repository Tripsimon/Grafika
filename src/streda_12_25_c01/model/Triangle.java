package streda_12_25_c01.model;

import streda_12_25_c01.rasterize.DashedLineRasterizer;
import streda_12_25_c01.rasterize.FilledLineRasterizer;
import streda_12_25_c01.rasterize.Raster;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Triangle {
    private List<Point> points;
    private Raster raster;
    private FilledLineRasterizer lineCreator;
    private DashedLineRasterizer dashedLineCreator;
    private Point middle;
    private int halfDistance;
    private int wholeDistance;

    private Color rightColor = Color.LIGHT_GRAY;
    private Color dangerColor = Color.ORANGE;

    public Triangle(Raster R) {
        points = new ArrayList<>();
        this.raster = R;
        lineCreator =  new FilledLineRasterizer(raster);
        dashedLineCreator = new DashedLineRasterizer(raster);
    }



    public void rasterizeTriangle() {
        if ( points.size() == 1){

            lineCreator.rasterize(points.get(0).x,points.get(0).y,points.get(0).x,points.get(0).y,rightColor);
        }
        if ( points.size() == 2){
            lineCreator.rasterize(points.get(0).x,points.get(0).y,points.get(1).x,points.get(1).y,rightColor);

            int midX = (points.get(1).x + points.get(0).x)/2;
            int midY = (points.get(1).y + points.get(0).y)/2;
            middle = new Point(midX,midY);
            addPoint(middle);
            halfDistance = (int)Math.sqrt((midX - points.get(1).x) * (midX - points.get(1).x) + (midY - points.get(1).y) * (midY - points.get(1).y));

        }
        if (points.size() == 4){
            wholeDistance = (int)Math.sqrt((points.get(2).x - points.get(3).x) * (points.get(2).x - points.get(3).x) + (points.get(2).y - points.get(3).y) * (points.get(2).y - points.get(3).y));
            int newX = points.get(2).x - (halfDistance *(points.get(2).x - points.get(3).x))/wholeDistance;
            int newY = points.get(2).y - (halfDistance *(points.get(2).y - points.get(3).y))/wholeDistance;
            points.add(new Point(newX,newY));

            lineCreator.rasterize(points.get(0).x,points.get(0).y,points.get(1).x,points.get(1).y,rightColor);
            lineCreator.rasterize(points.get(0).x,points.get(0).y,points.get(4).x,points.get(4).y,rightColor);
            lineCreator.rasterize(points.get(1).x,points.get(1).y,points.get(4).x,points.get(4).y,rightColor);
            dashedLineCreator.rasterize(points.get(2).x,points.get(2).y,points.get(3).x,points.get(3).y,dangerColor);

        }
        if (points.size() > 5) {
            System.out.println("Prosím, vymažte dosavadní trojuhelník");
        }
    }

    public void addPoint(Point J){
        this.points.add(J);
    }

    public void clear(){
        points.clear();
    }

}
