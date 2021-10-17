package streda_12_25_c01.app;

import streda_12_25_c01.controller.Controller2D;
import streda_12_25_c01.view.Window;

import javax.swing.*;

public class AppStart {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Window window = new Window();
            new Controller2D(window.getPanel());
            window.setVisible(true); // zobrazit okno
        });
    }

}
