/*
 Copyright (c) 2024 YAizhou, li-tianchu, JuliaGZL, TurkeyBilly

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

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
      System.setProperty("sun.java2d.uiScale", "1.50");
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
