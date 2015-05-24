<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>




<t:template>
    <jsp:attribute name="title">
        Реєстрація | Щоденник самоконтролю
    </jsp:attribute>
  <jsp:body>



          <form class="standartform"  action="/registration" method="post">
              <h2>Реєстрація студента</h2>
              <br>
              <p>${result}</p>

              <table style="margin: 0 auto">
                  <tr><td>Логін:</td><td><input type="text" name="nickname" value="${nickname}"></td></tr>
                  <tr><td>&nbsp;</td><td><br></td></tr>
                  <tr><td>Пароль:</td><td><input type="password" name="pass"></td></tr>
                  <tr><td>Повторити пароль:</td><td><input type="password" name="pass_check"></td></tr>
                  <tr><td>&nbsp;</td><td><br></td></tr>
                  <tr><td>Ім'я:</td><td><input type="text" name="name" value="${name}"></td></tr>
                  <tr><td>Прізвище:</td><td><input type="text" name="lastname" value="${lastname}"></td></tr>
                  <tr><td>Факультет:</td><td><input type="text" name="department" value="${departnament}"></td></tr>
                  <tr><td>Група:</td><td> <input type="text" name="group" value="${group}"></td></tr>
                  <tr><td>Викладач:</td><td><select name="teacher" ><option selected></option>${teachers}</select></td></tr>
                  <tr><td>&nbsp;</td><td><br></td></tr>
                  <tr  ><td align="center" colspan="2"><input name="submitted" type="submit" value="Зареєструватися"></td></tr>
              </table>
          </form>


  </jsp:body>
</t:template>