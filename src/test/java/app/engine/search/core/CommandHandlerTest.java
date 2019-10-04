package app.engine.search.core;

import app.engine.search.domain.RankData;
import app.engine.search.domain.Score;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by Ebrahim with ❤️ on 30 September 2019.
 */

@RunWith(MockitoJUnitRunner.class)
public class CommandHandlerTest {

    private InputStream anyInputStream;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final String words = "word1 word2";
    private CommandHandler commandHandler;
    private List<RankData> rankData = new ArrayList<>();

    @Mock
    private SearchProcessor searchProcessor;
    @Mock
    private RankCalculator rankCalculator;
    @Mock
    private List<Optional<Score>> searchResults;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        anyInputStream = new ByteArrayInputStream(words.getBytes());
        RankData rankData = new RankData("file.txt");

        rankData.setPercentage(100);
        rankData.increaseIncident(1L);
        rankData.increaseMatchText();
        this.rankData.add(rankData);

        commandHandler = new CommandHandler(searchProcessor, rankCalculator);
    }

    @Test
    public void should_propagate_words_to_search_engine() {
        Scanner input = new Scanner(anyInputStream);
        commandHandler.execute(input);
        verify(searchProcessor).doSearch(words);
    }

    @Test
    public void should_prompt_words_before_search() {
        Scanner input = new Scanner(anyInputStream);
        commandHandler.execute(input);
        assertThat(outContent.toString()).isEqualTo("search> Searching word1 word2 ...\n" +
                "search> ");
    }

    @Test
    public void should_prompt_no_matches_when_no_results() {
        Scanner input = new Scanner(anyInputStream);
        commandHandler.execute(input);
        assertThat(errContent.toString().trim()).isEqualTo("no matches found");
    }

    @Test
    public void should_prompt_results() {
        Scanner input = new Scanner(anyInputStream);
        doReturn(searchResults).when(searchProcessor).doSearch(words);
        doReturn(1).when(searchResults).size();
        doReturn(rankData).when(rankCalculator).calculate(searchResults);
        commandHandler.execute(input);

        assertThat(outContent.toString()).isEqualTo("search> Searching word1 word2 ...\n" +
                "file.txt(1 incident) : 100%\n" +
                "search> ");
    }

    @Test
    public void should_exit_when_quit_command() {
        ByteArrayInputStream quitInputStream = new ByteArrayInputStream(":quit".getBytes());
        Scanner input = new Scanner(quitInputStream);
        commandHandler.execute(input);

        assertThat(outContent.toString()).isEqualTo("search> ");
        assertThat(errContent.toString()).isEqualTo("");
    }

}
