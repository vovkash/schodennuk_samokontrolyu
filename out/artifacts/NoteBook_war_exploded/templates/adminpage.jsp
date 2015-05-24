<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>




<t:template>
    <jsp:attribute name="title">
        Адмінка | Щоденник самоконтролю
    </jsp:attribute>
  <jsp:body>
      <form style="float:right; margin: 3px;" method="post" action="/admin"><input type="submit" value="Вийти" name="submit"/></form>

      <h2>Список викладачів</h2>
      <table border cellspacing="0" id="teacherlist">

            ${teachers}

      </table>

      <br>
      <br>

      <h2>Видалення</h2>
      <form class="standartform" action="/admin" method="post">
          <table>
              <tr><td>ID:</td><td> <input type="text" name="delete_id"/></td></tr>

              <tr><td colspan="2" align="center"> <input type="submit" value="Видалити" name="submit"/></td></tr>

          </table>


      </form>

      <br>
      <br>

      <h2>Новий викладач</h2>


      <form class="standartform" action="/admin" method="post">
              ${result}
          <table>
              <tr><td>Логін:</td><td> <input type="text" name="nickname" value="${nickname}"/></td></tr>
              <tr><td>Прізвище:</td><td> <input type="text" name="lastname" value="${lastname}"/></td></tr>
              <tr><td>Ім'я:</td><td> <input type="text" name="name" value="${name}"/></td></tr>
              <tr><td>Направлення:</td><td><select name="section" ><option selected></option><option>Волейбол</option><option>Баскетбол</option><option>Плавання</option></select></td></tr>
              <tr><td>&nbsp</td><td></td></tr>
              <tr><td>Пароль:</td><td> <input type="password" name="pass"/></td></tr>
              <tr><td>Повторити пароль:</td><td> <input type="password" name="passcheck"/></td></tr>
              <tr><td colspan="2" align="center"> <input type="submit" value="Додати" name="submit"/></td></tr>



          </table>


      </form>

    <br>


  </jsp:body>
</t:template>
