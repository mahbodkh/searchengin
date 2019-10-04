package app.engine.search.core;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.regex.Pattern;

/**
 * Created by Ebrahim with ❤️ on 30 September 2019.
 */


public class TextProcessor {
    private static final Pattern specialChar = Pattern.compile("\\p{Punct}+");
    private IndexProcessor processor;


    public TextProcessor(IndexProcessor processor) {
        this.processor = processor;
    }

    public Consumer<? super String> execute(String fileName) {
        return line -> processLine(fileName, line);
    }

    private void processLine(String fileName, String line) {
        Arrays.stream(specialChar.matcher(line).replaceAll("").split(" "))
                .forEach(
                        text -> processor.addWord(fileName, text)
                );
    }
}
