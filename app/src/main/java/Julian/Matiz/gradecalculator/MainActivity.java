package Julian.Matiz.gradecalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
// Declare references

    EditText userInput1,userInput2;
    TextView recordsTextView;
    TextView gpaTextView;
    MyDBHandler dbHandler;
    DecimalFormat numberFormat = new DecimalFormat("#.00");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInput1 = (EditText) findViewById(R.id.user_Input1);
        userInput2 = (EditText) findViewById(R.id.user_Input2);
        recordsTextView = (TextView) findViewById(R.id.records_TextView);
        gpaTextView = (TextView) findViewById(R.id.gpa_TextView);
        dbHandler = new MyDBHandler(this);
        printDatabase();
    }

    //Print the database
    public void printDatabase(){
        String dbString = dbHandler.databaseToString();
        recordsTextView.setText(dbString);
        userInput1.setText("");
        userInput2.setText("");
    }

    //add your elements onclick methods.
    //Add a course to the database
    public void addButtonClicked(View view){
        // dbHandler.add needs an object parameter.

        double grade_input;

        if(userInput2.getText().toString().equals("A"))
        {
            grade_input = 4.00;
        }
        else if(userInput2.getText().toString().equals("A-"))
        {
            grade_input = 3.66;
        }
        else if(userInput2.getText().toString().equals("B+"))
        {
            grade_input = 3.33;
        }
        else if(userInput2.getText().toString().equals("B"))
        {
            grade_input = 3.00;
        }
        else if(userInput2.getText().toString().equals("B-"))
        {
            grade_input = 2.66;
        }
        else if(userInput2.getText().toString().equals("C+"))
        {
            grade_input = 2.33;
        }
        else if(userInput2.getText().toString().equals("C"))
        {
            grade_input = 2.00;
        }
        else if(userInput2.getText().toString().equals("C-"))
        {
            grade_input = 1.66;
        }
        else if(userInput2.getText().toString().equals("D+"))
        {
            grade_input = 1.33;
        }
        else if(userInput2.getText().toString().equals("D"))
        {
            grade_input = 1.00;
        }
        else if(userInput2.getText().toString().equals("D-"))
        {
            grade_input = 0.66;
        }
        else if(userInput2.getText().toString().equals("F"))
        {
            grade_input = 0.00;
        }
        else
        {
            grade_input = Double.parseDouble(userInput2.getText().toString());
        }



        Courses course =
                new Courses(userInput1.getText().toString(),grade_input);


        dbHandler.addCourse(course);
        printDatabase();
    }

    //Delete items
    public void deleteButtonClicked(View view){
        // dbHandler delete needs string to find in the db
        String inputText = userInput1.getText().toString();
        dbHandler.deleteCourse(inputText);
        printDatabase();
    }

    public void gpaButtonClicked(View view){
        // calculates gpa
        double gpa = dbHandler.get_gpa();


        if (gpa != 0) {
            gpaTextView.setText("Your GPA is " + numberFormat.format(gpa));
        }
        else {
            gpaTextView.setText("Please enter grades to Calculate");
        }


    }
    public void clearButtonClicked(View view){
        // clears table

        dbHandler.clearCourses();
        printDatabase();



    }



    public void switch_activity(View view){
        startActivity(new Intent(MainActivity.this, act1.class));
    }
}
