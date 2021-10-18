package streda_12_25_c01.controller;

import streda_12_25_c01.model.Point;
import streda_12_25_c01.model.Polygon;
import streda_12_25_c01.model.Triangle;
import streda_12_25_c01.rasterize.*;
import streda_12_25_c01.view.Panel;

import java.awt.event.*;

public class Controller2D {

    private final Panel panel;
    private final Raster raster;
    private Polygon polygonHolder;

    private boolean triangleMode = false;
    private Triangle triangleHolder;

    public Controller2D(Panel panel) {
        this.panel = panel;
        this.raster = panel.getRaster();

        polygonHolder = new Polygon(raster);
        triangleHolder = new Triangle(raster);



        initListeners();


        
    }

    private void initListeners() {
        panel.addMouseListener(new MouseAdapter() {
            //stlačené tlačítko
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == 1) {
                    if (!triangleMode) {
                        raster.clear();
                        polygonHolder.addPoint(new Point(e.getX(), e.getY()));
                        polygonHolder.rasterize();
                    } else {
                        System.out.println(new Point(e.getX(), e.getY()));
                        triangleHolder.addPoint(new Point(e.getX(), e.getY()));
                        triangleHolder.rasterizeTriangle();
                    }
                }
                if (e.getButton() == 3){
                    polygonHolder.findNearest(new Point(e.getX(), e.getY()));
                }

            //Release tlačítka
            }
             @Override
             public void mouseReleased(MouseEvent e){
                 if (e.getButton() == 3){
                     polygonHolder.replaceNearest(new Point(e.getX(), e.getY()));
                     raster.clear();
                     polygonHolder.rasterize();
                 }


            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {

            //Pohnutí myši
            @Override
            public void mouseDragged(MouseEvent e) { //Odposlouchávání pro pohnutí myší (Znázornění vygenerování)
                if (e.getModifiersEx() == 1024){
                    System.out.println();
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

        panel.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) { //Adapter odposlouchávací delete tlačítko

                if (e.getKeyCode() ==67){ //67 je kod pro tlačítko C - Vyčištění plochy
                    raster.clear();
                    polygonHolder.clear();
                    triangleHolder.clear();
                }

                if (e.getKeyCode() ==66){ //67 je kod pro tlačítko B - Odebrání posledního bodu
                    raster.clear();
                    polygonHolder.removeLast();
                    polygonHolder.rasterize();
                }

                if (e.getKeyCode() == 84){ //84 je kod pro tlačítko T - Zapnutí trojuhelníkového módu
                    raster.clear();
                    if (triangleMode)
                    {
                        triangleMode = false;
                        System.out.println("Trojůhelníkový mod vypnut");
                    }
                    else if (!triangleMode)
                    {
                        triangleMode = true;
                        System.out.println("Trojůhelníkový mod zapnout");
                        System.out.println(triangleMode);
                    }
                }


            }
        });
    }


}
