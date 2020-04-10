package seedu.fitbiz.model.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.fitbiz.model.FitBiz;
import seedu.fitbiz.model.ReadOnlyFitBiz;
import seedu.fitbiz.model.client.Address;
import seedu.fitbiz.model.client.Birthday;
import seedu.fitbiz.model.client.Client;
import seedu.fitbiz.model.client.CurrentWeight;
import seedu.fitbiz.model.client.Email;
import seedu.fitbiz.model.client.Gender;
import seedu.fitbiz.model.client.Height;
import seedu.fitbiz.model.client.Name;
import seedu.fitbiz.model.client.PersonalBest;
import seedu.fitbiz.model.client.Phone;
import seedu.fitbiz.model.client.Remark;
import seedu.fitbiz.model.client.Sport;
import seedu.fitbiz.model.client.TargetWeight;
import seedu.fitbiz.model.exercise.Exercise;
import seedu.fitbiz.model.exercise.ExerciseDate;
import seedu.fitbiz.model.exercise.ExerciseName;
import seedu.fitbiz.model.exercise.ExerciseReps;
import seedu.fitbiz.model.exercise.ExerciseSets;
import seedu.fitbiz.model.exercise.ExerciseWeight;
import seedu.fitbiz.model.exercise.UniqueExerciseList;
import seedu.fitbiz.model.schedule.Day;
import seedu.fitbiz.model.schedule.EndTime;
import seedu.fitbiz.model.schedule.Schedule;
import seedu.fitbiz.model.schedule.ScheduleList;
import seedu.fitbiz.model.schedule.StartTime;
import seedu.fitbiz.model.tag.Tag;

/**
 * Contains utility methods for populating {@code FitBiz} with sample data.
 */
public class SampleDataUtil {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static Client[] getSampleClients() {
        ScheduleList alexScheduleList = new ScheduleList();
        alexScheduleList.add(new Schedule(new Day("mon"), new StartTime("1100"), new EndTime("1230")));
        alexScheduleList.add(new Schedule(new Day("tue"), new StartTime("1700"), new EndTime("1900")));
        UniqueExerciseList alexExerciseList = new UniqueExerciseList();
        alexExerciseList.addToSorted(new Exercise(new ExerciseName("Bench Press"), new ExerciseReps("8"),
                new ExerciseSets("3"), new ExerciseWeight("82"),
                new ExerciseDate(LocalDate.now().minusDays(1).format(DATE_TIME_FORMATTER))));
        alexExerciseList.addToSorted(new Exercise(new ExerciseName("Bench Press"), new ExerciseReps("6"),
                new ExerciseSets("2"), new ExerciseWeight("85"),
                new ExerciseDate(LocalDate.now().minusDays(5).format(DATE_TIME_FORMATTER))));
        alexExerciseList.addToSorted(new Exercise(new ExerciseName("Bench Press"), new ExerciseReps("7"),
                new ExerciseSets("3"), new ExerciseWeight("80"),
                new ExerciseDate(LocalDate.now().minusDays(12).format(DATE_TIME_FORMATTER))));
        alexExerciseList.addToSorted(new Exercise(new ExerciseName("Barbell Squat"), new ExerciseReps("5"),
                new ExerciseSets("4"), new ExerciseWeight("125"),
                new ExerciseDate(LocalDate.now().minusDays(1).format(DATE_TIME_FORMATTER))));
        alexExerciseList.addToSorted(new Exercise(new ExerciseName("Barbell Squat"), new ExerciseReps("3"),
                new ExerciseSets("4"), new ExerciseWeight("145"),
                new ExerciseDate(LocalDate.now().minusDays(5).format(DATE_TIME_FORMATTER))));
        alexExerciseList.addToSorted(new Exercise(new ExerciseName("Barbell Squat"), new ExerciseReps("2"),
                new ExerciseSets("3"), new ExerciseWeight("150"),
                new ExerciseDate(LocalDate.now().minusDays(12).format(DATE_TIME_FORMATTER))));
        alexExerciseList.addToSorted(new Exercise(new ExerciseName("Sumo Deadlift"), new ExerciseReps("5"),
                new ExerciseSets("2"), new ExerciseWeight("165"),
                new ExerciseDate(LocalDate.now().minusDays(1).format(DATE_TIME_FORMATTER))));
        alexExerciseList.addToSorted(new Exercise(new ExerciseName("Sumo Deadlift"), new ExerciseReps("3"),
                new ExerciseSets("2"), new ExerciseWeight("185"),
                new ExerciseDate(LocalDate.now().minusDays(5).format(DATE_TIME_FORMATTER))));
        alexExerciseList.addToSorted(new Exercise(new ExerciseName("Sumo Deadlift"), new ExerciseReps("4"),
                new ExerciseSets("3"), new ExerciseWeight("180"),
                new ExerciseDate(LocalDate.now().minusDays(12).format(DATE_TIME_FORMATTER))));

