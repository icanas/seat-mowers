package com.seat.code.domain;

import com.seat.code.domain.enums.CardinalPosition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a compass that helps manage directions.
 */
public class Compass {

    private CardinalPosition pointing;
    private final List<CardinalPosition> cardinalPositions;

    public Compass(CardinalPosition initialHeading) {
        this.pointing = initialHeading;
        this.cardinalPositions = new ArrayList<>(Arrays.asList(CardinalPosition.N, CardinalPosition.E, CardinalPosition.S, CardinalPosition.W));
    }

    /**
     * Gets the current heading of the compass.
     *
     * @return The current heading.
     */
    public CardinalPosition getHeading() {
        return pointing;
    }

    /**
     * Rotates the compass left (counter-clockwise).
     */
    public void rotateLeft() {
        int positionInCompass = cardinalPositions.indexOf(pointing);
        int positionOnLeftRotation = mod(positionInCompass - 1, cardinalPositions.size());
        pointing = cardinalPositions.get(positionOnLeftRotation);
    }

    /**
     * Rotates the compass right (clockwise).
     */
    public void rotateRight() {
        int positionInCompass = cardinalPositions.indexOf(pointing);
        int positionOnRightRotation = mod(positionInCompass + 1, cardinalPositions.size());
        pointing = cardinalPositions.get(positionOnRightRotation);
    }

    /**
     * Prints the current heading of the compass.
     */
    public void printActualHeading() {
        System.out.print(getHeading());
    }

    /**
     * Calculates the modulo operation despite negative numbers.
     *
     * @param x The dividend.
     * @param y The divisor.
     * @return The result of the operation.
     */
    private int mod(int x, int y) {
        int result = x % y;
        if (result < 0) {
            result += y;
        }
        return result;
    }
}
