package schodennuk.servlets;

import schodennuk.web.DB;
import schodennuk.web.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FillJornal extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") == null) {
                response.sendRedirect("/index.jsp");
                return;
            }

            request.setAttribute("currentmonth", Calendar.getInstance().get(Calendar.MONTH)+1);
            request.setAttribute("currentyear", Calendar.getInstance().get(Calendar.YEAR));

            if (request.getParameter("submit") == null) {
                request.getRequestDispatcher("/templates/choosemonthform.jsp").forward(request, response);
                return;
            }

            String sMonth = request.getParameter("month").trim();
            String sYear = request.getParameter("year").trim();


            int nMonth, nYear;
            try {
                nMonth = Integer.parseInt(sMonth);
                nYear = Integer.parseInt(sYear);
            } catch (NumberFormatException exc) {
                request.getRequestDispatcher("/templates/choosemonthform.jsp").forward(request, response);
                return;
            }

            if ((nMonth > 12) || (nMonth < 1) || (nYear < 2015) || (nYear > Calendar.getInstance().get(Calendar.YEAR))) {
                request.getRequestDispatcher("/templates/choosemonthform.jsp").forward(request, response);
                return;
            }


            User user = (User) session.getAttribute("user");
            user.getDataFromDB();

            String sTable = "<table style='margin:0 auto;'>\n";
            sTable += "<tr><th>День</th><th>ЧСС зранку*</th><th>ЧСС до заняття</th><th>ЧСС після заняття</th></tr>";

            for (int i = 1; i <= (new GregorianCalendar(nYear, nMonth-1, 1)).getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                try {
                    DB.Connect();
                    DB.Query("SELECT * FROM records WHERE studentid = ? AND day = ? AND month = ? AND year = ?", Integer.toString(user.getID()), Integer.toString(i), Integer.toString(nMonth), Integer.toString(nYear));
                    if (DB.getResultSet().next())
                    {
                        String hm = DB.getResultSet().getString("heartrate_morning");
                        String hs = DB.getResultSet().getString("heartrate_start") == null ? "" : DB.getResultSet().getString("heartrate_start");
                        String he = DB.getResultSet().getString("heartrate_end") == null ? "" : DB.getResultSet().getString("heartrate_end");

                        sTable += "<tr><td>" + i + "</td><td><input type='number' name='heart_morn" + i + "' value = '" + hm +"'></td><td><input type='text'  name='heart_start" + i + "' value='" + hs + "'></td><td><input type='text' name='heart_end" + i + "' value='" + he + "'></td></tr>\n";
                    }
                    else
                        sTable += "<tr><td>" + i + "</td><td><input type='number' min=1 name='heart_morn"+i+"' value=''></td><td><input type='number' min=1 name='heart_start"+i+"' value=''></td><td><input type='number' min=1 name='heart_end"+i+"' value=''></td></tr>\n";

                } catch (Exception exc)
                {
                    pw.println(exc.getMessage());
                    return;
                }
            }
            sTable += "</table>";

            request.setAttribute("month", nMonth);
            request.setAttribute("year", nYear);
            request.setAttribute("table", sTable);


            request.getRequestDispatcher("/templates/journalmonthpage.jsp").forward(request, response);
        }
        catch (Exception exc)
        {
            pw.println(exc.getMessage());
        }



    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        try
        {

            if (request.getParameter("submit") == null)
                return;

            String sMonth = request.getParameter("month").trim();
            String sYear = request.getParameter("year").trim();


            int nMonth, nYear;
            try {
                nMonth = Integer.parseInt(sMonth);
                nYear = Integer.parseInt(sYear);
            } catch (NumberFormatException exc) {
                request.getRequestDispatcher("/templates/choosemonthform.jsp").forward(request, response);
                return;
            }


            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");
            user.getDataFromDB();

            DB.Connect();
            for (int i = 1; i <= (new GregorianCalendar(nYear, nMonth-1, 1)).getActualMaximum(Calendar.DAY_OF_MONTH); i++)
            {
                if(request.getParameter("heart_morn"+i).length() < 1)
                    continue;

                String hs = request.getParameter("heart_start"+i).length()<1 ? null : request.getParameter("heart_start"+i);
                String he = request.getParameter("heart_end"+i).length()<1 ? null : request.getParameter("heart_end"+i);

                DB.Query("SELECT * FROM records WHERE day = ? AND month = ? AND year = ? AND studentid = ?", Integer.toString(i), sMonth, sYear, Integer.toString(user.getID()));
                if(DB.isNullResult())
                    DB.UpdateQuery("INSERT INTO records (heartrate_morning ,heartrate_start, heartrate_end, studentid, `day`, `month`, `year`) VALUES (?,?,?,?,?,?,?)", request.getParameter("heart_morn"+i), hs, he, Integer.toString(user.getID()), Integer.toString(i), sMonth, sYear);
                else
                {
                    DB.getResultSet().next();
                    DB.UpdateQuery("UPDATE records SET heartrate_morning = ?, heartrate_start = ?, heartrate_end = ? WHERE id = ?", request.getParameter("heart_morn"+i), hs, he, DB.getResultSet().getString("id"));
                }
            }
            DB.Close();
            response.sendRedirect("/fill?month="+sMonth+"&year="+sYear+"&submit=OK");
        }
        catch (SQLException exc)
        {
            pw.println(exc.getMessage());
        }

    }
}
