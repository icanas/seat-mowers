package com.seat.code.application.service;

import com.seat.code.application.factory.ComponentsFactory;
import com.seat.code.domain.Mower;
import com.seat.code.infrastructure.FileReaderInterpreter;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.util.List;


@RequiredArgsConstructor
public class MowerService {

    private final FileReaderInterpreter inputFileInterpreter;
    private final ComponentsFactory componentsFactory;

    public List<Mower> getMowers() {
        return componentsFactory.parseMowers(inputFileInterpreter);
    }

    /**
     * Run all instructions in every Mower trough Plateau
     * @param mowers
     * @param plateauService
     */
    public void run(List<Mower> mowers, PlateauService plateauService) {
        mowers.forEach(mower -> {
            while (mower.operationsAvailable() && !mower.isStuck()) {
                if (mower.isPreparingToMove()){
                    Point nextPosition = calculateNextPosition(mower);
                    if (!plateauService.positionOutOfBounds(plateauService.getPlateau(), nextPosition)) {
                        mower.executeNextInstruction();
                    } else {
                        // TODO: control outOfBounds
                        mower.emergencyStop();
                    }
                } else {
                    mower.executeNextInstruction();
                }
                plateauService.updateMowerStatus(plateauService.getPlateau(), mower);
            }
        });
    }

    private Point calculateNextPosition(Mower mower) {
        return mower.calculateNextPosition();
    }

}
