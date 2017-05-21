package com.mcnichol.library;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.runners.Suite.*;

@RunWith(Suite.class)
@SuiteClasses({
        MediaLibraryTest.class,
        TextFileReaderTest.class
})
public class AllTestSuite {}
