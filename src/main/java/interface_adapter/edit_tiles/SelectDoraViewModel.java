package interface_adapter.edit_tiles;

import interface_adapter.ViewModel;

public class SelectDoraViewModel extends ViewModel<SelectDoraState> {
    public SelectDoraViewModel() {
        super("dora selector");
        setState(new SelectDoraState());
    }
}
