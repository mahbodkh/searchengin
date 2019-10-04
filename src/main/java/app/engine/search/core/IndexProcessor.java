package app.engine.search.core;

import app.engine.search.domain.CategoryIndex;
import app.engine.search.domain.Score;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Ebrahim with ❤️ on 30 September 2019.
 */


public class IndexProcessor {

    private Map<String, Score> indexMap = new ConcurrentHashMap<>();

    public void addWord(String fileName, String word) {
        String text = lowercaseText(word);
        Score score = indexMap.get(text);

        if (score == null) {
            score = new Score();
            score.getCategoryIndices().add(new CategoryIndex(fileName));
        } else {
            score.setIncident(score.getIncident() + 1);
            Optional<CategoryIndex> documentIndexInMemory =
                    score.getCategoryIndices().stream()
                            .filter(categoryIndex -> categoryIndex.getCategoryName().equals(fileName))
                            .findFirst();
            if (!documentIndexInMemory.isPresent()) {
                score.getCategoryIndices().add(new CategoryIndex(fileName));
            }
            documentIndexInMemory.ifPresent(CategoryIndex::increase);
        }
        indexMap.put(text, score);
    }


    private String lowercaseText(String text) {
        return text.toLowerCase();
    }

    public Optional<Score> findIndexByText(String text) {
        return Optional.ofNullable(indexMap.get(lowercaseText(text)));
    }


}
