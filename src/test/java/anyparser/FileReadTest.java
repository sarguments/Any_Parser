package anyparser;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class FileReadTest {
    private static final Logger log = LoggerFactory.getLogger(FileReadTest.class);

    @Test
    public void fileRead() throws IOException {
        String json = FileRead.read("json1.txt");
        log.debug("json : {}", json);
    }
}
