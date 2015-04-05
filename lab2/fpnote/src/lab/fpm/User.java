package lab.fpm;

public class User
{
    protected int ID;
    protected String Username;
    protected String Password;
    protected String FirstName;
    protected String LastName;



    public User( String username, String pass, String fname, String lname)
    {
        this.ID = 0;
        this.Username = username;
        this.Password = pass;
        this.FirstName = fname;
        this.LastName = lname;

    }

    public User(int id, String username, String pass, String fname, String lname)
    {
        this.ID = id;
        this.Username = username;
        this.Password = pass;
        this.FirstName = fname;
        this.LastName = lname;
    }
}