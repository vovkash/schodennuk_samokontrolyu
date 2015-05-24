package schodennuk.web;


import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDaoIml implements StudentDao {

    public StudentDaoIml() {}

    @Override
    public boolean addStudent(Student student)
    {
        try
        {
            DB.Connect();
            DB.UpdateQuery("INSERT INTO students (`nickname`, `password`, `name`, `lastname`, `group`, `department`, `teacher_id`,`section`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)" ,
                    student.getNickname(), student.getPassword(), student.getName(), student.getLastname(), student.getGroup(), student.getDepartment(), Integer.toString(student.getTeacherid()), student.getSection());
            DB.Close();
            return true;

        } catch (SQLException exc) {
            return false;
            //error
        }
    }

    @Override
    public Student getStudent(int id)
    {
        try
        {
            DB.Connect();
            DB.Query("SELECT * FROM students WHERE id = ?", Integer.toString(id));
            if (DB.isNullResult())
                return null;

            ResultSet rs = DB.getResultSet();
            if(rs.next())
            {
                Student s = new Student(DB.getResultSet().getString("nickname"));
                s.setID(rs.getInt("id"));
                s.setNickname(rs.getString("nickname"));
                s.setName(rs.getString("name"));
                s.setLastname(rs.getString("lastname"));
                s.setDepartment(rs.getString("department"));
                s.setGroup(rs.getString("group"));
                s.setSection(rs.getString("section"));
                s.setTeacherid(rs.getInt("teacher_id"));
                s.setVisits(rs.getInt("visitings"));
                s.setPassword(rs.getString("password"));
                return s;
            }

            return null;
        }
        catch (SQLException exc) {
            return null;

        }
    }

    @Override
    public Student getStudent(String nickname)
    {
        try
        {
            DB.Connect();
            DB.Query("SELECT * FROM students WHERE nickname = ?", nickname);
            if (DB.isNullResult())
                return null;

            Student s = new Student(nickname);
            ResultSet rs = DB.getResultSet();
            if(rs.next())
            {
                s.setID(rs.getInt("id"));
                s.setNickname(rs.getString("nickname"));
                s.setName(rs.getString("name"));
                s.setLastname(rs.getString("lastname"));
                s.setDepartment(rs.getString("department"));
                s.setGroup(rs.getString("group"));
                s.setSection(rs.getString("section"));
                s.setTeacherid(rs.getInt("teacher_id"));
                s.setVisits(rs.getInt("visitings"));
                s.setPassword(rs.getString("password"));
                return s;
            }


            return null;
        }
        catch (SQLException exc) {
            return null;

        }


    }

    @Override
    public boolean findStudent(String nickname)
    {
        try {
            DB.Connect();
            DB.Query("SELECT * FROM students WHERE nickname = ?", nickname);
            if (DB.isNullResult())
                return false;
            return true;
        }
        catch (SQLException exc)
        {
            //error
            return false;
        }

    }

    @Override
    public void updateStudent(Student student)
    {
        try
        {
            DB.Connect();
            DB.UpdateQuery("UPDATE students SET name = ?, lastname = ?, `group` = ?, `section` = ?, department = ?, `teacher_id` = ?, `password`= ?, " +
                    "`visitings` = ? WHERE id = ?", student.getName(), student.getLastname(), student.getGroup(), student.getSection(), student.getDepartment(), Integer.toString(student.getTeacherid()), student.getPassword(),Integer.toString(student.getVisits()), Integer.toString(student.getID()));
            DB.Close();
        }
        catch (SQLException exc)
        {
            //error
        }
    }
}
