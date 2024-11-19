package app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // set UI appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        // build the app
        AppBuilder appBuilder = new AppBuilder();
        final JFrame app = appBuilder
                                    .addEditStatusView()
                                    .addTileSelectorView()
                                    .addHandDisplayView()
                                    .addDoraDisplayView()
                                    .build();
        app.pack();
        app.setVisible(true);
    }
}
