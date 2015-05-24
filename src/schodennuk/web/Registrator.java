package schodennuk.web;

import java.sql.SQLException;
import java.util.regex.Pattern;


public class Registrator
{
    public String regStudent(String nickname, String password, String passwordcheck, String name, String lastname, String group, String department, String teacherid)
    {
        String sResult = "";

        StudentDao sd = new StudentDaoIml();

        if (!Pattern.compile("^[a-zA-Z0-9_-]{3,20}$").matcher(nickname).matches())
        {

            sResult += "<br> Логін має складатися з латинских символів, знаків '-', '_' та цифр. ";
        }
        else
        {
            if(sd.findStudent(nickname))
                sResult += "Користувач з таким логіном уже зареєстрований!";
        }

        if (password.length() < 6) {
            sResult += "<br> Довжина паролю має бути не менше 6 символів!";
        } else {
            if (!password.equals(passwordcheck))
                sResult += "<br> Введені паролі не співпадають!";
        }

        if (!Pattern.compile("^[\\p{L}-]{3,20}$").matcher(name).matches()) {
            sResult += "<br>Ім'я введено невірно!";
        }
        if (!Pattern.compile("^[\\p{L}-]{3,20}$").matcher(lastname).matches()) {
            sResult += "<br>Прізвище введено невірно!\n";
        }
        if (department.length() < 3) {
            sResult += "<br>Назва факультету має бути більше 2х символів!\n";
        }
        if (group.length() < 3) {
            sResult += "<br>Назва групи має бути більше 2х символів!\n";
        }

        String section = "";

        if (teacherid.length() < 1) {
            sResult += "<br>Ви не обрали секцію!\n";
        }
        else
        {
            TeacherDao td = new TeacherDaoIml();

            Teacher t = td.getTeacher(Integer.parseInt(teacherid));
            section = t.getSection();
        }



        if(sResult.length() > 0)
            return sResult;


        Student s = new Student(nickname);
        s.setPassword(DB.MD5(password));
        s.setName(name);
        s.setLastname(lastname);
        s.setGroup(group);
        s.setDepartment(department);
        s.setTeacherid(Integer.parseInt(teacherid));
        s.setSection(section);

        if(sd.addStudent(s))
            return "success";
        return "fault";
    }


    public String regTeacher(String nickname, String password, String passwordcheck, String name, String lastname, String section)
    {

        String sResult = "";
        TeacherDao td = new TeacherDaoIml();

        if (!Pattern.compile("^[a-zA-Z0-9_-]{3,20}$").matcher(nickname).matches()) {
            sResult += "<br> Логін має складатися з латинских символів, знаків '-', '_' та цифр. ";
        } else {
            if (td.findTeacher(nickname))
                sResult += "Користувач з таким логіном вже зареєстрований!";

        }

        if (password.length() < 6) {
            sResult += "<br> Довжина паролю має бути не менше 6 символів!";
        } else {
            if (!password.equals(passwordcheck))
                sResult += "<br> Введені паролі не співпадають!";


        }

        if (!Pattern.compile("^[\\p{L}-]{3,20}$").matcher(name).matches()) {
            sResult += "<br>Ім'я введено невірно!";
        }
        if (!Pattern.compile("^[\\p{L}-]{3,20}$").matcher(lastname).matches()) {
            sResult += "<br>Прізвище введено невірно!\n";
        }

        if (section.length() < 1) {
            sResult += "<br>Ви не обрали секцію!\n";
        }


        if (sResult.length() > 0)
            return sResult;


        Teacher t = new Teacher(nickname);
        t.setPassword(DB.MD5(password));
        t.setName(name);
        t.setLastname(lastname);
        t.setSection(section);

        if (td.addTeacher(t))
            return "success";

        return "fault";




    }
}
