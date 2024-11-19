package interface_adapter.edit_status;

import interface_adapter.ViewModel;

public class EditStatusViewModel extends ViewModel<EditStatusState> {

    public static final String[] TILE_TYPES = {"Hand", "Chii", "Pon", "Kan", "Ankan", "Dora", "Uradora", "Akadora"};
    public static final String[] WIN_TYPES = {"Tsumo", "Ron"};
    public static final String[] WINDS = {"Ton", "Nan", "Shaa", "Pei"};
    public static final String[] ATTRIBUTES = {"Riichi", "Double Riichi", "One-shot", "Under the Sea",
            "Under the River", "After a Kan", "Robbing a Kan", "Tenhou", "Chiihou"};

    public static final String MAN_LABEL = "Man";
    public static final String PIN_LABEL = "Pin";
    public static final String SOU_LABEL = "Sou";
    public static final String RED_DORA_LABEL = "Red Dora";
    public static final String SELECT_INDICATOR_LABEL = "Select Dora Indicators";

    public EditStatusViewModel() {
        super("edit status");
        setState(new EditStatusState());
    }

}
