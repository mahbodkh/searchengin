package app.engine.search.service;

import app.engine.search.core.*;

/**
 * Created by Ebrahim with ❤️ on 30 September 2019.
 */


public interface SearchFactoryService {

    IndexProcessor indexProcessor = new IndexProcessor();
    TextProcessor textProcessor = new TextProcessor(indexProcessor);
    CategoryReader categoryReader = new CategoryReader(textProcessor);
    SearchProcessor searchProcessor = new SearchProcessor(indexProcessor);
    RankCalculator rankCalculator = new RankCalculator();
    CommandHandler commandHandler = new CommandHandler(searchProcessor, rankCalculator);

    default CategoryReader getCategoryReader() {
        return categoryReader;
    }

    default CommandHandler getCommandHandler() {
        return commandHandler;
    }


}
