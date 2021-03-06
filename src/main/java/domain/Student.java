package domain;

import json.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private static final int PASSING_GRADE = 3;

    private final Tuple<String, Integer>[] exams;

    @SafeVarargs
    public Student(String name, String surname, Integer year,
                   Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = exams;
    }

    public boolean checkPassedStatus(int mark) {
        return mark >= PASSING_GRADE;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject student = super.toJsonObject();

        Json[] examsList = new Json[exams.length];

        for (int i = 0; i < exams.length; i++) {
            boolean status = checkPassedStatus(exams[i].value);

            JsonPair coursePair = new JsonPair("course",
                    new JsonString(exams[i].key));
            JsonPair markPair = new JsonPair("mark",
                    new JsonNumber(exams[i].value));
            JsonPair statusPair = new JsonPair("passed",
                    new JsonBoolean(status));
            JsonObject examObject = new JsonObject(coursePair,
                    markPair, statusPair);

            examsList[i] = examObject;
        }

        JsonArray examsArray = new JsonArray(examsList);
        JsonPair examsPair = new JsonPair("exams", examsArray);

        student.add(examsPair);
        return student;
    }

}