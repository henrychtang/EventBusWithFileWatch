package EventBusSample.event;

/**
 * Created by Henry on 6/8/2016.
 */
public class WatchRepositoryEvent {
String repository;

    public WatchRepositoryEvent(String repository) {
        this.repository = repository;
    }

    public String getRepository() {
        return repository;
    }
}
