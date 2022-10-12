package com.seat.code.infrastructure;

import java.io.*;

public class Parser {

    private final String RESOURCES_PATH = "./src/main/resources/input/";

    /**
     * Fet txt file with all information and generate a FileReaderInterpreter with all structured information
     * @param resourcesFileName
     * @return FileReaderInterpreter
     */
    public FileReaderInterpreter parseInstructions(String resourcesFileName){
        InputStream inputStream = getInputStream(resourcesFileName);
        return new FileReaderInterpreter(inputStream);
    }

    private InputStream getInputStream(String resourcesFileName){
        try {
            File initialFile = new File(RESOURCES_PATH + resourcesFileName);
            return new FileInputStream(initialFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("The file" + resourcesFileName + "does not exist");
        }
    }

}
