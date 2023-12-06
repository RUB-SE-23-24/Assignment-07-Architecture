package de.rub;

    /*----------------------------------------
    Please notice that we deliver our implementation after the deadline just in order to become evaluation
    ----------------------------------------*/

public class Transition {

    private String Event;
    private String Action;
    private State Destination_State;
    private Boolean guard = true;

    public Transition (String _Event, String _Action, State _Destination_State ) {
        this.Event = _Event;
        this.Action = _Action;
        this.Destination_State = _Destination_State;
    }

    public Transition (String _Event, State _Destination_State ) {
        this.Event = _Event;
        this.Destination_State = _Destination_State;
    }

    public Transition (String _Event, String _Action, BooleanSupplier guardIsTrue, State _Destination_State ) {
        this.Event = _Event;
        this.Action = _Action;
        this.Destination_State = _Destination_State;
        if (!guardIsTrue.getAsBoolean()) {
            this.guard = false;
        }
    }

    public String getEvent() {
        return this.Event;
    }

    public String getAction() {
        return this.Action;
    }

    public State getDestination_State() {
        return this.Destination_State;
    }

    public Boolean getGuard() {
        return this.guard;
    }
}
