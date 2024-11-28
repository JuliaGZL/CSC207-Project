package usecase.hu_solver;

/**
 * Output data format for the Hu solver.
 */
public class HuSolverOutputData {
    private final String message;
    private final int score;

    public HuSolverOutputData(String message, int score) {
        this.message = message;
        this.score = score;
    }

    public String getMessage() {
        return message;
    }

    public int getScore() {
        return score;
    }
}
