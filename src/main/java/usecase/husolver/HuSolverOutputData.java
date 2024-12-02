package usecase.husolver;

/**
 * Output data format for the Hu solver.
 */
public class HuSolverOutputData {
  private final String message;
  private final int score;

  /**
   * Constructs a new HuSolverOutputData with the specified message and score.
   *
   * @param message the message to be included in the output data
   * @param score the score to be included in the output data
   */
  public HuSolverOutputData(String message, int score) {
    this.message = message;
    this.score = score;
  }

  /**
   * Returns the message included in the output data.
   *
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Returns the score included in the output data.
   *
   * @return the score
   */
  public int getScore() {
    return score;
  }
}
