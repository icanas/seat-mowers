package com.seat.code.domain;

import com.seat.code.domain.enums.CardinalPosition;
import com.seat.code.domain.enums.Operation;
import lombok.Getter;

import java.awt.*;

public class Mower {
    @Getter
    private final Integer id;
    private final Point position;
    private final Compass compass;
    private final Instructions instructions;
    private boolean mowerStuck = false;
    private boolean preparingToMove = false;

    private final String TEXT_SEPARATOR = " "; // Can be moved into constants class

    public Mower(Integer id, Point position, Compass compass, Instructions instructions) {
        this.id = id;
        this.position = position;
        this.compass = compass;
        this.instructions = instructions;
    }

    public void executeNextInstruction() {
        executeOperation(instructions.extractOperation());
        if (Operation.M.equals(instructions.peakNextOperation())) {
            preparingToMove = true;
        } else {
            preparingToMove = false;
        }
    }

    public void executeAllInstructions() {
        while (instructions.operationsAvailable()) {
            executeOperation(instructions.extractOperation());
        }
    }

    public void executeOperation(Operation operation) {
        switch (operation) {
            case M -> move();
            case L -> rotateLeft();
            case R -> rotateRight();
            default -> {
            }
        }
    }

    /**
     * Calculate next position assuming operation is to move forward
     * This can be used to control outOfBounds
     *
     * @return
     */
    public Point calculateNextPosition() {
        Point calculatedPoint = new Point(position);
        CardinalPosition heading = compass.getHeading();
        switch (heading) {
            case N -> calculatedPoint.translate(0, 1);
            case S -> calculatedPoint.translate(0, -1);
            case E -> calculatedPoint.translate(1, 0);
            case W -> calculatedPoint.translate(-1, 0);
            default -> calculatedPoint.translate(0, 0);
        }
        return calculatedPoint;
    }

    public boolean isStuck() {
        return this.mowerStuck;
    }

    public void emergencyStop() {
        this.mowerStuck = true;
    }

    public CardinalPosition getHeading() {
        return this.compass.getHeading();
    }

    public Point getActualPosition() {
        return this.position;
    }

    public boolean isPreparingToMove() {
        return this.preparingToMove;
    }

    public void printActualPositionStatus() {
        printActualPosition();
        printActualHeading();
        System.out.println();
    }

    private void printActualPosition() {
        System.out.print(position.x + TEXT_SEPARATOR + position.y + TEXT_SEPARATOR);
    }

    private void printActualHeading() {
        compass.printActualHeading();
    }

    private void move() {
        Point calculatedPosition = calculateNextPosition();
        position.move(calculatedPosition.x, calculatedPosition.y);
    }

    public boolean operationsAvailable() {
        return instructions.operationsAvailable();
    }

    private void rotateLeft() {
        compass.rotateLeft();
    }

    private void rotateRight() {
        compass.rotateRight();
    }

}
