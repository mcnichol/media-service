package com.mcnichol.library;

import java.nio.file.Path;

public class TextFile implements MediaFile{
    private final String title;
    private final Path path;
    private final String filename;

    private TextFile(TextFileBuilder textFileBuilder){
        this.title = textFileBuilder.title;
        this.path = textFileBuilder.path;
        this.filename = textFileBuilder.filename;
    }

    public String getFileName() {
        return filename;
    }
    public String getTitle() {
        return title;
    }
    public Path getPath() {
        return path;
    }

    public static class TextFileBuilder {
        private String title;
        private Path path;
        private String filename;

        public TextFileBuilder title(String title) {
            this.title = title;
            return this;
        }

        public TextFileBuilder path(Path path) {
            this.path = path;
            return this;
        }

        public TextFileBuilder filename(String filename) {
            this.filename = filename;
            return this;
        }

        public TextFile build(){
            return new TextFile(this);
        }
    }
}
