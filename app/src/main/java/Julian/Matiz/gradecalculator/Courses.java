package Julian.Matiz.gradecalculator;

public class Courses {
    private String _coursename;
    private double _grade;
    public Courses(String courseName, double grade) {

        this._coursename = courseName;
        this._grade=grade;
    }

    public double get_grade() {
        return _grade;
    }

    public void set_grade(double _grade) {
        this._grade = _grade;
    }

    public String get_coursename() {
        return _coursename;
    }

    public void set_coursename(String _coursename) {
        this._coursename = _coursename;
    }
}
