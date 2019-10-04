package app.engine.search.core;

import app.engine.search.domain.Score;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;

/**
 * Created by Ebrahim with ❤️ on 30 September 2019.
 */

@RunWith(MockitoJUnitRunner.class)
public class SearchProcessorTest {

    private Score score;
    private SearchProcessor searchProcessor;

    @Mock
    private IndexProcessor processor;


    @Before
    public void setUp() {
        searchProcessor = new SearchProcessor(processor);
        score = new Score();
    }

    @Test
    public void should_find_word_in_index() {
        doReturn(Optional.ofNullable(score)).when(processor).findIndexByText("word1");
        List<Optional<Score>> searchResult = searchProcessor.doSearch("word1");
        assertThat(searchResult.size()).isEqualTo(1);
        assertThat(searchResult.get(0).get()).isEqualTo(score);
    }

}
