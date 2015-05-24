<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:attribute name="title">
        Редагування | Щоденник самоконтролю
    </jsp:attribute>
  <jsp:body>

    <h2>Оберіть дату:</h2><br>
    <form class="standartform" method="get" action="/view">
      Місяць:<input name="month" type="number" min="1" max="12" value="${month}">
      Рік: <input name="year" type="number" min="2015" max="${currentyear}" value="${year}">
      <input name="nickname" hidden value="${nickname}">
      <input type="submit" name="submit" value="OK">

    </form>

    ${data}

  </jsp:body>
</t:template>