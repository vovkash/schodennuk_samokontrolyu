package schodennuk.servlets;

import schodennuk.web.DB;
import schodennuk.web.Student;
import schodennuk.web.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ViewJournal extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        HttpSession session = request.getSession();

        String sNickname = request.getParameter("nickname");
        if(session.getAttribute("role") == null || !session.getAttribute("role").equals("teacher") || sNickname == null)
        {
            response.sendRedirect("index.jsp");
            return;
        }

        request.setAttribute("month", Calendar.getInstance().get(Calendar.MONTH) + 1);
        request.setAttribute("year", Calendar.getInstance().get(Calendar.YEAR));

        if (request.getParameter("submit") == null)
        {
            request.setAttribute("nickname", sNickname);
            request.getRequestDispatcher("/templates/teacherviewjournal.jsp").forward(request, response);
            return;
        }

        String sMonth = request.getParameter("month").trim();
        String sYear = request.getParameter("year").trim();


        int nMonth, nYear;
        try {
            nMonth = Integer.parseInt(sMonth);
            nYear = Integer.parseInt(sYear);
        } catch (NumberFormatException exc) {
            response.sendRedirect("/view?nickname="+sNickname);
            return;
        }

        if ((nMonth > 12) || (nMonth < 1) || (nYear < 2015) || (nYear > Calendar.getInstance().get(Calendar.YEAR))) {
            response.sendRedirect("/view?nickname="+sNickname);
            return;
        }


        Student student = new Student(sNickname);
        student.getDataFromDB();

        String sTable = "<table style='margin:0 auto;'>\n";
        sTable += "<tr><th>День</th><th>ЧСС зранку*</th><th>ЧСС до заняття</th><th>ЧСС після заняття</th></tr>";

        for (int i = 1; i <= (new GregorianCalendar(nYear, nMonth-1, 1)).getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            try {
                DB.Connect();
                DB.Query("SELECT * FROM records WHERE studentid = ? AND day = ? AND month = ? AND year = ?", Integer.toString(student.getID()), Integer.toString(i), Integer.toString(nMonth), Integer.toString(nYear));
                if (DB.getResultSet().next())
                {
                    String hm = DB.getResultSet().getString("heartrate_morning");
                    String hs = DB.getResultSet().getString("heartrate_start") == null ? "" : DB.getResultSet().getString("heartrate_start");
                    String he = DB.getResultSet().getString("heartrate_end") == null ? "" : DB.getResultSet().getString("heartrate_end");

                    sTable += "<tr><td>" + i + "</td><td><input disabled type='number' name='heart_morn" + i + "' value = '" + hm +"'></td><td><input disabled type='text'  name='heart_start" + i + "' value='" + hs + "'></td><td><input disabled type='text' name='heart_end" + i + "' value='" + he + "'></td></tr>\n";
                }
                else
                    sTable += "<tr><td>" + i + "</td><td><input disabled type='number' name='heart_morn"+i+"' value=''></td><td><input disabled type='text' name='heart_start"+i+"' value=''></td><td><input disabled type='text' name='heart_end"+i+"' value=''></td></tr>\n";

            } catch (Exception exc)
            {
                pw.println(exc.getMessage());
                return;
            }
        }
        sTable += "</table>";

        request.setAttribute("month", nMonth);
        request.setAttribute("year", nYear);
        request.setAttribute("data", sTable);
        request.setAttribute("nickname", sNickname);
        request.setAttribute("currentyear",  Calendar.getInstance().get(Calendar.YEAR));


        request.getRequestDispatcher("/templates/teacherviewjournal.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}
