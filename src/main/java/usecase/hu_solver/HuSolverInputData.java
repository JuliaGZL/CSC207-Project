package usecase.hu_solver;

/**
 * Input data format for the Hu solver.
 */
public class HuSolverInputData {
    private final String playerName;

    public HuSolverInputData(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
