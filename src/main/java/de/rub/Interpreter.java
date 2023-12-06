package de.rub;

import java.util.List;

/**
 * @author Thorsten Berger <thorsten.berger@rub.de>
 */

    /*----------------------------------------
    Please notice that we deliver our implementation after the deadline just in order to become evaluation
    ----------------------------------------*/
public interface Interpreter {

    /**
     * Runs the state machine based on the input provided in the list input.
     * It immediately stops when an end state is reached or when the input is invalid.
     * In that case, it returns the list of outputs created so far during execution.
     * @param events a list of events, each of which trigger a transition.
     * @return the list of action output messages; when no output is created by a transition, no element
     * is added to the list.
     */
    public List<String> run(List<String> events );

    /**
     * Prints the start state (#printCurrentStateAnInputs) and possible input events.
     * Then, reads input events from the console and outputs the transitions' action output when defined.
     * It also prints the current state after each input. The method returns when a final state is reached.
     */
    public void runInteractive();

    /**
     * Prints the current state and available input events (i.e., all events of transitions leaving from
     * the current state).
     */
    void printCurrentStateAndPossibleEvents();
}
