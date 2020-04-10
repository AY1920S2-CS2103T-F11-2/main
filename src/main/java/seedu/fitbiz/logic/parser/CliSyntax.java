package seedu.fitbiz.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    public static final String PREFIX_DELIMITTER = "/";

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");

    // ======================= client =====================================
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_REMARK = new Prefix("r/");
    public static final Prefix PREFIX_GENDER = new Prefix("g/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_BIRTHDAY = new Prefix("b/");
    public static final Prefix PREFIX_HEIGHT = new Prefix("h/");
    public static final Prefix PREFIX_SPORT = new Prefix("s/");
    public static final Prefix PREFIX_TARGET_WEIGHT = new Prefix("tw/");
    public static final Prefix PREFIX_CURRENT_WEIGHT = new Prefix("cw/");

    //======================= exercise =====================================
    public static final Prefix PREFIX_REPS = new Prefix("reps/");
    public static final Prefix PREFIX_SETS = new Prefix("sets/");
    public static final Prefix PREFIX_EXERCISE_WEIGHT = new Prefix("ew/");
    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_DAY = new Prefix("day/");
    public static final Prefix PREFIX_START_TIME = new Prefix("st/");
    public static final Prefix PREFIX_END_TIME = new Prefix("et/");

    //======================= schedule =====================================
    public static final Prefix PREFIX_SCHEDULE = new Prefix("sch/");
}
