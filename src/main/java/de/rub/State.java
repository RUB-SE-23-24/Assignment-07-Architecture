package de.rub;

import java.util.ArrayList;

    /*----------------------------------------
    Please notice that we deliver our implementation after the deadline just in order to become evaluation
    ----------------------------------------*/

public class State {
    private Boolean isEndState = false;
    private Boolean isInitial = false;
    private String name;

    public ArrayList <Transition> leavingTransitions;

    public State (Boolean isEndState) {
        this.isEndState = true;
        leavingTransitions = new ArrayList<>();
    }

    public State (String _name) {
        this.name = _name;
        leavingTransitions = new ArrayList<>();
    }

    public State (String _name, Boolean _isInitial ) {
        this.name = _name;
        this.isInitial = _isInitial;
        leavingTransitions = new ArrayList<>();
    }

    public Boolean getIsEndState() {
        return this.isEndState;
    }

    public Boolean getIsInitial() {
        return this.isInitial;
    }

    public String getName() {
        return this.name;
    }
}
