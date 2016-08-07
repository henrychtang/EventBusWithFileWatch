package EventBusSample;

import EventBusSample.event.NewFileEvent;
import EventBusSample.event.UpdatedFileEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.util.zip.ZipFile;


public class FileReadyEventListener {


    EventBus eventBus;
    Path path;
    int lastMessage;
    public FileReadyEventListener(EventBus eventBus) {
        this.eventBus = eventBus;
    }
    @Subscribe
    public void onNewFileEvent(NewFileEvent fileReadyEvent) throws IOException {
        path = fileReadyEvent.getFilePath();
        long fileSize=Files.size(path);
        FileTime fileTime=Files.getLastModifiedTime(path);
        System.out.println(">>>>New File event recevied:" + path.getFileName());
        System.out.println(">>>>File Size:" + fileSize);
        System.out.println(">>>>File Last modified time:" + fileTime);
    }

    @Subscribe
    public void onModifiedFileEvent(UpdatedFileEvent fileReadyEvent) throws IOException {
        path = fileReadyEvent.getFilePath();
        long fileSize=Files.size(path);
        FileTime fileTime=Files.getLastModifiedTime(path);
        System.out.println(">>>>Modified File event recevied:"+ path);
        System.out.println(">>>>File Size:"+ fileSize);
        System.out.println(">>>>File Last modified time:"+ fileTime);
        if(!isValidZip(path.toFile())){
            System.out.println("Not a valid zip. Neglecting file: "+ path);
        } else {
            System.out.println("It's valid zip. Go further processing.....");
            System.out.println("1. Read the Zipfile");
            System.out.println("2. determine data type by meta data file");
            System.out.println("3. push result file into PLS");
            System.out.println("4. push error file into PLS");
        }
    }
    static boolean isValidZip(final File file) {
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(file);
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            try {
                if (zipfile != null) {
                    zipfile.close();
                    zipfile = null;
                }
            } catch (IOException e) {
            }
        }
    }
   /* @Subscribe
    public void onMesaageRecevied(OurTestEvent event) {

        lastMessage = event.getMessage();
        System.out.println(">>>>You've got a message:" + lastMessage);
    }*/

}
