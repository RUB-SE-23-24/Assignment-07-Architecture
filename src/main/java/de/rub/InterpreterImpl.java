package de.rub;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

    /*----------------------------------------
    Please notice that we deliver our implementation after the deadline just in order to become evaluation
    ----------------------------------------*/

public class InterpreterImpl implements Interpreter{

    private StateMachine DronologyMachine;
    private State CurrentState;
    public InterpreterImpl() {}

    public InterpreterImpl(StateMachine _DronologyMachine) {
        this.DronologyMachine = Examples.createDronologyMachine();
    }

    public List<String> run(List<String> events ) {

        List<String> actionOutputMessages = new ArrayList<>();

        State ActualState = this.DronologyMachine.getInitialState();
        int event_index = 0;
        Boolean Invalid_Input = false;

        Outer_Block : while (!ActualState.getIsEndState()){
            if (Invalid_Input) {
                break Outer_Block;
            }
            String currenEvent = events.get(event_index);
            for (int j = 0; j < ActualState.leavingTransitions.size(); j++) {
                if ( (currenEvent.equals(ActualState.leavingTransitions.get(j).getEvent())) && (ActualState.leavingTransitions.get(j).getGuard())){
                    if (ActualState.leavingTransitions.get(j).getAction() != null) {
                        actionOutputMessages.add(ActualState.leavingTransitions.get(j).getAction());}
                    ActualState = ActualState.leavingTransitions.get(j).getDestination_State();
                    event_index++;
                    continue Outer_Block;
                }
            }
            Invalid_Input = true;
        }
        return actionOutputMessages;
    }

    public void runInteractive() {
        this.CurrentState = this.DronologyMachine.getInitialState();
        Scanner input = new Scanner(System.in);
        System.out.println("/* Simple console interaction \n*******************************");

        ArrayList<Transition> possibleTransitions;

        while (!this.CurrentState.getIsEndState()) {
            this.printCurrentStateAndPossibleEvents();
            possibleTransitions =  new ArrayList<>();

            for (Transition transition : this.CurrentState.leavingTransitions) {
                possibleTransitions.add(transition);
            }

            System.out.println("*** Choose one of the possible inputs ***");

            String answer = input.nextLine();
            while (!this.ValidAnswer(possibleTransitions, answer)) {
                System.out.println("!!! Please choose one of the possible inputs !!!");
                answer = input.nextLine();
            }

            for (Transition transition : possibleTransitions) {
                if (transition.getEvent().equals(answer) && transition.getGuard() && !this.CurrentState.getIsEndState()){
                    System.out.println(transition.getAction());
                    System.out.println("\n*******************************");
                    this.CurrentState = transition.getDestination_State();
                }
            }
        }
    }

    public void printCurrentStateAndPossibleEvents() {
        System.out.println("You are in the state ("+this.CurrentState.getName()+")\nPossible input events :");
        for ( Transition t : this.CurrentState.leavingTransitions) {
            if (t.getGuard()) {
                System.out.println("--> "+t.getEvent());
            }
        }
    }

    public Boolean ValidAnswer(ArrayList<Transition> transitions, String Answer) {
        for (Transition tr : transitions) {
            if (tr.getEvent().equals(Answer)){
                return true;
            }
        }
        return false;
    }
}
