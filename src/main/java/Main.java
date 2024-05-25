import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\mihai\\IdeaProjects\\ControlWork2\\v5\\v5.png");
        FileOutputStream fos = new FileOutputStream(file);

        File files = new File("C:\\Users\\mihai\\IdeaProjects\\ControlWork2\\v5");
        String[] filesNames = files.list();

        ArrayList<FileReaderThread> threads = new ArrayList<>();
        for (String string : filesNames) {
            FileReaderThread thread = new FileReaderThread("v5/" + string);
            thread.run();
            threads.add(thread);
        }

        for (FileReaderThread thread : threads) {
            fos.write(thread.bytes);
            fos.flush();
        }
    }
}
