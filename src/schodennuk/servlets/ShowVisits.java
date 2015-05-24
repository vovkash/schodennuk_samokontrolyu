package schodennuk.servlets;

import schodennuk.web.Student;
import schodennuk.web.StudentDao;
import schodennuk.web.StudentDaoIml;
import schodennuk.web.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowVisits extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        HttpSession session = request.getSession();

        if(session.getAttribute("role") == null || !session.getAttribute("role").equals("student"))
        {
            response.sendRedirect("/index.jsp");
            return;
        }

        Student student = (Student)session.getAttribute("user");

        request.setAttribute("visits", student.getVisits());
        request.getRequestDispatcher("/templates/showvisitings.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
