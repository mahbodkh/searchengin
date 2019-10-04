package app.engine.search.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Created by Ebrahim with ❤️ on 30 September 2019.
 */


public class CategoryReader {


    private TextProcessor processor;

    public CategoryReader(TextProcessor processor) {
        this.processor = processor;
    }

    public void processCategory(String directory) {
        try (Stream<Path> paths = Files.walk(Paths.get(directory))) {
            final AtomicInteger count = new AtomicInteger();
            paths.filter(Files::isRegularFile)
                    .forEach(file -> {
                        count.incrementAndGet();
                        contentReader(file, processor.execute(file.getFileName().toFile().getName()));
                    });
            System.out.println(count.get() + " files read in directory " + directory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void contentReader(Path fileName, Consumer<? super String> processWords) {
        try (Stream<String> stream = Files.lines(fileName)) {
            stream.forEach(processWords);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
