package streda_12_25_c01.controller;

import streda_12_25_c01.model.Point;
import streda_12_25_c01.model.Polygon;
import streda_12_25_c01.model.Triangle;
import streda_12_25_c01.rasterize.*;
import streda_12_25_c01.view.Panel;

import java.awt.event.*;

public class Controller2D {

    //Vykreslovací proměné
    private final Panel panel;
    private final Raster raster;
    private Polygon polygonHolder;

    //Deklarace proměných pro práci s módem trojuhelníku
    private boolean triangleMode = false;
    private Triangle triangleHolder;

    //Kontruktor třídy
    public Controller2D(Panel panel) {
        //Inicializace plátna a rasteru
        this.panel = panel;
        this.raster = panel.getRaster();

        //Inicializace proměné polygonu a trojuhelníku
        polygonHolder = new Polygon(raster);
        triangleHolder = new Triangle(raster);

        initListeners();
    }

    //Metoda pro inicializaci listenerů pomocí adaptéru
    private void initListeners() {
        //Adaptér pro práci s myší
        panel.addMouseListener(new MouseAdapter() {

            //Adaptér pro stlačení tlačítek myší
            @Override
            public void mousePressed(MouseEvent e) {
                //Dělení podle módu práce
                if (e.getButton() == 1) {
                    if (!triangleMode) {
                        raster.clear();
                        polygonHolder.addPoint(new Point(e.getX(), e.getY()));
                        polygonHolder.rasterize();
                    } else {
                        triangleHolder.addPoint(new Point(e.getX(), e.getY()));
                        triangleHolder.rasterizeTriangle();
                    }
                }
                if (e.getButton() == 3){
                    polygonHolder.findNearest(new Point(e.getX(), e.getY()));
                }

                //Adaptér pro release tlačítek myší
            }
             @Override
             public void mouseReleased(MouseEvent e){

                 //Dělení podle módu práce

                 if (e.getButton() == 3){
                     polygonHolder.replaceNearest(new Point(e.getX(), e.getY()));
                     raster.clear();
                     polygonHolder.rasterize();
                 }
            }
        });

        //Adaptér pro práci s pohybem myši
        panel.addMouseMotionListener(new MouseAdapter() {

            //Odposlouchávání pro pohnutí myší (Znázornění vygenerování)
            @Override
            public void mouseDragged(MouseEvent e) {
                //Dělení podle stlačeného tlačítka
                if (e.getModifiersEx() == 1024){
                    raster.clear();
                    polygonHolder.showWhere(new Point(e.getX(),e.getY()));
                }
                if (e.getModifiersEx() == 4096){
                    raster.clear();
                    polygonHolder.rasterize();
                    polygonHolder.showNearestReplacePoint(new Point(e.getX(), e.getY()));
                }

            }
        });

        //Adaptér pro práci s klávesnicí
        panel.addKeyListener(new KeyAdapter() {

            //Adapter pro release tlačítka klávesnice
            @Override
            public void keyReleased(KeyEvent e) {

                //67 je kod pro tlačítko C - Vyčištění plochy
                if (e.getKeyCode() ==67){
                    raster.clear();
                    polygonHolder.clear();
                    triangleHolder.clear();
                }

                //66 je kod pro tlačítko B - Odebrání posledního bodu
                if (e.getKeyCode() ==66){
                    raster.clear();
                    polygonHolder.removeLast();
                    polygonHolder.rasterize();
                }

                //84 je kod pro tlačítko T - Zapnutí trojuhelníkového módu
                if (e.getKeyCode() == 84){
                    //Nutnost čištění !
                    raster.clear();

                    //Dělení pro přepínání módu práce
                    if (triangleMode)
                    {
                        triangleMode = false;
                        System.out.println("Trojůhelníkový mod vypnut");
                    }
                    else if (!triangleMode)
                    {
                        triangleMode = true;
                        System.out.println("Trojůhelníkový mod zapnout");
                    }
                }


            }
        });
    }


}
