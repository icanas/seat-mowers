package com.seat.code.domain;

import com.seat.code.domain.enums.CardinalPosition;
import com.seat.code.domain.enums.Operation;
import lombok.Getter;

import java.awt.*;

/**
 * This class represents a mower that can be placed and moved on a plateau.
 */
public class Mower {

    @Getter
    private final Integer id;
    private final Point position;
    private final Compass compass;
    private final Instructions instructions;
    private boolean mowerStuck = false;
    private boolean preparingToMove = false;

    private static final String TEXT_SEPARATOR = " ";

    public Mower(Integer id, Point position, Compass compass, Instructions instructions) {
        this.id = id;
        this.position = position;
        this.compass = compass;
        this.instructions = instructions;
    }

    public void executeNextInstruction() {
        Operation currentOperation = instructions.extractOperation();
        executeOperation(currentOperation);
        preparingToMove = Operation.M.equals(instructions.peakNextOperation());
    }

    public void executeAllInstructions() {
        while (instructions.operationsAvailable()) {
            Operation currentOperation = instructions.extractOperation();
            executeOperation(currentOperation);
        }
    }

    public void executeOperation(Operation operation) {
        switch (operation) {
            case M:
                move();
                break;
            case L:
                rotateLeft();
                break;
            case R:
                rotateRight();
                break;
            default:
                break;
        }
    }

    /**
     * Calculate next position assuming operation is to move forward
     * This can be used to control outOfBounds
     *
     * @return The calculated next position.
     */
    public Point calculateNextPosition() {
        Point calculatedPoint = new Point(position);
        CardinalPosition heading = compass.getHeading();
        switch (heading) {
            case N:
                calculatedPoint.translate(0, 1);
                break;
            case S:
                calculatedPoint.translate(0, -1);
                break;
            case E:
                calculatedPoint.translate(1, 0);
                break;
            case W:
                calculatedPoint.translate(-1, 0);
                break;
            default:
                break;
        }
        return calculatedPoint;
    }

    public boolean isStuck() {
        return mowerStuck;
    }

    public void emergencyStop() {
        mowerStuck = true;
    }

    public CardinalPosition getHeading() {
        return compass.getHeading();
    }

    public Point getActualPosition() {
        return position;
    }

    public boolean isPreparingToMove() {
        return preparingToMove;
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
        position.setLocation(calculatedPosition);
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
