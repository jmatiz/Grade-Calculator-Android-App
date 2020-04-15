package Julian.Matiz.gradecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class act1 extends AppCompatActivity {
// Declare references

    EditText userInput1,userInput2;
    TextView recordsTextView;
    TextView gpaTextView;
    MyDBHandler dbHandler_act1;
    DecimalFormat numberFormat = new DecimalFormat("#.00");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act1);
        userInput1 = (EditText) findViewById(R.id.user_Input1);
        userInput2 = (EditText) findViewById(R.id.user_Input2);
        recordsTextView = (TextView) findViewById(R.id.records_TextView);
        gpaTextView = (TextView) findViewById(R.id.gpa_TextView);
        dbHandler_act1 = new MyDBHandler(this);
        printDatabase();
    }

    //Print the database
    public void printDatabase(){
        String dbString = dbHandler_act1.databaseToString1();
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


        dbHandler_act1.addAssignment(course);
        printDatabase();
    }

    //Delete items
    public void deleteButtonClicked(View view){
        dbHandler_act1.deleteAssignment();
        printDatabase();
    }

    public void gpaButtonClicked(View view){
        // calculates gpa
       double gpa = dbHandler_act1.get_course_grade();


       if (gpa >= 93)
           gpaTextView.setText("Your Grade in this course is an A (" + numberFormat.format(gpa) + ")");
       else if (gpa >= 90)
           gpaTextView.setText("Your Grade in this course is an A- (" + numberFormat.format(gpa) + ")");
       else if (gpa >= 87)
           gpaTextView.setText("Your Grade in this course is a B+ (" + numberFormat.format(gpa) + ")");
       else if (gpa >= 83)
           gpaTextView.setText("Your Grade in this course is a B (" + numberFormat.format(gpa) + ")");
       else if (gpa >= 80)
           gpaTextView.setText("Your Grade in this course is a B- (" + numberFormat.format(gpa) + ")");
       else if (gpa >= 77)
           gpaTextView.setText("Your Grade in this course is a C+ (" + numberFormat.format(gpa) + ")");
       else if (gpa >= 73)
           gpaTextView.setText("Your Grade in this course is a C (" + numberFormat.format(gpa) + ")");
       else if (gpa >= 70)
           gpaTextView.setText("Your Grade in this course is a C- (" + numberFormat.format(gpa) + ")");
       else if (gpa >= 67)
           gpaTextView.setText("Your Grade in this course is a D+ (" + numberFormat.format(gpa) + ")");
       else if (gpa >= 63)
           gpaTextView.setText("Your Grade in this course is a D (" + numberFormat.format(gpa) + ")");
       else if (gpa >= 60)
           gpaTextView.setText("Your Grade in this course is a D- (" + numberFormat.format(gpa) + ")");
       else if (gpa < 60)
           gpaTextView.setText("Your Grade in this course is an F (" + numberFormat.format(gpa) + ")");


    }

    public void switch_activity(View view){
        startActivity(new Intent(act1.this, MainActivity.class));
    }
}
