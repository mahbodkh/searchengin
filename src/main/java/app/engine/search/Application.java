package app.engine.search;

import app.engine.search.error.InputException;
import app.engine.search.error.NotFoundCategoryException;
import app.engine.search.service.SearchFactoryService;

import java.util.Scanner;

/**
 * Created by Ebrahim with ❤️ on 30 September 2019.
 */


public class Application implements SearchFactoryService {

    private SearchFactoryService searchService;

    public Application(SearchFactoryService searchService) {
        this.searchService = searchService;
    }


    public static void main(String[] args) {

        try {
            new Application(new SearchFactoryService() {
            }).run(args);
        } catch (InputException e) {
            System.exit(-1); // End of program
        }
    }


    public void run(String[] args) {
        validate(args);
        searchService.getCategoryReader().processCategory(args[0]);
        searchService.getCommandHandler().execute();
    }

    private static void exit(Scanner keyboard) {
        try {
            keyboard.close();
        } catch (Exception e) {
            throw new InputException(keyboard.next(), e);
        } finally {
            System.out.println("Application has been Finished.");
        }
    }

    private static void validate(String[] args) {
        if (args == null || args.length != 1) {
            throw new NotFoundCategoryException();
        }
    }
}
