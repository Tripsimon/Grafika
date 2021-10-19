package streda_12_25_c01.model;

import streda_12_25_c01.rasterize.DashedLineRasterizer;
import streda_12_25_c01.rasterize.FilledLineRasterizer;
import streda_12_25_c01.rasterize.Raster;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Polygon {

    //Deklarace
    private List<Point> points;
    private Raster raster;

    private FilledLineRasterizer lineCreator;
    private DashedLineRasterizer dashedLineCreator;
    private int currentNearest;

    private Color rightColor = Color.YELLOW;
    private Color dangerColor = Color.RED;

    //Konstruktor třídy
    public Polygon(Raster R) {
        points = new ArrayList<>();
        this.raster = R;

        lineCreator = new FilledLineRasterizer(raster);
        dashedLineCreator =  new DashedLineRasterizer(raster);
    }

    //rasterizace linii mezi uloženými body.
    public void rasterize() {
        if (points.size() == 1){
            raster.setPixel(points.get(0).x,points.get(0).y,rightColor.getRGB());
        }
        if ( points.size()>= 2){
            for (int i = 1; i < points.size(); i++) { //Je potřeba projít celé pole s body
            lineCreator.rasterize(points.get(i-1).x,points.get(i-1).getY(),points.get(i).x,points.get(i).y,rightColor);
            }
            if (points.size()>=3){
                lineCreator.rasterize(points.get(0).x,points.get(0).getY(),points.get(points.size() -1).x,points.get(points.size() -1).y,rightColor);}
        }
    }

    //rasterizace po podržení tlačítka myši, která se ještě tvrdě nezapíše do listu bodů
    public void showWhere(Point p){
        if ( points.size()>= 1){
            for (int i = 1; i < points.size(); i++) {
                dashedLineCreator.rasterize(points.get(i-1).x,points.get(i-1).getY(),points.get(i).x,points.get(i).y,dangerColor);
            }
            dashedLineCreator.rasterize(points.get(0).x,points.get(0).getY(),p.x,p.y,dangerColor);
            dashedLineCreator.rasterize(p.x,p.y,points.get(points.size() -1).x,points.get(points.size() -1).y,dangerColor);
        }


    }


    //Metoda pro přidání bodu
    public void addPoint(Point J){
        this.points.add(J);

        System.out.println(points.size());
    }

    //Metoda pro "Restart" tlačítkem C
    public void clear(){
        points.clear();
    }

    public void removeLast(){ //Odebrání posledního bodu
        points.remove(points.size()-1);

    }

    public void findNearest(Point J){
        int dis = (int)Math.sqrt((points.get(0).x - J.getX()) * (points.get(0).x - J.getX()) + (points.get(0).y - J.getY()) * (points.get(0).y - J.getY()));
        currentNearest = 0;
        for (int i = 0; i < points.size(); i++) {
            if ((int)Math.sqrt((points.get(i).x - J.getX()) * (points.get(i).x - J.getX()) + (points.get(i).y - J.getY()) * (points.get(i).y - J.getY())) < dis){
                dis = (int)Math.sqrt((points.get(i).x - J.getX()) * (points.get(i).x - J.getX()) + (points.get(i).y - J.getY()) * (points.get(i).y - J.getY()));
                currentNearest = i;
                System.out.println("Nejbližší je hrana: "+i);
            }
            System.out.println((int)Math.sqrt((points.get(i).x - J.getX()) * (points.get(i).x - J.getX()) + (points.get(i).y - J.getY()) * (points.get(i).y - J.getY())));
        }
        dashedLineCreator.rasterize(points.get(currentNearest).x,points.get(currentNearest).y,J.getX(),J.getY(),dangerColor);
    }

    public void showNearestReplacePoint(Point J){
        dashedLineCreator.rasterize(points.get(currentNearest).x,points.get(currentNearest).y,J.getX(),J.getY(),dangerColor);
    }

    public void replaceNearest(Point J){
        points.set(currentNearest,J);
    }



}
