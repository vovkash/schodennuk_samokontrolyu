package lab.fpm;

public class Student extends User
{
    private int Course;
    private String Department;
    private String Group;
    private String MedGroup;
    private VisitedWeekDay VisitationDays;
    private String Section;
    private int NumberofAntendance;
    private String Standart;
    private WeeklyRecord[] theWeeklyRecord;
    public DailyRecordWLesson theDailyRecordWithLesson;
    public DailyRecordWoutLesson theDailyRecordWithoutLesson;

    public Student(String username, String pass, String fname, String lname, int course, String department, String group)
    {
        super(username, pass, fname, lname);
        this.Course = course;
        this.Department= department;
        this.Group = group;
        this.NumberofAntendance = 0;
        VisitationDays = new VisitedWeekDay();
    }

    public Student(String username, String pass, String fname, String lname, int course, String department, String group, VisitedWeekDay vwd)
    {
        super(username, pass, fname, lname);
        this.Course = course;
        this.Department= department;
        this.Group = group;
        this.NumberofAntendance = 0;
        VisitationDays = vwd;
    }

    public void setVisitationDays(VisitedWeekDay vwd)
    {
        VisitationDays = vwd;
    }


    public String Info()
    {
        return "Name: "+this.FirstName + " "+this.LastName+"\n Curse: "+this.Course + "\n Department/Group: "+this.Department+" "+this.Group +"\n" + this.VisitationDays.ToString();

    }


    public void DrowGraphic(int start, int end)
    {

    }

    public void ShowTableofDailyRecord(int start,  int end)
    {

    }

    public void CompareStudents(Student Student1, Student Student2)
    {

    }


    public void GetStudentsByDayVisit(int Day)
    {

    }


    public void PasstheStandart(String Standart)
    {

    }



    public void Delete()
    {

    }
}
