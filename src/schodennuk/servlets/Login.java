package schodennuk.servlets;

import schodennuk.web.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class Login extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null)
            session.invalidate();

        request.getRequestDispatcher("/templates/loginform.jsp").forward(request,response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String sNickname = request.getParameter("nickname").trim();
        String sPassword = request.getParameter("password").trim();

        Authenticator authenticator = new Authenticator();

        String result = authenticator.authenticate(sNickname, sPassword);
        if(!result.equals("student") && !result.equals("teacher"))
        {
            request.setAttribute("result", result);
            request.getRequestDispatcher("/templates/loginform.jsp").forward(request,response);
            return;
        }

        request.getSession().invalidate();
        HttpSession session = request.getSession();

        if(result.equals("teacher"))
        {
            TeacherDao td = new TeacherDaoIml();
            Teacher teacher = td.getTeacher(sNickname);
            session.setAttribute("user", teacher);
        }
        else
        {
            StudentDao sd = new StudentDaoIml();
            Student student = sd.getStudent(sNickname);
            session.setAttribute("user", student);

        }

        session.setAttribute("role", result);


        request.getRequestDispatcher("/index.jsp").forward(request,response);




    }


}
