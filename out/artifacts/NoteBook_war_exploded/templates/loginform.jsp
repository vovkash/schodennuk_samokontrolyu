<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>




<t:template>
    <jsp:attribute name="title">
        Вхід у систему | Щоденник самоконтролю
    </jsp:attribute>
  <jsp:body>

      <form class="standartform"  action="/login" method="post">
          <h2>Вхід у систему</h2>
          <br>
          <p>${result}</p>

          <table style="margin: 0 auto">
              <tr><td>Логін:</td><td><input type="text" name="nickname" value="${nickname}"></td></tr>
              <tr><td>Пароль:</td><td><input type="password" name="password" value=""></td></tr>

              <tr  ><td align="center" colspan="2"><input name="submitted" type="submit" value="Ввійти"></td></tr>
          </table>
      </form>

  </jsp:body>
</t:template>