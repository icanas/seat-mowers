package com.seat.code.domain;

import com.seat.code.domain.enums.CardinalPosition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Compass {
    private CardinalPosition pointing;
    private List<CardinalPosition> cardinalPositions;

    public Compass(CardinalPosition initialHeading) {
        this.pointing = initialHeading;
        this.cardinalPositions = new ArrayList<>();
        cardinalPositions.addAll(
                Arrays.asList(CardinalPosition.N, CardinalPosition.E, CardinalPosition.S, CardinalPosition.W)
        );
    }

    public CardinalPosition getHeading() {
        return pointing;
    }

    /**
     * Calculate next compass value at left side based on actual heading
     */
    public void rotateLeft() { // Modulo 4
        int positionInCompass = cardinalPositions.indexOf(pointing);
        int positionOnLeftRotation = mod(positionInCompass - 1, cardinalPositions.size()); // cardinal Positions = 4
        pointing = cardinalPositions.get(positionOnLeftRotation);
    }

    /**
     * Calculate next compass value at left side based on actual heading
     */
    public void rotateRight() {
        int positionInCompass = cardinalPositions.indexOf(pointing);
        int positionOnLeftRotation = mod(positionInCompass + 1, cardinalPositions.size());
        pointing = cardinalPositions.get(positionOnLeftRotation);
    }

    public void printActualHeading(){
        System.out.print(this.getHeading());
    }

    /**
     * Calculates module despite negative numbers
     * @param x
     * @param y
     * @return
     */
    private int mod(int x, int y)
    {
        int result = x % y;
        if (result < 0)
        {
            result += y;
        }
        return result;
    }

}
