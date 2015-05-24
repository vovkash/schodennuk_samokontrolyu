<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>




<t:template>
    <jsp:attribute name="title">
        Список студентів | Щоденник самоконтролю
    </jsp:attribute>
  <jsp:body>
        <h2> Список студентів: ${section} </h2>

      ${students}


    <script type="text/javascript" src="js/visits.js"></script>
  </jsp:body>
</t:template>