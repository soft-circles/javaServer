package http.handlers.directory;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class FileFetcherTest {

    @Test
    void fetch() {
//        assertEquals(samples(), FileFetcher.fetch("./src/test/java/Directory/"));
    }

    private List<String> samples() {
        List<String> samples = new ArrayList<>();
        samples.add("HTMLgeneratorTest.java");
        samples.add("FileFetcherTest.java");
        return samples;
    }

    @Test
    void parseTextFile() {
    }
}