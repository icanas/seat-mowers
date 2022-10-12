package com.seat.code.infrastructure;

import com.seat.code.application.factory.ComponentsFactory;
import com.seat.code.application.service.MowerService;
import com.seat.code.application.service.PlateauService;
import com.seat.code.domain.Mower;
import com.seat.code.domain.Plateau;

import java.util.List;

public class Controller {

    private PlateauService plateauService;
    private MowerService mowerService;
    private ComponentsFactory componentsFactory;

    private Plateau plateau;
    private List<Mower> mowers;

    public void init(FileReaderInterpreter inputFileInterpreter) {
        this.componentsFactory = new ComponentsFactory();
        this.plateauService = new PlateauService(inputFileInterpreter, componentsFactory);

        initPlateu(inputFileInterpreter);
        initMowers(inputFileInterpreter);
    }

    public void startMowers() {
        mowerService.run(mowers, plateauService);
    }

    public void printMowersStatus() {
        plateauService.printMowersPositions(plateau);
    }

    private void initPlateu(FileReaderInterpreter inputFileInterpreter){
        plateauService = new PlateauService(inputFileInterpreter, componentsFactory);
        this.plateau = plateauService.getPlateau();
    }

    private void initMowers(FileReaderInterpreter inputFileInterpreter){
        mowerService = new MowerService(inputFileInterpreter, componentsFactory);
        this.mowers = mowerService.getMowers();
        plateauService.addMowersToPlateau(mowers, plateau);
    }

}
