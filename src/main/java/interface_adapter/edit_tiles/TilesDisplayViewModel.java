package interface_adapter.edit_tiles;

import interface_adapter.ViewModel;

public class TilesDisplayViewModel extends ViewModel<TilesDisplayState> {
    public TilesDisplayViewModel(String viewName) {
        super(viewName);
        setState(new TilesDisplayState());
    }
}
