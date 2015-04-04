package lab.fpm;

public class Teacher extends User
{
    private String Section;
    protected String Workdays;
    private String About;


    public Teacher(String username, String pass,  String name, String lastname, String section, String workdays, String about)
    {
        super(username, pass, name, lastname);
        this.Section = section;
        this. Workdays = workdays;
        this.About = about;
    }
}