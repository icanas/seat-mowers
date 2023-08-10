package com.seat.code.infrastructure;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * This class interprets a file containing information about mowers and their instructions.
 */
public class FileReaderInterpreter {

    private final String tableuSize;
    @Getter
    private List<MowersInfo> mowersInfo = new ArrayList<>();

    /**
     * Constructs a FileReaderInterpreter by reading mower information and instructions from an input stream.
     *
     * @param inputStream The input stream containing the file data.
     * @throws RuntimeException If an error occurs while reading or parsing the file.
     */
    public FileReaderInterpreter(InputStream inputStream) {
        try (Scanner scanner = new Scanner(inputStream)) {
            this.tableuSize = scanner.nextLine();
            while (scanner.hasNextLine()) {
                this.mowersInfo.add(
                        MowersInfo.builder()
                                .mowersInitialPosition(scanner.nextLine())
                                .rawInstructions(scanner.nextLine())
                                .build()
                );
            }
        } catch (InputMismatchException e) {
            throw new RuntimeException("Error reading file");
        }
    }

    /**
     * Gets the table size from the file.
     *
     * @return The table size as a string.
     */
    public String getTableuSize() {
        return tableuSize;
    }

    /**
     * Builder class for creating MowersInfo instances.
     */
    @Builder
    @Getter
    public static class MowersInfo {
        private String mowersInitialPosition;
        private String rawInstructions;
    }
}
