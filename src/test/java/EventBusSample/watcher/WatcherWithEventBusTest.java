package EventBusSample.watcher;

import EventBusSample.FileReadyEventListener;
import EventBusSample.watcher.DirWatcher;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertThat;

public class WatcherWithEventBusTest {
    @Test
    public void shouldReceiveEvent() throws Exception {

        // given
        //EventBus eventBus = new EventBus();
        EventBus eventBus = newAsyncEventBus();
        FileReadyEventListener fileReadyEventListener = new FileReadyEventListener(eventBus);
        eventBus.register(fileReadyEventListener);
        //FileWatcher fileWatcher = new FileWatcher(eventBus, "/Users/Henry/temp");
        //fileWatcher.startFileWatcher();
        try {
            // register directory and process its events
            Path dir = Paths.get("/Users/Henry/temp");
            new DirWatcher(eventBus,dir, true).processEvents();
        }catch(IOException e){
            System.out.println(e.toString());
        }

    }

    private EventBus newAsyncEventBus() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        //asyncExecutorList.add(executor);
        return new AsyncEventBus(executor);


    }
}