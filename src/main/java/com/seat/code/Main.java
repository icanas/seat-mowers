package com.seat.code;

import com.seat.code.infrastructure.Controller;
import com.seat.code.infrastructure.FileReaderInterpreter;
import com.seat.code.infrastructure.Parser;

public class Main {
    public static void main(String[] args) {
        Parser inputParser = new Parser();
        FileReaderInterpreter inputFileInterpreter = inputParser.parseInstructions(args[0]);
        Controller gridController = new Controller();
        gridController.init(inputFileInterpreter);
        gridController.startMowers();
        gridController.printMowersStatus();
    }
}