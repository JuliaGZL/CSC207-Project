package interfaceadapter.edittiles;

import interfaceadapter.ViewModel;

/**
 * ViewModel for selecting Dora tiles.
 */
public class SelectDoraViewModel extends ViewModel<SelectDoraState> {
  /**
   * Constructs a new SelectDoraViewModel with the default state.
   */
  public SelectDoraViewModel() {
    super("dora selector");
    setState(new SelectDoraState());
  }
}
