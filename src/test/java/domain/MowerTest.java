package domain;

import com.seat.code.domain.Compass;
import com.seat.code.domain.Instructions;
import com.seat.code.domain.Mower;
import com.seat.code.domain.enums.CardinalPosition;
import com.seat.code.domain.enums.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.Collections;
import java.util.Stack;
import java.util.stream.Stream;

public class MowerTest {

    private Compass compass = new Compass(CardinalPosition.N);
    private Point initialPosition = new Point(0,0);
    private Instructions instructions;

    static Stream<Arguments>instructionsProvider(){
        return Stream.of(
                Arguments.of(
                        new Stack<Operation>(){{
                            push(Operation.M);
                            push(Operation.M);
                            push(Operation.M);
                            push(Operation.M);
                        }},
                        CardinalPosition.N,
                        new Point(0,4)
                ),
                Arguments.of(
                        new Stack<Operation>(){{
                            push(Operation.M);
                            push(Operation.L);
                            push(Operation.L);
                            push(Operation.M);
                        }},
                        CardinalPosition.S,
                        new Point(0,0)
                ),
                Arguments.of(
                        new Stack<Operation>(){{
                            push(Operation.L);
                            push(Operation.L);
                            push(Operation.L);
                            push(Operation.L);
                        }},
                        CardinalPosition.N,
                        new Point(0,0)
                ),
                Arguments.of(
                        new Stack<Operation>(){{
                            push(Operation.R);
                            push(Operation.M);
                            push(Operation.M);
                            push(Operation.M);
                        }},
                        CardinalPosition.E,
                        new Point(3,0)
                )
            );
    }

    @ParameterizedTest
    @MethodSource("instructionsProvider")
    public void testMowerMove(Stack inverseInstructionStack, CardinalPosition expectedHeading, Point expectedPosition) {
        Mower mower = new Mower(0, initialPosition, compass, createInstructionsStack(inverseInstructionStack));
        mower.executeAllInstructions();
        Assertions.assertEquals(expectedHeading, mower.getHeading());
        Assertions.assertEquals(expectedPosition, mower.getActualPosition());
    }

    private Instructions createInstructionsStack(Stack<Operation> instructionStack) {
        revertStack(instructionStack);
        return new Instructions(instructionStack);
    }

    private void revertStack(Stack<Operation> instructionStack){
        Collections.reverse(instructionStack);
    }

}
