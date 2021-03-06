package app;

import domain.*;
import json.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JSONApp {
    public static void main(String[] args) {
        final int FIRST_MARK = 3;
        final int SECOND_MARK = 4;

        Json jYear = new JsonNumber(2);
        print(jYear); // 2

        Json jMarks = new JsonArray(new JsonNumber(FIRST_MARK),
                new JsonNumber(SECOND_MARK));
        print(jMarks); // [3, 4]

        JsonPair name = new JsonPair("name", new JsonString("Andrii"));
        JsonPair surname = new JsonPair("surname", new JsonString("Rodionov"));
        JsonPair marks = new JsonPair("marks", jMarks);
        JsonPair year = new JsonPair("year", jYear);
        JsonObject jsonObj = new JsonObject(name, surname, year, marks);
        print(jsonObj); // {'name': 'Andrii', 'surname': 'Rodionov',
        // 'year': 2, 'marks': [3, 4]}

        print(jsonObj.projection("surname", "age", "year", "marks"));
        // {'surname': 'Rodionov', 'year': 2, 'marks': [3, 4]}

        BasicStudent basicStudent = new BasicStudent("Andrii",
                "Rodionov", 2);
        print(basicStudent.toJsonObject()); // {'name': 'Andrii', 'surname':
        // 'Rodionov', 'year': 2}

    }

    private static void print(Json json) {
        System.out.println(json.toJson());
    }

    public static JsonObject sessionResult() {
        final int ENGLISH_MARK = 5;
        final int OOP_MARK = 3;
        Json jYear = new JsonNumber(2);
        JsonPair year = new JsonPair("year", jYear);

        JsonPair name = new JsonPair("name", new JsonString("Andrii"));
        JsonPair surname = new JsonPair("surname", new JsonString("Rodionov"));

        JsonObject oopObject = new JsonObject(new JsonPair("course",
                new JsonString("OOP")), new JsonPair("mark",
                new JsonNumber(OOP_MARK)),
                new JsonPair("passed", new JsonBoolean(true)));
        JsonObject englishObject = new JsonObject(new JsonPair("course",
                new JsonString("English")), new JsonPair("mark",
                new JsonNumber(ENGLISH_MARK)), new JsonPair("passed",
                new JsonBoolean(true)));
        JsonObject mathObject = new JsonObject(new JsonPair("course",
                new JsonString("Math")), new JsonPair("mark",
                new JsonNumber(2)), new JsonPair("passed",
                new JsonBoolean(false)));

        JsonArray subjects = new JsonArray(oopObject, englishObject,
                mathObject);
        JsonPair exams = new JsonPair("exams", subjects);

        return new JsonObject(name, surname, year, exams);
    }
}
