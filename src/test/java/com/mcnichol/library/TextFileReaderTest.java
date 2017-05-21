package com.mcnichol.library;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class TextFileReaderTest {
    public static final String DAT_TEST_FILE = "src/test/resources/data/test.dat";
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private TextFileReader textFileReader;

    @Before
    public void setup(){
        textFileReader = new TextFileReader();
    }

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(Paths.get(DAT_TEST_FILE));
    }

    @Test
    public void onlySupportsPDF() throws Exception {
        Path file = Paths.get(DAT_TEST_FILE);
        Files.deleteIfExists(file);
        Files.createFile(file);

        TextFile dataFile = new TextFile.TextFileBuilder().path(file).filename("test.dat").build();

        thrown.expect(InvalidFileType.class);
        thrown.expectMessage("File must be of types [pdf]");
        textFileReader.readFile(dataFile);

    }
}