package app.engine.search.core;

import app.engine.search.domain.CategoryIndex;
import app.engine.search.domain.RankData;
import app.engine.search.domain.Score;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by Ebrahim with ❤️ on 30 September 2019.
 */


public class RankCalculator {

    public List<RankData> calculate(List<Optional<Score>> searchResults) {
        Map<String, RankData> ranking = new ConcurrentHashMap<>();
        searchResults.forEach(index -> index.ifPresent(getIndexConsumer(ranking)));

        List<RankData> results = ranking.values().stream()
                .map(rankData -> calculatePercentages(rankData, searchResults.size()))
                .sorted(this::percentageDifference)
                .collect(Collectors.toList());

        return results;
    }

    private Integer percentageDifference(RankData rank1, RankData rank2) {
        return rank2.getPercentage() - rank1.getPercentage();
    }

    private RankData calculatePercentages(RankData rankData, Integer searchedWordsNumber) {
        Integer percent = (rankData.getMatchTexts() * 100) / searchedWordsNumber;
        rankData.setPercentage(percent);
        return rankData;
    }

    private Consumer<Score> getIndexConsumer(Map<String, RankData> ranking) {
        return score -> score.getCategoryIndices().forEach(getDocumentIndexConsumer(ranking));
    }

    private Consumer<CategoryIndex> getDocumentIndexConsumer(Map<String, RankData> rankData) {
        return categoryIndex -> {
            String documentName = categoryIndex.getCategoryName();
            if (rankData.get(documentName) == null) {
                rankData.put(documentName, new RankData(documentName));
            }
            rankData.get(documentName).increaseMatchText();
            rankData.get(documentName).increaseIncident(categoryIndex.getIncident());
        };
    }

}
