package firesquad.com.testingimagethings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by Noman on 2/20/2016.
 */
public class AddClassActivity extends AppCompatActivity {
    public static final int CLASS = 1;  // The request code
    public static String hello = "";

    Spinner coursesDropDown;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addclass);
        ArrayList<String> courses = getIntent().getExtras().getStringArrayList("Courses");

        coursesDropDown= (Spinner) findViewById(R.id.dropdown);
        submit = (Button) findViewById(R.id.submitClass);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
               android.R.layout.simple_spinner_item, courses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        for (String s: courses) {
//            adapter.add(s);
//        }
        coursesDropDown.setAdapter(adapter);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveItem();
            }
        });
    }

    private void saveItem() {
        String className = coursesDropDown.getSelectedItem().toString();
        hello = className;
        Bundle backToAdd = new Bundle();
        backToAdd.putString("CourseName", className);
        Log.d("SelectedName", className);
        Intent backToadd = new Intent(AddClassActivity.this, ClassActivity.class);
        backToadd.putExtras(backToAdd);
        startActivityForResult(backToadd, CLASS);
    }
}
