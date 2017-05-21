package com.mcnichol.library;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private List<MediaFile> fileStore = new ArrayList<>();
    private MediaFileReader mfr;

    MediaLibrary(MediaFileReader mfr) {
        this.mfr = mfr;
    }

    void add(TextFile filePath) throws IOException {
        MediaFile file = mfr.readFile(filePath);

        BasicFileAttributes bfa = Files.readAttributes(file.getPath(), BasicFileAttributes.class);

        if (bfa.isRegularFile()) {
            fileStore.add(file);
        }
    }

    public int fileCount() {
        return fileStore.size();
    }

    public MediaFile get(String title) {
        Optional<MediaFile> first = fileStore.stream().filter(mediaFile -> mediaFile.getTitle().equals(title)).findFirst();
        return first.orElse(null);
    }
}
