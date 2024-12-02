package app;

import javax.swing.JFrame;

/**
 * The Main class serves as the entry point for the application.
 */
public class Main {

  /**
   * The main method sets the GUI appearance and builds the application.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    // set GUI appearance
    try {
      // TODO: just a workaround to make it looks better on my device...
      System.setProperty("sun.java2d.uiScale", "2");
      // UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
    } catch (Exception ex) {
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
        .addHandReader()
        .build();
    app.pack();
    app.setVisible(true);
  }
}
