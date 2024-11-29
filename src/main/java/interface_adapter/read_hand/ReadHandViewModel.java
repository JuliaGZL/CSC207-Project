package interface_adapter.read_hand;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Read Hand Use Case.
 */
public class ReadHandViewModel extends ViewModel<ReadHandState> {
    public static final String[] ATTRIBUTE_NAMES = {
            "Riichi", "Double Riichi", "One-shot", "Under the Sea",
            "Under the River", "After a Kan", "Robbing a Kan", "Tenhou", "Chiihou"
    };

    /**
     * Creates a new ReadHandViewModel.
     */
    public ReadHandViewModel() {
        super("read hand");
        setState(new ReadHandState());
    }
}
