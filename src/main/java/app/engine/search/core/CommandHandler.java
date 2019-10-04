package app.engine.search.core;

import app.engine.search.CommandTypes;
import app.engine.search.domain.RankData;
import app.engine.search.domain.Score;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Created by Ebrahim with ❤️ on 30 September 2019.
 */


public class CommandHandler {

    private SearchProcessor searchProcessor;
    private RankCalculator rankCalculator;


    public CommandHandler(SearchProcessor searchProcessor, RankCalculator rankCalculator) {
        this.searchProcessor = searchProcessor;
        this.rankCalculator = rankCalculator;
    }

    public void execute() {
        execute(new Scanner(System.in));
    }

    public void execute(Scanner input) {
        System.out.print(CommandTypes.START.getValue());
        while (input.hasNext()) {
            String line = input.nextLine();
            if (line.equals(CommandTypes.QUIT.getValue())) {
                break;
            }
            System.out.println(CommandTypes.SEARCHING.getValue() + line + " ...");
            List<Optional<Score>> searchResults = searchProcessor.doSearch(line);
            printResult(searchResults);
            System.out.print(CommandTypes.START.getValue());
        }
    }


    private void printResult(List<Optional<Score>> searchResults) {
        if (searchResults.size() == 0) {
            System.err.println(CommandTypes.NOT_FOUND.getValue());
        } else {
            getRankResult(searchResults);
        }
    }

    private void getRankResult(List<Optional<Score>> searchResults) {

        List<RankData> rankData = rankCalculator.calculate(searchResults);
        if (rankData.size() == 0) {
            System.err.println(CommandTypes.NOT_FOUND.getValue());
        } else {
            printRankResults(rankData);
        }
    }

    private void printRankResults(List<RankData> rankData) {

        rankData.forEach(fileRank -> {
            System.out.println(fileRank.getFileName()
                    + "("
                    + fileRank.getMatchTexts()
                    + " incident)"
                    + " : "
                    + fileRank.getPercentage()
                    + "%");
        });
    }


}
