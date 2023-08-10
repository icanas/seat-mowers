package com.seat.code.application.service;

import com.seat.code.application.factory.ComponentsFactory;
import com.seat.code.domain.Mower;
import com.seat.code.domain.Plateau;
import com.seat.code.infrastructure.FileReaderInterpreter;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.List;

/**
 * This class provides services related to managing and running mowers.
 */
@RequiredArgsConstructor
public class MowerService {

    private final FileReaderInterpreter inputFileInterpreter;
    private final ComponentsFactory componentsFactory;

    /**
     * Gets the list of mowers from the input file interpreter.
     *
     * @return The list of mowers.
     */
    public List<Mower> getMowers() {
        return componentsFactory.parseMowers(inputFileInterpreter);
    }

    /**
     * Runs instructions for each mower on the plateau.
     *
     * @param mowers The list of mowers to run instructions for.
     * @param plateauService The plateau service to interact with the plateau.
     */
    public void run(List<Mower> mowers, PlateauService plateauService) {
        mowers.forEach(mower -> {
            while (mower.operationsAvailable() && !mower.isStuck()) {
                if (mower.isPreparingToMove()) {
                    Point nextPosition = mower.calculateNextPosition();
                    Plateau plateau = plateauService.getPlateau();
                    if (!plateauService.positionOutOfBounds(plateau, nextPosition)) {
                        mower.executeNextInstruction();
                    } else {
                        handleOutOfBounds(mower, plateau, nextPosition);
                    }
                } else {
                    mower.executeNextInstruction();
                }
                plateauService.updateMowerStatus(plateauService.getPlateau(), mower);
            }
        });
    }

    private void handleOutOfBounds(Mower mower, Plateau plateau, Point nextPosition) {
        System.out.println("Mower " + mower.getId() + " went out of bounds!");
        mower.emergencyStop();
    }
}
