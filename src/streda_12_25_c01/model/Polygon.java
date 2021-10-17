package streda_12_25_c01.model;

import streda_12_25_c01.rasterize.DashedLineRasterizer;
import streda_12_25_c01.rasterize.FilledLineRasterizer;
import streda_12_25_c01.rasterize.Raster;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Polygon {

    //Deklarace
    private List<Point> points;
    private Color color;
    private Raster raster;
    private  FilledLineRasterizer lajnovac;
    private DashedLineRasterizer Dlajnovac;

    //Konstruktor třídy
    public Polygon(Raster R) {
        points = new ArrayList<>();
        color = Color.YELLOW;
        this.raster = R;
        lajnovac =  new FilledLineRasterizer(raster);
        Dlajnovac = new DashedLineRasterizer(raster);
    }

    //rasterizace linii mezi uloženými body.
    public void rasterize() {
        if ( points.size()>= 2){
            for (int i = 1; i < points.size(); i++) { //Je potřeba projít celé pole s body
            lajnovac.rasterize(points.get(i-1).x,points.get(i-1).getY(),points.get(i).x,points.get(i).y,Color.yellow);
            }
            if (points.size()>=3){lajnovac.rasterize(points.get(0).x,points.get(0).getY(),points.get(points.size() -1).x,points.get(points.size() -1).y,Color.yellow);}
        }


    }

    //rasterizace po podržení tlačítka myši, která se ještě tvrdě nezapíše do listu bodů
    public void showWhere(Point p){
        if ( points.size()>= 1){
            for (int i = 1; i < points.size(); i++) {
                Dlajnovac.rasterize(points.get(i-1).x,points.get(i-1).getY(),points.get(i).x,points.get(i).y,Color.red);
            }
            Dlajnovac.rasterize(points.get(0).x,points.get(0).getY(),p.x,p.y,Color.red);
            Dlajnovac.rasterize(p.x,p.y,points.get(points.size() -1).x,points.get(points.size() -1).y,Color.red);
        }


    }


    //Metoda pro přidání bodu
    public void addPoint(Point J){
        System.out.println("X:" + J.x);
        System.out.println("Y:" + J.y);
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



}
