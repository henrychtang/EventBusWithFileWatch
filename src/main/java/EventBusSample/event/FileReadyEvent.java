package EventBusSample.event;

import java.nio.file.Path;

public class FileReadyEvent {
    Path filePath;
    public FileReadyEvent(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFilePath() {
        return filePath;
    }
}
