package EventBusSample.watcher;

import EventBusSample.event.FileReadyEvent;
import com.google.common.eventbus.EventBus;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

public class FileWatcher {

    private EventBus eventBus;
    private String rootPath;

    public FileWatcher(EventBus eventBus, String rootPath) {
        this.eventBus = eventBus;
        this.rootPath = rootPath;
    }

    public void startFileWatcher() {
        //define a folder root
        Path myDir = Paths.get(rootPath);

        try {
            WatchService watcher = myDir.getFileSystem().newWatchService();
            myDir.register(watcher,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);

            WatchKey watchKey = watcher.take();

            while (true) {
                List<WatchEvent<?>> events = watchKey.pollEvents();
                for (WatchEvent event : events) {
                    Path path = myDir.resolve(myDir);
                    if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {

                        System.out.println("Created: " + ">>>" + path + ":" + event.context().toString());
                        eventBus.post(new FileReadyEvent(myDir.resolve(event.context().toString())));
                    }
                    if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                        System.out.println("Delete: " + path + ":" + event.context().toString());
                        eventBus.post(new FileReadyEvent(myDir.resolve(event.context().toString())));
                    }
                    if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                        System.out.println("Modify: " + path + ":" + event.context().toString());
                        eventBus.post(new FileReadyEvent(myDir.resolve(event.context().toString())));
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }
}