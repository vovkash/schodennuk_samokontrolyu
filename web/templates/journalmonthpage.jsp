<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:attribute name="title">
        Редагування | Щоденник самоконтролю
    </jsp:attribute>
  <jsp:body>

    <h2>Журнал за ${month}й місяць ${year} року</h2>
    <form class="standartform" method="post" action="/fill">
        ${table}
        <input type="submit" name="submit" value="Зберегти"/>

            <input hidden type="text" name="month" value="${month}"/>
            <input hidden type="text" name="year" value="${year}"/>

    </form>
  </jsp:body>
</t:template>