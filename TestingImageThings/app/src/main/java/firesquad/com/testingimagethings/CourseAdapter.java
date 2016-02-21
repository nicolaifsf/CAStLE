package firesquad.com.testingimagethings;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Noman on 2/20/2016.
 */
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private ArrayList<Course> courses;

    public CourseAdapter(ArrayList<Course> someCourses) {
        courses = someCourses;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.class_item_list, parent, false);

        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        Course c = courses.get(position);
        holder.courseName.setText(c.getCourseName());
        holder.courseNum.setText(c.getCourseNumber());
        holder.bar.setProgress(c.getProgress());
    }

    @Override
    public int getItemCount()
    {
        return courses.size();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {

        protected TextView courseNum;
        protected TextView courseName;
        protected ProgressBar bar;

        public CourseViewHolder(View itemView) {
            super(itemView);
            courseNum = (TextView) itemView.findViewById(R.id.courseNum);
            courseName = (TextView) itemView.findViewById(R.id.courseName);
            bar = (ProgressBar)itemView.findViewById(R.id.progress);
        }
    }
}
