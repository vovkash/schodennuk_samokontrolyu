<%@ tag import="java.io.Writer" %>
<%@ tag import="java.io.PrintWriter" %>
<%@tag description="Template" pageEncoding="UTF-8"%>
<%@attribute name="title" fragment="true" %>

<html>
<header>

    <title><jsp:invoke fragment="title"/></title>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>


    <script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>


</header>

<body>
<div class="container">
    <h1>Щоденник самоконтролю</h1>
    <div class="menu">


        <a href="index.jsp"><div class="button">Головна</div></a>

        <% if(session.getAttribute("role") == "student") { %>
            <a href="/fill"><div class="button">Журнал</div></a>
        <%}%>

        <% if(session.getAttribute("role") == "student") { %>
            <a href="/showvisits"><div class="button">Відвідування</div></a>
        <%}%>

        <% if(session.getAttribute("role") == "teacher") { %>
            <a href="/students"><div class="button">Студенти</div></a>
        <%}%>


        <a href="login"><div class="button"><%= (session.getAttribute("user") == null) ? "Вхід" : "Вихід" %></div></a>



        <% if(session.getAttribute("user") == null) {%>
            <a href="registration"><div class="button">Реєстрація</div></a>
        <% } %>




    </div>
    <div class="content">
        <jsp:doBody/>
    </div>
    <div class="footer">
        By Vovan and The a-Lesha
    </div>
</div>
</body>
</html>