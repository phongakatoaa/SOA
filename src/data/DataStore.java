package data;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import model.Student;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class DataStore {
    private static final String resourceFile = "data.json";
    private Gson gson;
    private HashMap<String, Student> store;

    public DataStore() {
        gson = new Gson();
        store = new HashMap<>();
        try {
            FileReader fileReader = new FileReader(resourceFile);
            JsonArray studentData = JsonParser.parseReader(fileReader).getAsJsonArray();
            studentData.forEach(item -> {
                Student student = new Student(item.getAsJsonObject());
                store.put(student.getStudentId(), student);
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getStudentAsJsonString(String studentId) {
        Student result = store.get(studentId);
        if (result == null) {
            return null;
        }
        return gson.toJson(result);
    }

    public void listStudents() {
        store.values().forEach(Student::printInfo);
    }
}
