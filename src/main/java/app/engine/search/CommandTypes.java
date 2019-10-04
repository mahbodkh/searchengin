package app.engine.search;


import app.engine.search.util.General;

/**
 * Created by Ebrahim with ❤️ on 30 September 2019.
 */

public enum CommandTypes {

    NOT_FOUND("no matches found "),
    SEARCHING("Searching "),
    QUIT(":quit"),
    START("search> "),
    UNDEFINED(""),

    ;

    private String value;

    CommandTypes(String value) {
        this.value = value;
    }

    public static CommandTypes get(String value) {
        if (General.isEmpty(value)) {
            return UNDEFINED;
        }

        CommandTypes[] arr$ = values();
        for (CommandTypes val : arr$) {
            if (val.value.equalsIgnoreCase(value)) {
                return val;
            }
        }
        return UNDEFINED;
    }

    public String getValue() {
        return value;
    }
}
