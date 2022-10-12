package com.seat.code.domain;

import lombok.Setter;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Plateau {
    private final int height;
    private final int width;
    @Setter
    private Map<Integer, Mower> mowersPlaced;

    public Plateau(int width, int height) {
        this.height = height;
        this.width = width;
        this.mowersPlaced = new HashMap<>();
    }

    /**
     * True if position is out of bounds
     * @param position
     * @return
     */
    public boolean positionOutOfBounds(Point position) {
        if (position.x < 0 || position.y < 0 || position.x > width || position.y > height) {
            return true;
        }
        return false;
    }

    public void updateMower(Mower mower) {
        if (mowersPlaced.containsKey(mower.getId())) {
            mowersPlaced.replace(mower.getId(), mower);
        } else {
            mowersPlaced.put(mower.getId(), mower);
        }
    }

    public void printMowersPositions() {
        mowersPlaced.forEach(
                (id, mower) -> {
                    mower.printActualPositionStatus();
                }
        );
    }
}
