package usecase.read_hand;

/**
 * Input Boundary for the read hand use case.
 */
public interface ReadHandInputBoundary {
    /**
     * Executes the read hand use case.
     * @param inputData the input data
     */
    void execute(ReadHandInputData inputData);
}
