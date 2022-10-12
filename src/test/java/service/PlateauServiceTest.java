package service;

import com.seat.code.application.service.PlateauService;
import com.seat.code.domain.Plateau;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.stream.Stream;

public class PlateauServiceTest {

    static Stream<Arguments> instructionsProvider(){
        return Stream.of(
                Arguments.of(
                        new Plateau(5,5),
                        new Point(0,0),
                        false
                ),
                Arguments.of(
                        new Plateau(0,0),
                        new Point(0,0),
                        false
                ),
                Arguments.of(
                        new Plateau(1,1),
                        new Point(2,0),
                        true
                ),
                Arguments.of(
                        new Plateau(1,1),
                        new Point(0,2),
                        true
                ),
                Arguments.of(
                        new Plateau(1,1),
                        new Point(-1,0),
                        true
                )
        );
    }

    @ParameterizedTest
    @MethodSource("instructionsProvider")
    public void testService(Plateau plateau, Point positionToMove, boolean expectedValueOutOfBounds) {
        PlateauService ps = new PlateauService(null, null);
        Assertions.assertEquals(expectedValueOutOfBounds,ps.positionOutOfBounds(plateau, positionToMove));
    }
}
