package com.mcnichol.library;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class MediaLibraryTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private static final String TEST_RESOURCES_URI = "src/test/resources/data/";
    private static final String TEST_MASTER_FILE_TABLE = TEST_RESOURCES_URI + "master-file-table.dat";
    private MediaLibrary mediaLibrary;
    private Path filePath_1;
    private Path filePath_2;
    private TextFile testfile_1;
    private TextFile testfile_2;

    @Before
    public void setup() throws IOException {
        String fileName_1 = "test-file-1.pdf";
        String fileName_2 = "test-file-2.pdf";


        filePath_1 = Paths.get(TEST_RESOURCES_URI + fileName_1);
        filePath_2 = Paths.get(TEST_RESOURCES_URI + fileName_2);

        Files.deleteIfExists(filePath_1);
        Files.deleteIfExists(filePath_2);

        testfile_1 = new TextFile.TextFileBuilder()
                .filename(fileName_1)
                .path(Paths.get(TEST_RESOURCES_URI)).build();
        testfile_2 = new TextFile.TextFileBuilder()
                .filename(fileName_2)
                .path(Paths.get(TEST_RESOURCES_URI)).build();

        Files.deleteIfExists(Paths.get(TEST_MASTER_FILE_TABLE));
        Files.createFile(Paths.get(TEST_MASTER_FILE_TABLE));

        mediaLibrary = new MediaLibrary(new MockTextFileReader());
    }

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(filePath_1);
        Files.deleteIfExists(filePath_2);
        Files.deleteIfExists(Paths.get(TEST_MASTER_FILE_TABLE));
    }

    @Test
    public void canAddMultipleFiles() throws Exception {
        mediaLibrary.add(testfile_1);
        mediaLibrary.add(testfile_2);

        assertEquals(mediaLibrary.fileCount(), 2);
    }

    private class MockTextFileReader implements MediaFileReader {
        @Override
        public MediaFile readFile(MediaFile mediaFile) throws IOException {
            String stringPath = mediaFile.getPath() + "/" + mediaFile.getFileName();
            Path path = Paths.get(stringPath);

            createPdf(stringPath);

            return new TextFile.TextFileBuilder().path(path).build();
        }
    }

    private void createPdf(String filename) {
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);

            PDFont font = PDType1Font.HELVETICA_BOLD;

            try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
                contents.beginText();
                contents.setFont(font, 12);
                contents.newLineAtOffset(100, 700);
                contents.showText("Some Message");
                contents.endText();
            }

            doc.save(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}