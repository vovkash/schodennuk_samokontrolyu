package schodennuk.web;


import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherDaoIml implements TeacherDao
{
    public TeacherDaoIml() {}

    @Override
    public boolean addTeacher(Teacher teacher)
    {
        try
        {
            DB.Connect();
            DB.UpdateQuery("INSERT INTO teachers (`nickname`, `password`, `name`, `lastname`,`section`) VALUES (?, ?, ?, ?, ?)",
                    teacher.getNickname(), teacher.getPassword(), teacher.getName(), teacher.getLastname(), teacher.getSection());
            DB.Close();
            return true;

        } catch (SQLException exc) {
            return false;
            //error
        }
    }

    @Override
    public Teacher getTeacher(int id)
    {
        try
        {
            DB.Connect();
            DB.Query("SELECT * FROM teachers WHERE id = ?", Integer.toString(id));
            if (DB.isNullResult())
                return null;
            ResultSet rs = DB.getResultSet();

            if(rs.next())
            {
                Teacher t = new Teacher(rs.getString("nickname"));
                t.setID(rs.getInt("id"));
                t.setNickname(rs.getString("nickname"));
                t.setName(rs.getString("name"));
                t.setLastname(rs.getString("lastname"));
                t.setSection(rs.getString("section"));
                t.setPassword(rs.getString("password"));
                return t;
            }

            return null;
        }
        catch (SQLException exc) {
            return null;

        }
    }

    @Override
    public boolean findTeacher(String nickname)
    {
        try {
            DB.Connect();
            DB.Query("SELECT * FROM teachers WHERE nickname = ?", nickname);
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
    public boolean deleteTeacher(int id)
    {
        try
        {
            DB.Connect();
            DB.UpdateQuery("DELETE FROM teachers WHERE id = ?", Integer.toString(id));
            DB.Close();
            return true;
        }
        catch (SQLException exc)
        {
            return false;
        }

    }

    @Override
    public Teacher getTeacher(String nickname)
    {
        try
        {
            DB.Connect();
            DB.Query("SELECT * FROM teachers WHERE nickname = ?", nickname);
            if (DB.isNullResult())
                return null;

            Teacher t = new Teacher(nickname);
            ResultSet rs = DB.getResultSet();
            if(rs.next())
            {
                t.setID(rs.getInt("id"));
                t.setNickname(rs.getString("nickname"));
                t.setName(rs.getString("name"));
                t.setLastname(rs.getString("lastname"));
                t.setSection(rs.getString("section"));
                t.setPassword(rs.getString("password"));
                return t;
            }


            return null;
        }
        catch (SQLException exc) {
            return null;

        }


    }
}
