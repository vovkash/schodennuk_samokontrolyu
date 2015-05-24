package schodennuk.web;

import java.sql.ResultSet;


public class Student extends User
{

    private int teacherid;
    private int visits;
    private String group;
    private String department;


    public Student(String nickname)
    {
        super(nickname);
    }

    public boolean getDataFromDB()
    {
        try{
            DB.Connect();
            DB.Query("SELECT * FROM students WHERE nickname = ? ", this.getNickname());
            if(DB.isNullResult())
                return false;

            ResultSet rs = DB.getResultSet();
            if(rs.next())
            {
                this.setID(rs.getInt("id"));
                this.setName(rs.getString("name"));
                this.setLastname(rs.getString("lastname"));
                this.setDepartment(rs.getString("department"));
                this.setGroup(rs.getString("group"));
                this.setSection(rs.getString("section"));
                return true;
            }

            return false;



        }
        catch (Exception exc)
        {
            return false;
        }
    }




    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    public int getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }
}
