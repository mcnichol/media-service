package com.mcnichol.library;

import java.nio.file.Path;

public interface MediaFile {
    String getFileName();
    String getTitle();
    Path getPath();

}
