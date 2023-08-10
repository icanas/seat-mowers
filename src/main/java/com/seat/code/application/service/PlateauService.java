package com.seat.code.application.service;

import com.seat.code.application.factory.ComponentsFactory;
import com.seat.code.domain.Mower;
import com.seat.code.domain.Plateau;
import com.seat.code.infrastructure.FileReaderInterpreter;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * This class provides services related to the plateau and its interactions with mowers.
 */
@RequiredArgsConstructor
public class PlateauService {

    private final FileReaderInterpreter inputFileInterpreter;
    private final ComponentsFactory componentsFactory;

    /**
     * Gets the plateau from the input file interpreter.
     *
     * @return The plateau.
     */
    public Plateau getPlateau() {
        return componentsFactory.parsePlateau(inputFileInterpreter);
    }

    /**
     * Adds mowers to the plateau.
     *
     * @param mowers  The list of mowers to add.
     * @param plateau The plateau to which mowers are added.
     */
    public void addMowersToPlateau(List<Mower> mowers, Plateau plateau) {
        for (Mower mower : mowers) {
            plateau.updateMower(mower);
        }
    }

    /**
     * Prints the positions of all mowers on the plateau.
     *
     * @param plateau The plateau with mowers.
     */
    public void printMowersPositions(Plateau plateau) {
        plateau.printMowersPositions();
    }

    /**
     * Updates the status of a mower on the plateau.
     *
     * @param plateau The plateau containing the mower.
     * @param mower   The mower to update.
     */
    public void updateMowerStatus(Plateau plateau, Mower mower) {
        plateau.updateMower(mower);
    }

    /**
     * Checks if a position is out of bounds on the plateau.
     *
     * @param plateau  The plateau to check against.
     * @param position The position to check.
     * @return True if the position is out of bounds, false otherwise.
     */
    public boolean positionOutOfBounds(Plateau plateau, Point position) {
        return plateau.positionOutOfBounds(position);
    }
}
