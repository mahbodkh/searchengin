package app.engine.search.core;

import app.engine.search.domain.Score;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Ebrahim with ❤️ on 30 September 2019.
 */


public class SearchProcessor {
    private IndexProcessor processor;

    public SearchProcessor(IndexProcessor processor) {
        this.processor = processor;
    }

    public List<Optional<Score>> doSearch(String input) {
        List<Optional<Score>> collectedTexts = Arrays.stream(input.split(" "))
                .map(this::searchInIndexer).collect(Collectors.toList());
        return collectedTexts;
    }

    private Optional<Score> searchInIndexer(String word) {
        Optional<Score> indexByText = processor.findIndexByText(word);
        return indexByText;
    }
}