        ScheduleList berniceScheduleList = new ScheduleList();
        berniceScheduleList.add(new Schedule(new Day("mon"), new StartTime("1100"), new EndTime("1230")));
        berniceScheduleList.add(new Schedule(new Day("fri"), new StartTime("1700"), new EndTime("1900")));

        ScheduleList irfanScheduleList = new ScheduleList();
        irfanScheduleList.add(new Schedule(new Day("sat"), new StartTime("1200"), new EndTime("1400")));
        irfanScheduleList.add(new Schedule(new Day("tue"), new StartTime("1200"), new EndTime("1400")));

        ScheduleList royScheduleList = new ScheduleList();
        royScheduleList.add(new Schedule(new Day("sat"), new StartTime("0900"), new EndTime("1100")));

        ScheduleList amyScheduleList = new ScheduleList();
        amyScheduleList.add(new Schedule(new Day("thu"), new StartTime("1945"), new EndTime("2115")));
        amyScheduleList.add(new Schedule(new Day("sat"), new StartTime("1945"), new EndTime("2115")));

        return new Client[] {
            new Client(new Name("Alex Yeoh"), new Gender("Male"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"), new Address("Blk 69 Geylang Street 29, #06-40"),
                    getTagSet("Paleo"), new Birthday("25-12-1997"), new CurrentWeight("70"),
                    new TargetWeight("75"), new Height("170"), new Remark("Diet may not be good"),
                    getSportSet("Sumo Wrestling"), alexExerciseList, new PersonalBest(), alexScheduleList),
            new Client(new Name("Bernice Yu"), new Gender("Female"), new Phone("99272758"),
                    new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("Normal"), new Birthday("29-02-2000"), new CurrentWeight("55.8"),
                    new TargetWeight("50"), new Height("160"), new Remark(""), getSportSet("Hockey"),
                    new UniqueExerciseList(), new PersonalBest(), berniceScheduleList),
            new Client(new Name("Irfan Ibrahim"), new Gender("Male"), new Phone("92492021"),
                    new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("Halal"), new Birthday("12-08-1975"), new CurrentWeight("70"),
                    new TargetWeight("75"), new Height("180"), new Remark("Requires protein"),
                    getSportSet("Soccer"), new UniqueExerciseList(), new PersonalBest(), irfanScheduleList),
            new Client(new Name("Roy Balakrishnan"), new Gender("Male"), new Phone("92624417"),
                    new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("Vegan"), new Birthday("05-12-1990"), new CurrentWeight("67.4"),
                    new TargetWeight("75"), new Height("174"), new Remark("History of past injuries"),
                    getSportSet("Belly Dance"), new UniqueExerciseList(), new PersonalBest(), royScheduleList),
            new Client(new Name("Amy Ang"), new Gender("Female"), new Phone("97227128"),
                    new Email("amyyyy@example.com"), new Address("Blk 90 Sunset Way, #20-18"),
                    getTagSet("Normal"), new Birthday("12-05-1970"), new CurrentWeight("61.2"),
                    new TargetWeight("60"), new Height("165"), new Remark("History of past injuries"),
                    getSportSet("Fencing"), new UniqueExerciseList(), new PersonalBest(), amyScheduleList) };
    }

    public static ReadOnlyFitBiz getSampleFitBiz() {
        FitBiz sampleAb = new FitBiz();
        for (Client sampleClient : getSampleClients()) {
            sampleAb.addClient(sampleClient);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings).map(Tag::new).collect(Collectors.toSet());
    }

    /**
     * Returns a sport set containing the list of strings given.
     */
    public static Set<Sport> getSportSet(String... strings) {
        return Arrays.stream(strings)
                .map(Sport::new)
                .collect(Collectors.toSet());
    }

}
