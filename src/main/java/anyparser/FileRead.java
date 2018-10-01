package anyparser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class FileRead {
    private static final Logger log = LoggerFactory.getLogger(FileRead.class);

    public static String read(String fileName) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            log.debug("파일 읽기 에러 : {}", e.getMessage());
            throw e;
        }

        return new String(bytes);
    }
}
