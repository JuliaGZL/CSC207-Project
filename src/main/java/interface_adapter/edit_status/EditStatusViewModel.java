package interface_adapter.edit_status;

import interface_adapter.ViewModel;

public class EditStatusViewModel extends ViewModel<EditStatusState> {

    public static final String[] TILE_TYPES = {"Hand", "Chii", "Pon", "Kan", "Ankan", "Dora", "Uradora", "Akadora"};
    public static final String[] WIN_TYPES = {"Tsumo", "Ron"};
    public static final String[] WINDS = {"Ton", "Nan", "Shaa", "Pei"};
    public static final String[] ATTRIBUTES = {"Riichi", "Double Riichi", "One-shot", "Under the Sea",
            "Under the River", "After a Kan", "Robbing a Kan", "Tenhou", "Chiihou"};

    public static final int UNDER_THE_SEA_INDEX = 3;
    public static final int UNDER_THE_RIVER_INDEX = 4;

    public EditStatusViewModel() {
        super("edit status");
        setState(new EditStatusState());
    }
}
