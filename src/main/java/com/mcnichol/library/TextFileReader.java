package com.mcnichol.library;

import java.util.Arrays;

public class TextFileReader implements MediaFileReader {
    private final String[] fileTypes = {"pdf"};

    @Override
    public MediaFile readFile(MediaFile textFile) {
        validateFileType(textFile);

        return null;
    }

    private void validateFileType(MediaFile textFile) {
        String[] split = textFile.getFileName().split("\\.");
        if (!split[split.length - 1].equals(fileTypes[0])) {
            throw new InvalidFileType("File must be of types " + Arrays.toString(fileTypes));
        }
    }


}
