package streda_12_25_c01.model;

//Import
import streda_12_25_c01.rasterize.DashedLineRasterizer;
import streda_12_25_c01.rasterize.FilledLineRasterizer;
import streda_12_25_c01.rasterize.Raster;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Polygon {

    //Deklarace proměných
    private List<Point> points;
    private Raster raster;
    private int currentNearest;

    //Deklarace raterizačních tříd
    private FilledLineRasterizer lineCreator;
    private DashedLineRasterizer dashedLineCreator;

    //Konstruktor třídy
    public Polygon(Raster R) {
        points = new ArrayList<>();
        this.raster = R;

        //Inicializace rasterizačních proměných
        lineCreator = new FilledLineRasterizer(raster);
        dashedLineCreator =  new DashedLineRasterizer(raster);
    }

    //Rasterizace linii mezi uloženými body.
    public void rasterize() {
        //Rasterizace začíná od 2 bodů
        if ( points.size()>= 2){
            //Nutnost projít pole a spojit každý bod s následujícím
            for (int i = 1; i < points.size(); i++) {
            lineCreator.rasterize(points.get(i-1).x,points.get(i-1).getY(),points.get(i).x,points.get(i).y,Color.yellow);
            }

            //Rasterizace je zakončena spojením posledního bodu s  prvním
            if (points.size()>=3){
                lineCreator.rasterize(points.get(0).x,points.get(0).getY(),points.get(points.size() -1).x,points.get(points.size() -1).y,Color.yellow);}
        }
    }

    //Rasterizace po podržení tlačítka myši, která se ještě tvrdě nezapíše do listu bodů (Znázornění rasterizace)
    public void showWhere(Point p){
        if ( points.size()>= 1){
            for (int i = 1; i < points.size(); i++) {
                dashedLineCreator.rasterize(points.get(i-1).x,points.get(i-1).getY(),points.get(i).x,points.get(i).y,Color.red);
            }
            dashedLineCreator.rasterize(points.get(0).x,points.get(0).getY(),p.x,p.y,Color.red);
            dashedLineCreator.rasterize(p.x,p.y,points.get(points.size() -1).x,points.get(points.size() -1).y,Color.red);
        }


    }


    //Metoda pro přidání bodu
    public void addPoint(Point J){
        this.points.add(J);
    }

    //Metoda pro "Restart" tlačítkem C
    public void clear(){
        points.clear();
    }

    //Odebrání posledního bodu (bonus)
    public void removeLast(){
        points.remove(points.size()-1);

    }

    //Metoda pro najití nejbližšího bodu ke kurzoru
    public void findNearest(Point J){
        int dis = (int)Math.sqrt((points.get(0).x - J.getX()) * (points.get(0).x - J.getX()) + (points.get(0).y - J.getY()) * (points.get(0).y - J.getY()));
        currentNearest = 0;
        for (int i = 0; i < points.size(); i++) {
            if ((int)Math.sqrt((points.get(i).x - J.getX()) * (points.get(i).x - J.getX()) + (points.get(i).y - J.getY()) * (points.get(i).y - J.getY())) < dis){
                dis = (int)Math.sqrt((points.get(i).x - J.getX()) * (points.get(i).x - J.getX()) + (points.get(i).y - J.getY()) * (points.get(i).y - J.getY()));
                currentNearest = i;
                System.out.println("Nejbližší je hrana: "+i);
            }
        }
        dashedLineCreator.rasterize(points.get(currentNearest).x,points.get(currentNearest).y,J.getX(),J.getY(),Color.RED);
    }

    //Metoda pro znázornění nejbližšího bodu
    public void showNearestReplacePoint(Point J){
        dashedLineCreator.rasterize(points.get(currentNearest).x,points.get(currentNearest).y,J.getX(),J.getY(),Color.RED);
    }

    //Záměna bodu
    public void replaceNearest(Point J){
        points.set(currentNearest,J);
    }



}
