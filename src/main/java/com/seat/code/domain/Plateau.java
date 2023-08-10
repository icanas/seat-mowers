package com.seat.code.domain;

import lombok.Setter;

import java.awt.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the plateau where mowers are placed and moved.
 */
public class Plateau {

    private final int height;
    private final int width;
    @Setter
    private Map<Integer, Mower> mowersPlaced;

    /**
     * Constructs a Plateau with the specified width and height.
     *
     * @param width  The width of the plateau.
     * @param height The height of the plateau.
     */
    public Plateau(int width, int height) {
        this.height = height;
        this.width = width;
        this.mowersPlaced = new HashMap<>();
    }

    /**
     * Checks if the given position is out of bounds of the plateau.
     *
     * @param position The position to check.
     * @return True if the position is out of bounds, false otherwise.
     */
    public boolean positionOutOfBounds(Point position) {
        return position.x < 0 || position.y < 0 || position.x > width || position.y > height;
    }

    /**
     * Updates the position of a mower on the plateau.
     *
     * @param mower The mower to update.
     */
    public void updateMower(Mower mower) {
        mowersPlaced.put(mower.getId(), mower);
    }

    /**
     * Prints the positions of all mowers on the plateau.
     */
    public void printMowersPositions() {
        mowersPlaced.forEach(
                (id, mower) -> {
                    mower.printActualPositionStatus();
                }
        );
    }
}
