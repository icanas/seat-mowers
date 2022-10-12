package com.seat.code.infrastructure;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileReaderInterpreter {
    private String tableuSize;
    @Getter
    private List<MowersInfo> mowersInfo = new ArrayList<>();

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
        } catch (InputMismatchException e){
            throw new RuntimeException("Error reading file");
        }
    }

    public String getTableuSize() {
        return tableuSize;
    }

    public List<String> getMowersInitialPositions() {
        return mowersInfo.stream().map(MowersInfo::getMowersInitialPosition).collect(Collectors.toList());
    }

    public List<String> getRawInstructions() {
        return mowersInfo.stream().map(MowersInfo::getRawInstructions).collect(Collectors.toList());
    }

    @Builder
    @Getter
    public static class MowersInfo {
        private String mowersInitialPosition;
        private String rawInstructions;
    }

}
