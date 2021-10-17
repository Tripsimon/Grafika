package streda_12_25_c01.controller;

import streda_12_25_c01.model.Point;
import streda_12_25_c01.model.Polygon;
import streda_12_25_c01.rasterize.*;
import streda_12_25_c01.view.Panel;

import java.awt.event.*;

public class Controller2D {

    private final Panel panel;
    private final Raster raster;
    private Polygon polygonHolder;
    private TrivialLineRasterizer lr;

    private int mx, my;

    public Controller2D(Panel panel) {
        this.panel = panel;
        this.raster = panel.getRaster();

        polygonHolder = new Polygon(raster);
        initListeners();
        
    }

    private void initListeners() {
        panel.addMouseListener(new MouseAdapter() {
            //stlačené tlačítko
            @Override
            public void mousePressed(MouseEvent e) {
                raster.clear();
                polygonHolder.addPoint(new Point(e.getX(),e.getY()));
                polygonHolder.rasterize();
            //Release tlačítka
            }
             @Override
             public void mouseReleased(MouseEvent e){


            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {

            //Pohnutí myši
            @Override
            public void mouseDragged(MouseEvent e) { //Odposlouchávání pro pohnutí myší (Znázornění vygenerování
                raster.clear();
                polygonHolder.showWhere(new Point(e.getX(),e.getY()));

            }
        });

        panel.addKeyListener(new KeyAdapter() {

            //Stlačení C = Wipe
            @Override
            public void keyReleased(KeyEvent e) { //Adapter odposlouchávací delete tlačítko
                System.out.println(e.getKeyCode());

                if (e.getKeyCode() ==67){ //67 je kod pro tlačítko C
                    raster.clear();
                    polygonHolder.clear();
                }

                if (e.getKeyCode() ==66){ //67 je kod pro tlačítko B - Odebrání posledního bodu //Polostupidní řešení Bonusu 1
                    raster.clear();
                    polygonHolder.removeLast();
                    polygonHolder.rasterize();
                }


            }
        });
    }


}
