package schodennuk.web;

import java.sql.*;
import java.security.*;
import java.math.BigInteger;


public class DB
{
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL    = "jdbc:mysql://localhost/NoteBook";

    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static Connection connection = null;
    private static ResultSet queryresult = null;


    public synchronized static void Connect() throws SQLException
    {
        try {

            Class.forName(DRIVER);
            if (connection == null || connection.isClosed())
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (ClassNotFoundException exc) {
            connection = null;
        }
    }


    public synchronized static void Query(String query, String... params) throws SQLException
    {
        if(connection == null)
        {
            throw new SQLException("Подключение к базе не установлено.");
        }

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        for(int i=0; i < params.length; i++)
            preparedStatement.setString(i+1, params[i]);

        queryresult =  preparedStatement.executeQuery();

    }

    public synchronized static void UpdateQuery(String query, String... params) throws SQLException
    {
        if(connection == null)
        {
            throw new SQLException("Подключение к базе не установлено.");
        }

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        for(int i=0; i < params.length; i++)
            preparedStatement.setString(i+1, params[i]);



        preparedStatement.executeUpdate();

    }

    public static String MD5(String s)
    {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(s.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String hashed = bigInt.toString(16);

            while (hashed.length() < 32) {
                hashed = "0" + hashed;
            }
            return hashed;
        }
        catch (NoSuchAlgorithmException exc)
        {
            return "";
        }



    }

    public synchronized static boolean isNullResult() throws SQLException
    {
        return !queryresult.isBeforeFirst();

    }

    public static ResultSet getResultSet()
    {
        return queryresult;
    }


    public synchronized static void Close() throws SQLException
    {
        connection.close();
    }



}
