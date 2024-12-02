package unit.view;


import interfaceadapter.edittiles.TileSelectorViewModel;
import javax.swing.JFrame;
import view.TileSelectorView;

public class SelectDoraViewTest {
  public static void main(String[] args) {
    TileSelectorViewModel tileSelectorViewModel = new TileSelectorViewModel();

    TileSelectorView tileSelectorView = new TileSelectorView(tileSelectorViewModel);

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(tileSelectorView);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
