package usecase.hu_solver;

/**
 * Input boundary for the Hu solver
 */
public interface HuSolverInputBoundary {
    /**
     * Executes the add_tile use case.
     * @param inputData the input data
     */
    public void execute(HuSolverInputData inputData);
}
