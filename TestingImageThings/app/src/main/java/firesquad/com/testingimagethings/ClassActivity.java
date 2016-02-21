package firesquad.com.testingimagethings;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noman on 2/20/2016.
 */
public class ClassActivity extends AppCompatActivity {
    static final int CLASS = 1;  // The request code

    private ArrayList<Course> theCourses = new ArrayList<>();
    private ArrayList<Course> someCourses = new ArrayList<>();
    private ParseQuery<ParseObject> query;
    private RecyclerView rv;
    private CourseAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        String name = AddClassActivity.hello;
//        Intent i = getIntent();
//        if (i != null) {
//            Bundle b = i.getExtras();
//            for (Course c: theCourses) {
//                if (c.getCourseName().equals(b.getString("CourseName"))) {
//                    someCourses.add(new Course(b.getString("CourseName"), c.getCourseNumber()));
//                }
//            }
//        }
        for (Course c: theCourses) {
            if (c.getCourseName().equals(name)) {
                someCourses.add(new Course(name, c.getCourseNumber()));
            }
        }
        initializeData();
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addClass);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAdd();
            }
        });
    }

    private void initializeData() {

        query = ParseQuery.getQuery("Syllabus");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject object : objects) {
                        String courseName = object.getString("courseName");
                        Log.e("CourseName", courseName);
                        String courseNumber = object.getString("courseNum");
                        Log.e("CourseNum", courseNumber);
                        if (courseNumber.contains("CS")) {
                            addToView(courseName, courseNumber);
                        }
                        addToList(courseName, courseNumber);

                    }

                    courseAdapter = new CourseAdapter(someCourses);
                    rv.setAdapter(courseAdapter);
                } else {
                    Log.e("Hello", e.getMessage());
                }
            }
        });
    }

    private void addToList(String name, String number) {
        theCourses.add(new Course(name, number));
    }
    private void addToView(String name, String number) {
        someCourses.add(new Course(name, number));}

    private void moveToAdd() {
        ArrayList<String> courseNames = new ArrayList<>();
        Bundle addBundle = new Bundle();
        for (int i = 0; i < theCourses.size(); i++) {
            courseNames.add(theCourses.get(i).getCourseName());
        }
        addBundle.putStringArrayList("Courses", courseNames);

        Intent add = new Intent(ClassActivity.this, AddClassActivity.class );
        add.putExtras(addBundle);
        startActivity(add);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CLASS) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Bundle b = data.getExtras();
                Log.e("Bundle Course", b.getString("CourseName"));
                for (Course c: theCourses) {
                    if (c.getCourseName().equals(b.getString("CourseName"))) {
                        someCourses.add(new Course(b.getString("CourseName"), c.getCourseNumber()));
                    }
                }
//                courseAdapter = new CourseAdapter(someCourses);
                courseAdapter.notifyDataSetChanged();
//                rv.setAdapter(courseAdapter);
            }
        }
    }
}
