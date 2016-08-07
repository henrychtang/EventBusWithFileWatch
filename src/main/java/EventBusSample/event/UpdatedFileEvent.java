package EventBusSample.event;

import java.nio.file.Path;

/**
 * Created by Henry on 7/8/2016.
 */
public class UpdatedFileEvent extends FileReadyEvent {
    public UpdatedFileEvent(Path filePath) {
        super(filePath);
    }
}
