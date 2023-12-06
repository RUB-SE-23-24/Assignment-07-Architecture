package de.rub;


/**
 * @author Thorsten Berger <thorsten.berger@rub.de>
 */

/*

    Aymen  Ramadan  :  1080  2021  6583
    Abdulrohman  Atasi  :  1080   2023  5709
    Omar  Al-Akkad  :  1080  2121  9559
    ----------------------------------------
    Please notice that we deliver our implementation after the deadline just in order to become evaluation
    ----------------------------------------
    1. Abstract/concrete syntax :
    - The classes StateMachine, State, and Transition represent an abstract syntax and in addition
     to that the attributes : name (StateMachine) , Event and Action (Transition) and methods like :
      run( ) , runInteractive( ) are all examples of the abstract syntax, which refers to concepts,
       characteristics and relationships in the system (project).
    - And so the concrete syntax is simply the implementation of the abstract syntax.

    2. Static/dynamic semantics :
    - Dynamic semantic : With methods like : run( ) and runInteractive( ) we can see how the drone´s state
     change from a state to another which include changing the current state,
     it´s output transitions (possible events), status of guard and so on.
    - Static semantic : the names of the states, events, actions and the declaration of their types.

*/

public class Main {

    public static void main(String[] args) {
        new InterpreterImpl( Examples.createDronologyMachine() ).runInteractive();
    }
}
