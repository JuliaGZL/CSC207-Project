package interfaceadapter;

/**
 * Model for the View Manager. Its state is the name of the View which
 * is currently active. An initial state of "" is used.
 */
public class ViewManagerModel extends ViewModel<String> {

  /**
   * Constructs a new ViewManagerModel with an initial state of an empty string.
   */
  public ViewManagerModel() {
    super("view manager");
    this.setState("");
  }

}
