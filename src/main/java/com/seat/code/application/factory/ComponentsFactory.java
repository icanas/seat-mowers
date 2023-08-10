package com.seat.code.application.factory;

import com.seat.code.domain.Compass;
import com.seat.code.domain.Instructions;
import com.seat.code.domain.Mower;
import com.seat.code.domain.Plateau;
import com.seat.code.domain.enums.CardinalPosition;
import com.seat.code.domain.enums.Operation;
import com.seat.code.infrastructure.FileReaderInterpreter;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class used to generate Plateau, Mowers, Compass, and Instruction set from FileReaderInterpreter.
 */
public class ComponentsFactory {

    private final String SEPARATOR = " ";
    private final String WRONG_CONVERSION_EXCEPTION = " Can not convert string into integer";

    /**
     * Parses the plateau information from the input file interpreter.
     *
     * @param inputFileInterpreter The input file interpreter.
     * @return The generated Plateau object.
     * @throws IllegalArgumentException If the tableau size is not valid.
     */
    public Plateau parsePlateau(FileReaderInterpreter inputFileInterpreter) {
        String[] splittedRawInfo = split(inputFileInterpreter.getTableuSize());
        if (splittedRawInfo.length != 2) {
            throw new IllegalArgumentException("Wrong tableau Size");
        }
        try {
            int width = getInteger(splittedRawInfo[0]);
            int height = getInteger(splittedRawInfo[1]);
            return new Plateau(width, height);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Parses the list of mowers from the input file interpreter.
     *
     * @param inputFileInterpreter The input file interpreter.
     * @return The list of generated Mower objects.
     * @throws IllegalArgumentException If parsing the mower information fails.
     */
    public List<Mower> parseMowers(FileReaderInterpreter inputFileInterpreter) {
        List<Mower> mowersList = new ArrayList<>();
        AtomicInteger id = new AtomicInteger();
        inputFileInterpreter.getMowersInfo().forEach(mi -> {
            mowersList.add(parseMower(id.getAndIncrement(), mi.getMowersInitialPosition(), mi.getRawInstructions()));
        });
        return mowersList;
    }

    private Mower parseMower(Integer id, String initialPosition, String rawInstructions) {
        try {
            String[] initialPositionArray = split(initialPosition);
            Compass compass = new Compass(getCardinalPosition(initialPositionArray[2]));
            Instructions instructions = new Instructions(generateInstructionsStack(rawInstructions));
            return new Mower(id, generateInitialPosition(initialPositionArray[0], initialPositionArray[1]), compass, instructions);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private Point generateInitialPosition(String x, String y) {
        return new Point(Integer.parseInt(x), Integer.parseInt(y));
    }

    private Stack<Operation> generateInstructionsStack(String rawInstructions) {
        Stack<Operation> instructionStack = new Stack<>();
        char[] chars = rawInstructions.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            instructionStack.push(Operation.valueOf(chars[i]));
        }
        return instructionStack;
    }

    private String[] split(String stringToSplit) {
        return stringToSplit.split(SEPARATOR);
    }

    /**
     * Gets a valid cardinal position based on the input string.
     *
     * @param strOp The input string representing the cardinal position.
     * @return The corresponding CardinalPosition enum value.
     * @throws RuntimeException If the input cardinal point is not recognized.
     */
    private CardinalPosition getCardinalPosition(String strOp) {
        if (strOp == null) {
            throw new RuntimeException("Operation parsed is null");
        }
        try {
            return CardinalPosition.valueOf(strOp);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Cardinal point " + strOp + " not recognized");
        }
    }

    /**
     * Converts a string to an integer, checking for valid conversion.
     *
     * @param strNum The input string to convert.
     * @return The converted integer.
     * @throws NumberFormatException If the string cannot be converted to a non-negative integer.
     */
    private int getInteger(String strNum) throws NumberFormatException {
        try {
            int i = Integer.parseInt(strNum);
            if (i < 0) throw new NumberFormatException("Value cannot be negative");
            return i;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage() + WRONG_CONVERSION_EXCEPTION);
        }
    }
}
