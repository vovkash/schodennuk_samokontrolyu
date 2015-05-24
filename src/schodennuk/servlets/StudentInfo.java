package schodennuk.servlets;

import schodennuk.web.DB;
import schodennuk.web.Student;
import schodennuk.web.Teacher;
import schodennuk.web.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class StudentInfo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        if(session.getAttribute("role") == null || session.getAttribute("role") != "teacher")
        {
            response.sendRedirect("/index.jsp");
            return;
        }



        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();


        Teacher teacher = (Teacher)session.getAttribute("user");


        try
        {
            DB.Connect();
            DB.Query("SELECT * FROM students WHERE teacher_id = ? ORDER BY `department`, `group`, `lastname`", Integer.toString(teacher.getID()));
            DB.getResultSet();

            String sTable = "<table style='border-color:gray;' border cellspacing=0 id='stview'>\n";
            sTable += "<tr><th>Прізвище</th><th>Ім'я</th><th>Факультет</th><th>Група</th><th>Логін</th><th colspan=2>К-сть відвідувань</th><th>Детальніше</th></tr>\n";

            while(DB.getResultSet().next())
            {
                String sNickname = DB.getResultSet().getString("nickname");
                String sLastname = DB.getResultSet().getString("lastname");
                String sName = DB.getResultSet().getString("name");
                String sGroup = DB.getResultSet().getString("group");
                String sDepartment = DB.getResultSet().getString("department");
                int nVisitings = DB.getResultSet().getInt("visitings");

                sTable += String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%d</td><td><button width=35px type='submit'>+</button><button width=35px  type='submit'>-</button></td><td><a href='/view?nickname=%s'>Журнал</a></td></tr>\n", sLastname, sName, sDepartment,sGroup, sNickname, nVisitings, sNickname);

            }

            sTable += "</table>\n";

            request.setAttribute("students", sTable);
            request.setAttribute("section", teacher.getSection());
            request.getRequestDispatcher("/templates/studentstable.jsp").forward(request, response);

        }
        catch (SQLException exc)
        {
            pw.println("Помилка БД: "+exc.getMessage());
        }


    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }


}
