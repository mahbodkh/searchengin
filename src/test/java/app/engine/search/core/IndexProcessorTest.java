package app.engine.search.core;

import app.engine.search.domain.CategoryIndex;
import app.engine.search.domain.Score;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by Ebrahim with ❤️ on 30 September 2019.
 */


public class IndexProcessorTest {

    private IndexProcessor processor = new IndexProcessor();
    private CategoryIndex categoryIndex;
    private Score score;

    @Before
    public void setUp() {
        score = new Score();
        categoryIndex = new CategoryIndex("file.txt");
        score.getCategoryIndices().add(categoryIndex);
    }

    @Test
    public void should_add_word_to_score() {
        processor.addWord("file.txt", "word");
        assertThat(processor.findIndexByText("word").get()).isEqualTo(score);
    }

    @Test
    public void should_add_several_words_to_score() {
        int max = 1000;
        int min = 1;
        Random random = new Random();
        int number = random.nextInt(max - min + 1);
        score.setIncident((long) number);
        for (int i = 1; i < number; i++) {
            categoryIndex.increase();
        }
        for (int i = 0; i < number; i++) {
            processor.addWord("file.txt", "word");
        }

        assertThat(processor.findIndexByText("word").get()).isEqualTo(score);
    }

    @Test
    public void should_add_word_to_score_from_different_files() {
        score.setIncident(2L);
        categoryIndex = new CategoryIndex("file2.txt");
        score.getCategoryIndices().add(categoryIndex);
        processor.addWord("file.txt", "word");
        processor.addWord("file2.txt", "word");

        assertThat(processor.findIndexByText("word").get()).isEqualTo(score);
    }

    @Test
    public void should_normalise_and_add_word_to_score() {
        processor.addWord("file.txt", "WORD");
        assertThat(processor.findIndexByText("word").get()).isEqualTo(score);
    }

}
