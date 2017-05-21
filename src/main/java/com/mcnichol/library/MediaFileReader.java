package com.mcnichol.library;

import java.io.IOException;

public interface MediaFileReader {
    MediaFile readFile(MediaFile mediaFile) throws IOException;
}
