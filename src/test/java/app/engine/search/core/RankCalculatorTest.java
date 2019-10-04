package app.engine.search.core;

import app.engine.search.domain.CategoryIndex;
import app.engine.search.domain.RankData;
import app.engine.search.domain.Score;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by Ebrahim with ❤️ on 30 September 2019.
 */


public class RankCalculatorTest {

    private RankCalculator rankCalculator = new RankCalculator();
    private Score score;
    private RankData rankData;
    private CategoryIndex categoryIndex;
    private List<Optional<Score>> Scores;

    @Before
    public void setUp() {
        score = new Score();
        categoryIndex = new CategoryIndex("file.txt");
        score.getCategoryIndices().add(categoryIndex);
        Scores = new ArrayList<>();
        Scores.add(Optional.ofNullable(score));
        rankData = new RankData("file.txt");
        rankData.increaseIncident(1L);
        rankData.increaseMatchText();
    }

    @Test
    public void should_rank_100_1_file() {
        rankData.setPercentage(100);
        List<RankData> rankData = rankCalculator.calculate(Scores);
        assertThat(rankData.get(0)).isEqualTo(this.rankData);
    }

    @Test
    public void should_rank_50_in_not_appear_in_1_file() {
        rankData.setPercentage(50);
        CategoryIndex documentIndex = new CategoryIndex("file2.txt");
        Score score = new Score();
        score.getCategoryIndices().add(documentIndex);
        Scores.add(Optional.ofNullable(score));
        List<RankData> rankData = rankCalculator.calculate(Scores);
        assertThat(rankData.get(0)).isEqualTo(this.rankData);
    }

    @Test
    public void should_rank_0_in_not_appear_in_0_file() {
        List<Optional<Score>> scores = new ArrayList<>();
        scores.add(Optional.empty());
        List<RankData> fileRanks = rankCalculator.calculate(scores);
        assertThat(fileRanks.size()).isEqualTo(0);
    }
}
