package interface_adapter.display_tiles;

import interface_adapter.ViewModel;

public class TilesDisplayViewModel extends ViewModel<TilesDisplayState> {
    public TilesDisplayViewModel(String viewName) {
        super(viewName);
        setState(new TilesDisplayState());
    }
}
