package app.engine.search.core;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.Mockito.verify;

/**
 * Created by Ebrahim with ❤️ on 30 September 2019.
 */

@RunWith(MockitoJUnitRunner.class)
public class TextProcessorTest {

    private TextProcessor textProcessor;
    @Mock
    private IndexProcessor processor;


    @Before
    public void setUp() {
        textProcessor = new TextProcessor(processor);
    }

    @Test
    public void should_add_line_words_to_indexProcessor() {
        Arrays.asList("Galaxy A50 Triple camera").stream().forEach(
                textProcessor.execute("sampleCatTest00.txt"));

        Arrays.asList("Galaxy", "A50", "Triple", "camera").forEach(word -> {
            verify(processor).addWord("sampleCatTest00.txt", word);
        });
    }

    @Test
    public void should_ignore_special_characters() {
        Arrays.asList("Galaxy. A50, Triple; camera-").stream().forEach(
                textProcessor.execute("sampleCatTest00.txt"));

        Arrays.asList("Galaxy", "A50", "Triple", "camera").forEach(word -> {
            verify(processor).addWord("sampleCatTest00.txt", word);
        });
    }
}
