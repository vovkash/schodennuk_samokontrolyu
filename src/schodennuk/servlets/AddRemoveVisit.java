package schodennuk.servlets;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


import com.google.gson.JsonObject;
import schodennuk.web.Student;
import schodennuk.web.StudentDao;
import schodennuk.web.StudentDaoIml;

public class AddRemoveVisit extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.sendRedirect("/index.jsp");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter pw = response.getWriter();
        String sNickname = request.getParameter("nickname").trim();
        String sTodo = request.getParameter("todo").trim();

        int nVisit = 0;
        if(sTodo.equals("+"))
            nVisit = 1;
        if(sTodo.equals("-"))
            nVisit = -1;

        JsonObject jsonObject = new JsonObject();

        try
        {
            StudentDao sd = new StudentDaoIml();
            Student student = sd.getStudent(sNickname);

            student.setVisits(student.getVisits() + nVisit);
            sd.updateStudent(student);

            jsonObject.addProperty("success", true);
            jsonObject.addProperty("visits", student.getVisits());

        }
        catch (Exception exc)
        {
            jsonObject.addProperty("success", false);
            jsonObject.addProperty("error", exc.getMessage());
        }

        pw.println(jsonObject.toString());
        pw.close();

    }


}
