package streda_12_25_c01.model;

//Import
import streda_12_25_c01.rasterize.DashedLineRasterizer;
import streda_12_25_c01.rasterize.FilledLineRasterizer;
import streda_12_25_c01.rasterize.Raster;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//Třída pro práci v rojuhelníkovém modu
public class Triangle {
    private List<Point> points;
    private Raster raster;
    private FilledLineRasterizer lineCreator;
    private DashedLineRasterizer dashedLineCreator;
    private Point middle;
    private int halfDistance;
    private int wholeDistance;

    public Triangle(Raster R) {
        points = new ArrayList<>();
        this.raster = R;
        lineCreator =  new FilledLineRasterizer(raster);
        dashedLineCreator = new DashedLineRasterizer(raster);
    }


    //Rasterizační metoda
    public void rasterizeTriangle() {
        //Přidání prcního bodu do seznamu
        if ( points.size() == 1){

            lineCreator.rasterize(points.get(0).x,points.get(0).y,points.get(0).x,points.get(0).y,Color.BLUE);
        }
        //Přidání druhého bodu do seznamu
        //Výpočet středního bodu přímky a vzdálenosti k tomu bodu v závislosti na vytvořené příjmce
        if ( points.size() == 2){
            lineCreator.rasterize(points.get(0).x,points.get(0).y,points.get(1).x,points.get(1).y,Color.BLUE);

            int midX = (points.get(1).x + points.get(0).x)/2;
            int midY = (points.get(1).y + points.get(0).y)/2;
            middle = new Point(midX,midY);
            addPoint(middle);
            halfDistance = (int)Math.sqrt((midX - points.get(1).x) * (midX - points.get(1).x) + (midY - points.get(1).y) * (midY - points.get(1).y));

        }
        //Nalezení a vykreslení třetího bodu a odvěsen trojuhelníku. Je zde také vykreslena pomocná tečkovaná přímka k možnosti posouzení pozice kurzoru
        if (points.size() == 4){
            wholeDistance = (int)Math.sqrt((points.get(2).x - points.get(3).x) * (points.get(2).x - points.get(3).x) + (points.get(2).y - points.get(3).y) * (points.get(2).y - points.get(3).y));
            int newX = points.get(2).x - (halfDistance *(points.get(2).x - points.get(3).x))/wholeDistance;
            int newY = points.get(2).y - (halfDistance *(points.get(2).y - points.get(3).y))/wholeDistance;
            points.add(new Point(newX,newY));

            lineCreator.rasterize(points.get(0).x,points.get(0).y,points.get(1).x,points.get(1).y,Color.BLUE);
            lineCreator.rasterize(points.get(0).x,points.get(0).y,points.get(4).x,points.get(4).y,Color.BLUE);
            lineCreator.rasterize(points.get(1).x,points.get(1).y,points.get(4).x,points.get(4).y,Color.BLUE);
            dashedLineCreator.rasterize(points.get(2).x,points.get(2).y,points.get(3).x,points.get(3).y,Color.ORANGE);

        }
        //Chybová hláška
        if (points.size() > 5) {
            System.out.println("Prosím, vymažte dosavadní trojuhelník");
        }
    }

    //Přidání bodu do seznamu
    public void addPoint(Point J){

        this.points.add(J);
    }

    //Metoda pro vymazání rasteru
    public void clear(){

        points.clear();
    }

}

//Pozn.
// Zde by bylo lepší využít Abstraktní třídu Polygon a nynější třídu polygon i triangle z ní dědit. Pracuji však s projektem z minulého roku a bojím se o rozšířenější změny v balíčkování.
