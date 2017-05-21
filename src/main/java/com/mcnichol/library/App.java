package com.mcnichol.library;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        TextFileReader mfr = new TextFileReader();
        MediaLibrary mediaLibrary = new MediaLibrary(mfr);
    }
}
