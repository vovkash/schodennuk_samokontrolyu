package schodennuk.servlets;

import schodennuk.web.DB;
import schodennuk.web.Registrator;
import schodennuk.web.TeacherDao;
import schodennuk.web.TeacherDaoIml;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminPage extends HttpServlet {

    private final String ADMIN_LOGIN = "admin";
    private final String ADMIN_PASSWORD = DB.MD5("admin1");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter pw = response.getWriter();

        HttpSession session = request.getSession();
        if((session.getAttribute("admin") == null) || !session.getAttribute("admin").equals(ADMIN_PASSWORD))
        {
            //pw.println("xddddd1");
            session.invalidate();
            request.getRequestDispatcher("/templates/admin_loginform.jsp").forward(request, response);
            return;
        }



        String sResultTeachers = "";

        try
        {

            DB.Connect();
            DB.Query("SELECT * FROM teachers");
            ResultSet rs = DB.getResultSet();
            sResultTeachers += "<tr><th>ID</th><th>Логін</th><th>Прізвище</th><th>Ім'я</th><th>Секція</th></tr>";
            while(rs.next())
            {
                sResultTeachers += String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>\n", rs.getString("id"), rs.getString("nickname"), rs.getString("lastname"), rs.getString("name"), rs.getString("section"));

            }
        }
        catch (SQLException exc)
        {

            request.setAttribute("teachers", exc.getMessage());
            request.getRequestDispatcher("/templates/adminpage.jsp").forward(request, response);
            return;
        }

        request.setAttribute("teachers", sResultTeachers);
        request.getRequestDispatcher("/templates/adminpage.jsp").forward(request, response);



    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();






        String submit = request.getParameter("submit");
        if(submit == null)
        {
            request.getRequestDispatcher("/templates/admin_loginform.jsp").forward(request,response);
            return;

        }


        if(submit.equals("Ввійти"))
        {

            String sNick = request.getParameter("nickname").trim();
            String sPassword = request.getParameter("password").trim();

            if (sNick.equals(ADMIN_LOGIN) && DB.MD5(sPassword).equals(ADMIN_PASSWORD))
            {
                HttpSession session = request.getSession();
                session.setAttribute("admin", ADMIN_PASSWORD);
                response.sendRedirect("/admin");
                return;

            }

            request.getRequestDispatcher("/templates/admin_loginform.jsp").forward(request,response);
            return;

        }


        if(submit.equals("Видалити"))
        {
            String sID = request.getParameter("delete_id").trim();

            TeacherDao td = new TeacherDaoIml();
            td.deleteTeacher(Integer.parseInt(sID));

            response.sendRedirect("/admin");
        }

        if(submit.equals("Додати"))
        {

            String sNickName = request.getParameter("nickname").trim();
            String sPassword = request.getParameter("pass").trim();
            String sPassCheck = request.getParameter("passcheck").trim();
            String sName = request.getParameter("name").trim();
            String sLastName = request.getParameter("lastname").trim();
            String sSection = request.getParameter("section").trim();


            Registrator registrator = new Registrator();
            String sResult = registrator.regTeacher(sNickName, sPassword, sPassCheck, sName, sLastName, sSection);


            if (sResult.equals("success"))
            {
                response.sendRedirect("/admin");
                return;
            }

            sResult += "<br><br><br>";
            request.setAttribute("result", sResult);
            request.setAttribute("nickname", sNickName);
            request.setAttribute("name", sName);
            request.setAttribute("lastname", sLastName);
            request.getRequestDispatcher("/templates/adminpage.jsp").forward(request, response);

            return;

        }

        if(submit.equals("Вийти"))
        {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("/admin");
            return;
        }

    }


}
