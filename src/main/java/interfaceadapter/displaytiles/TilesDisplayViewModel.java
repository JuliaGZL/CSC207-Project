package interfaceadapter.displaytiles;

import interfaceadapter.ViewModel;

/**
 * ViewModel for displaying tiles.
 */
public class TilesDisplayViewModel extends ViewModel<TilesDisplayState> {

  /**
   * Constructs a TilesDisplayViewModel with the specified view name.
   *
   * @param viewName the name of the view
   */
  public TilesDisplayViewModel(String viewName) {
    super(viewName);
    setState(new TilesDisplayState());
  }
}
