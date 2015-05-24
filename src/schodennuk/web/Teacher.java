package schodennuk.web;

import java.sql.ResultSet;


public class Teacher extends User {


    public Teacher(String nickname)
    {
        super(nickname);
    }

    public boolean getDataFromDB()
    {
        try{
            DB.Connect();
            DB.Query("SELECT * FROM teachers WHERE nickname = ? ", getNickname());
            if(DB.isNullResult())
                return false;

            ResultSet rs = DB.getResultSet();
            if(rs.next())
            {
                this.setID(rs.getInt("id"));
                this.setName(rs.getString("name"));
                this.setLastname(rs.getString("lastname"));;
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




}
