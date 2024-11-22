package app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // set GUI appearance
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        // build the app
        AppBuilder appBuilder = new AppBuilder();
        final JFrame app = appBuilder
                                    .addEditStatusView()
                                    .addHandDisplayView()
                                    .addDoraDisplayView()
                                    .addTileSelectorView()
                                    .build();
        app.pack();
        app.setVisible(true);
    }
}
