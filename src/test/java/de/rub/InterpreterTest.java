package de.rub;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


/**
 * Contains a test case checking whether a turnstyle state machine is interpreted correctly by
 * an implementation of the interface {@link de.rub.Interpreter}.
 * @author thorsten.berger@rub.de
 * @author jan.sollmann@rub.de
 */
public class InterpreterTest {

    @Test
    void testDronologyMachine(){

        var drone = Examples.createDronologyMachine();
        // input events
        final List<String> events;
        // expected action output
        final List<String> output;
        if (LocalDateTime.now().isAfter(LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.of(Examples.SUNSET, 0))))
        {
            events = List.of(
                    Examples.E_ARM,
                    Examples.E_EMERGENCY,
                    Examples.E_VISION,
                    Examples.E_OVER,
                    Examples.E_EMERGENCY,
                    Examples.E_VISION,
                    Examples.E_FLIGHT,
                    Examples.E_FOUND,
                    Examples.E_LOST,
                    Examples.E_OVER,
                    Examples.E_ARRIVED,
                    Examples.E_UNARM,
                    Examples.E_SD);
            output = List.of(
                    Examples.A_EQUIP,
                    Examples.A_NIGHT,
                    Examples.A_ROUTE,
                    Examples.A_ABORT,
                    Examples.A_NIGHT,
                    Examples.A_ROUTE,
                    Examples.A_FLY,
                    Examples.A_TRACK,
                    Examples.A_FLY,
                    Examples.A_RETURN,
                    Examples.A_LAND,
                    Examples.A_UNEQUIP);
        }
        else
        {
            events = List.of(
                    Examples.E_ARM,
                    Examples.E_EMERGENCY,
                    Examples.E_OVER,
                    Examples.E_EMERGENCY,
                    Examples.E_FLIGHT,
                    Examples.E_FOUND,
                    Examples.E_LOST,
                    Examples.E_OVER,
                    Examples.E_ARRIVED,
                    Examples.E_UNARM,
                    Examples.E_SD);
            output = List.of(
                    Examples.A_EQUIP,
                    Examples.A_ROUTE,
                    Examples.A_ABORT,
                    Examples.A_ROUTE,
                    Examples.A_FLY,
                    Examples.A_TRACK,
                    Examples.A_FLY,
                    Examples.A_RETURN,
                    Examples.A_LAND,
                    Examples.A_UNEQUIP);
        }
        Assertions.assertIterableEquals(new InterpreterImpl( drone ).run( events ), output);
    }

}
