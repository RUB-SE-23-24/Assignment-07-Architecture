package de.rub;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Thorsten Berger <thorsten.berger@rub.de>
 */
public class Main {

    public static void main(String[] args) {
        new InterpreterImpl( Examples.createDronologyMachine() ).runInteractive();
    }
}
