package domain;

import json.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {

    protected String name;
    protected String surname;
    protected Integer year;
    Tuple<String, Integer>[] exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        // ToDo
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.exams = exams;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonPair namePair = new JsonPair("name", new JsonString(name));
        JsonPair surnamePair = new JsonPair("surname", new JsonString(surname));
        JsonPair yearPair = new JsonPair("year", new JsonNumber(year));

        Json[] examsList = new Json[exams.length];

        for (int i = 0; i < exams.length; i++) {
            boolean status = exams[i].value >= 3;

            JsonPair coursePair = new JsonPair("course", new JsonString(exams[i].key));
            JsonPair markPair = new JsonPair("mark", new JsonNumber(exams[i].value));
            JsonPair statusPair = new JsonPair("passed", new JsonBoolean(status));
            JsonObject examObject = new JsonObject(coursePair, markPair, statusPair);

            examsList[i] = examObject;
        }

        JsonArray examsArray = new JsonArray(examsList);
        JsonPair examsPair = new JsonPair("exams", examsArray);

        return new JsonObject(namePair, surnamePair, yearPair, examsPair);
    }

}