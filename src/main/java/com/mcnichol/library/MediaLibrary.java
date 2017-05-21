package com.mcnichol.library;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * This app can:
 * <p>
 * Add new files
 * * only supports PDF files
 * <p>
 * Can retrieve file information
 * <p>
 * Display the file information to the screen
 */
class MediaLibrary {
    private int fileCount = 0;
    private MediaFileReader mfr;

    MediaLibrary(MediaFileReader mfr) {
        this.mfr = mfr;
    }

    void add(TextFile filePath) throws IOException {
        MediaFile file = mfr.readFile(filePath);

        BasicFileAttributes bfa = Files.readAttributes(file.getPath(), BasicFileAttributes.class);
        if (bfa.isRegularFile()) fileCount += 1;
    }

    public int fileCount() {
        return fileCount;
    }
}
