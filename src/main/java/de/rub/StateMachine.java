package de.rub;
import java.util.List;

    /*----------------------------------------
    Please notice that we deliver our implementation after the deadline just in order to become evaluation
    ----------------------------------------*/

public class StateMachine {
    private String name;
    private List<State> States;
    private State InitialState;

    public StateMachine () {}

    public StateMachine (String _name, List<State> _States, State _InitialState) {
        this.name = _name;
        this.States = _States;
        this.InitialState = _InitialState;
    }

    public String getName() {
        return this.name;
    }

    public List<State> getStates() {
        return this.States;
    }

    public State getInitialState () {
        return this.InitialState;
    }

}
