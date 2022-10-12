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

@RequiredArgsConstructor
public class PlateauService {

    private final FileReaderInterpreter inputFileInterpreter;
    private final ComponentsFactory componentsFactory;

    public Plateau getPlateau() {
        return componentsFactory.parsePlateau(inputFileInterpreter);
    }

    public void addMowersToPlateau(List<Mower> mowers, Plateau plateau) {
        AtomicInteger index = new AtomicInteger();
        Map<Integer, Mower> mowersMap = mowers.stream()
                .collect(Collectors.toMap((m) -> index.getAndIncrement(), (m) -> m));
        plateau.setMowersPlaced(mowersMap);
    }

    public void printMowersPositions(Plateau plateau) {
        plateau.printMowersPositions();
    }

    public void updateMowerStatus(Plateau plateau, Mower mower) {
        plateau.updateMower(mower);
    }

    public boolean positionOutOfBounds(Plateau plateau, Point position) {
        return plateau.positionOutOfBounds(position);
    }

}
