package firesquad.com.testingimagethings;

import java.util.Random;

/**
 * Created by Noman on 2/20/2016.
 */
public class Course {

    private String courseNumber;
    private String courseName;
    int progress = new Random().nextInt(100);

    public Course(String name, String num) {
        courseName = name;
        courseNumber = num;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public int getProgress() {
        return progress;
    }

    public String getCourseName() {
        return courseName;
    }
}
