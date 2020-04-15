package Julian.Matiz.gradecalculator;
// This class handles all the database activities
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class MyDBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "courseDB1.db";
    public static final String TABLE_COURSES = "courses";
    public static final String TABLE_ASSIGNMENTS = "assignments";
    //public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COURSENAME = "coursename";
    public static final String COLUMN_ASSIGNMENT = "assignmentname";
    public static final String COLUMN_GRADE = "grade";
    public static final String COLUMN_ASSIGNMENT_GRADE = "assignmentgrade";


    //We need to pass database information along to superclass
    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_COURSES + "(" +
                                COLUMN_COURSENAME + " TEXT," + COLUMN_GRADE + " DOUBLE );";
        db.execSQL(query);

        String query1 = "CREATE TABLE " + TABLE_ASSIGNMENTS + "(" +
                COLUMN_ASSIGNMENT + " TEXT," + COLUMN_ASSIGNMENT_GRADE + " DOUBLE );";
        db.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSIGNMENTS);
        onCreate(db);
    }

    //Add a new row to the database
    public void addCourse(Courses course){
        ContentValues values = new ContentValues();
        values.put(COLUMN_COURSENAME, course.get_coursename());
        values.put(COLUMN_GRADE, course.get_grade());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_COURSES, null, values);
        db.close();
    }

    //Add a new row to the database
    public void addAssignment(Courses course){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ASSIGNMENT, course.get_coursename());
        values.put(COLUMN_ASSIGNMENT_GRADE, course.get_grade());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_ASSIGNMENTS, null, values);
        db.close();
    }

    //Delete a course from the database
    public void deleteCourse(String courseName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_COURSES + " WHERE " + COLUMN_COURSENAME + "=\'" + courseName + "\';");
    }

    //Delete assignments from the database
    public void deleteAssignment(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM " + TABLE_ASSIGNMENTS + ";";
        db.execSQL(query);
    }

    //Delete a course from the database
    public void clearCourses(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM " + TABLE_COURSES + ";";
        db.execSQL(query);
    }


    // this is going in record_TextView in the Main activity.
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_COURSES + " WHERE 1";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();

        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {
            if (recordSet.getString(recordSet.getColumnIndex("coursename")) != null) {
                dbString += recordSet.getString(recordSet.getColumnIndex("coursename"));
                //In the above, recordSet.getColumnIndex("coursename") can be replaced by 0, which is column index
                dbString += ",   ";
                dbString += recordSet.getString(recordSet.getColumnIndex("grade"));
                //In the above, recordSet.getColumnIndex("grade") can be replaced by 0, which is column index
                dbString += "\n";
            }
            recordSet.moveToNext();
        }
        db.close();
        return dbString;
    }

    public String databaseToString1(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ASSIGNMENTS + " WHERE 1";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();


        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {
            if (recordSet.getString(recordSet.getColumnIndex("assignmentname")) != null) {

                dbString += recordSet.getString(recordSet.getColumnIndex("assignmentname"));
                //In the above, recordSet.getColumnIndex("coursename") can be replaced by 0, which is column index
                dbString += "%,   ";
                dbString += recordSet.getString(recordSet.getColumnIndex("assignmentgrade"));
                //In the above, recordSet.getColumnIndex("grade") can be replaced by 0, which is column index
                dbString += "\n";
            }
            recordSet.moveToNext();
        }
        db.close();
        return dbString;
    }

    public double get_gpa() {
        double gpa = 0;
        double course_amount = 0;
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_COURSES + " WHERE 1";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();

        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {
            if (recordSet.getString(recordSet.getColumnIndex("coursename")) != null) {
                gpa += recordSet.getDouble(recordSet.getColumnIndex("grade"));
                //In the above, recordSet.getColumnIndex("grade") can be replaced by 0, which is column index
                course_amount++;
            }
            recordSet.moveToNext();
        }
        db.close();

        if (course_amount != 0) {
            gpa = gpa / course_amount;
        }

        return gpa;
    }

    public double get_course_grade() {
        double course_grade = 0;
        double course_calc = 0;
        double course_weight = 0;
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ASSIGNMENTS + " WHERE 1";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();

        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {
            if (recordSet.getString(recordSet.getColumnIndex("assignmentname")) != null) {
                course_calc = recordSet.getDouble(recordSet.getColumnIndex("assignmentgrade"));
                //In the above, recordSet.getColumnIndex("grade") can be replaced by 0, which is column index
                course_weight = Double.parseDouble(recordSet.getString(recordSet.getColumnIndex("assignmentname")));

                course_grade += (course_calc/100) * course_weight;

            }
            recordSet.moveToNext();
        }
        db.close();



        return course_grade;



    }

}
