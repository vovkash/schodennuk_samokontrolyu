package schodennuk.servlets;

import schodennuk.web.DB;
import schodennuk.web.Registrator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


public class Registration extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        try
        {
            String sTeachers = "";

            DB.Connect();
            DB.Query("SELECT * FROM teachers");
            while(DB.getResultSet().next())
            {
                sTeachers += String.format("<option value='%s'>%s %s (%s)</option>", DB.getResultSet().getString("id"), DB.getResultSet().getString("lastname"), DB.getResultSet().getString("name"),DB.getResultSet().getString("section"));
            }
            DB.Close();

            request.setAttribute("teachers", sTeachers);
            request.getRequestDispatcher("/templates/registrationform.jsp").forward(request, response);

        }
        catch (SQLException exc)
        {
            pw.println("БД помилка: "+ exc.getMessage());
        }




    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        try
        {
            
            String submitted = request.getParameter("submitted");

            if (submitted == null)
            {

                request.getRequestDispatcher("/templates/registrationform.jsp").forward(request, response);
                return;
            }

            String sNickName = request.getParameter("nickname").trim();
            String sPassword = request.getParameter("pass").trim();
            String sPassCheck = request.getParameter("pass_check").trim()  ;
            String sName = request.getParameter("name").trim();
            String sLastName = request.getParameter("lastname").trim();
            String sDepartment = request.getParameter("department").trim();
            String sGroup = request.getParameter("group").trim();
            String sTeacherid = request.getParameter("teacher").trim();
            
            Registrator registrator = new Registrator();
            String sResult = registrator.regStudent(sNickName, sPassword, sPassCheck, sName, sLastName, sGroup, sDepartment, sTeacherid);

            if (sResult.equals("success"))
            {
                response.sendRedirect("/login");
                return;
            }


            sResult += "<br>";
            request.setAttribute("result", sResult);
            request.setAttribute("nickname", sNickName);
            request.setAttribute("name", sName);
            request.setAttribute("department", sDepartment);
            request.setAttribute("group", sGroup);

            this.doGet(request,response);


            //request.getRequestDispatcher("/templates/registrationform.jsp").forward(request, response);
                

            


        }
        catch(Exception exc)
        {
            pw.println("ERROR! " + exc.getMessage());
        }

    }
}
