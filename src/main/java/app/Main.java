package app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // set GUI appearance
        try {
            // TODO: just a workaround to make it looks better on my device...
            System.setProperty("sun.java2d.uiScale","2");
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        // build the app
        AppBuilder appBuilder = new AppBuilder();
        final JFrame app = appBuilder
                                    .addEditStatusView()
                                    .addPlayerEventsView()
                                    .addHandDisplayView()
                                    .addDoraDisplayView()
                                    .addUradoraDisplayView()
                                    .addTileSelectorView()
                                    .addChangePlayerSupport()
                                    .addHanReader()
                                    .build();
        app.pack();
        app.setVisible(true);
    }
}
