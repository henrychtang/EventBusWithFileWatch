package EventBusSample.event;

import java.nio.file.Path;

/**
 * Created by Henry on 7/8/2016.
 */
public class NewFileEvent extends FileReadyEvent {
    public NewFileEvent(Path filePath) {
        super(filePath);
    }
}

