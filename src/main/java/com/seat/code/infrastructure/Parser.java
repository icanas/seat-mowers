package com.seat.code.infrastructure;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class handles parsing instructions from a txt file and generating a FileReaderInterpreter.
 */
public class Parser {

    private static final String RESOURCES_PATH = "./src/main/resources/input/";

    /**
     * Parses a txt file with instructions and generates a FileReaderInterpreter.
     *
     * @param resourcesFileName The name of the resources file.
     * @return A FileReaderInterpreter containing structured information.
     * @throws RuntimeException If an error occurs during parsing.
     */
    public FileReaderInterpreter parseInstructions(String resourcesFileName){
        try (InputStream inputStream = getInputStream(resourcesFileName)) {
            return new FileReaderInterpreter(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Error while parsing instructions: " + e.getMessage());
        }
    }

    /**
     * Retrieves an input stream for the specified resources file.
     *
     * @param resourcesFileName The name of the resources file.
     * @return An input stream for the resources file.
     * @throws IOException If an error occurs while getting the input stream.
     * @throws IllegalArgumentException If the specified file does not exist.
     */
    private InputStream getInputStream(String resourcesFileName) throws IOException {
        Path filePath = Paths.get(RESOURCES_PATH, resourcesFileName);
        if (!Files.exists(filePath)) {
            throw new IllegalArgumentException("The file " + resourcesFileName + " does not exist");
        }
        return Files.newInputStream(filePath);
    }
}
