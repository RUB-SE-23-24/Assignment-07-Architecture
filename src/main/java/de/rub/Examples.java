package de.rub;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Thorsten Berger <thorsten.berger@rub.de>
 * @author Jan Sollmann <jan.sollmann@rub.de>
 */

    /*----------------------------------------
    Please notice that we deliver our implementation after the deadline just in order to become evaluation
    ----------------------------------------*/
public class Examples {

    public static final String E_ARM = "arm";
    public static final String E_UNARM = "unarm";
    public static final String E_OVER = "emergency over";
    public static final String E_VISION = "night vision ready";
    public static final String E_EMERGENCY = "emergency starts";
    public static final String E_FLIGHT = "initialize flight";
    public static final String E_FOUND = "person found";
    public static final String E_LOST = "person lost";
    public static final String E_ARRIVED = "arrived at base";
    public static final String E_SD = "shutdown";

    public static final String A_EQUIP = "Equipping drone";
    public static final String A_UNEQUIP = "Unequipping drone";
    public static final String A_ROUTE = "Computing ideal route";
    public static final String A_ABORT = "Aborting path planning";
    public static final String A_FLY = "Flying at operation level";
    public static final String A_TRACK= "Person is being tracked";
    public static final String A_RETURN = "Returning to base";
    public static final String A_LAND = "Drone is starting landing sequence";
    public static final String A_NIGHT = "WARNING: bad vision";

    // Sunset
    public static final int SUNSET = 21;

    public static StateMachine createDronologyMachine(){

        var dronepadState = new State("on dronepad",true );
        var standbyState = new State("armed");
        var nightState = new State("prepare night vision");
        var pathplanningState = new State("planning path");
        var searchingState = new State("searching area");
        var trackingState = new State("tracking");
        var returningState = new State("returning to base");

        // we add an endstate, to be able to exit the interactive interpreter
        // (not shown in the example in the slides, which is a state machine that never ends ;))
        // the Boolean constructor argument indicates whether the state is an end state or not
        var endState = new State(true);

        dronepadState.leavingTransitions.add(new Transition(E_ARM, A_EQUIP,standbyState));
        dronepadState.leavingTransitions.add(new Transition(E_SD,endState));

        // hint: you can represent the transition condition as a BooleanSupplier with a default value of ()->true
        standbyState.leavingTransitions.add( new Transition(E_EMERGENCY, A_ROUTE, () -> (LocalDateTime.now().isBefore(LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.of(SUNSET, 0)))
        ), pathplanningState));
        standbyState.leavingTransitions.add( new Transition(E_UNARM, A_UNEQUIP, dronepadState));
        standbyState.leavingTransitions.add( new Transition(E_EMERGENCY, A_NIGHT, () -> (LocalDateTime.now().isAfter(LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.of(SUNSET, 0)))
        ), nightState));

        nightState.leavingTransitions.add( new Transition(E_VISION, A_ROUTE, pathplanningState));

        pathplanningState.leavingTransitions.add( new Transition(E_OVER, A_ABORT, standbyState));
        pathplanningState.leavingTransitions.add( new Transition(E_FLIGHT, A_FLY, searchingState));

        searchingState.leavingTransitions.add( new Transition(E_FOUND, A_TRACK, trackingState));
        searchingState.leavingTransitions.add( new Transition(E_OVER, A_RETURN, returningState));

        trackingState.leavingTransitions.add( new Transition(E_LOST, A_FLY, searchingState));
        trackingState.leavingTransitions.add( new Transition(E_OVER, A_RETURN, returningState));

        returningState.leavingTransitions.add( new Transition(E_ARRIVED, A_LAND, standbyState));

        return new StateMachine( "drone", List.of( dronepadState, standbyState, nightState, pathplanningState, searchingState, trackingState, endState), dronepadState );
    }

}
