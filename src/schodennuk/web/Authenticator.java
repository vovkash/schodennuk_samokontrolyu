package schodennuk.web;

public class Authenticator {

    public String authenticate(String nickname, String password)
    {
        try
        {
            if(nickname.length()*password.length() == 0)
                return "Заповніть потрібні поля!";



            StudentDao sd = new StudentDaoIml();
            Student s = sd.getStudent(nickname);
            if(s != null && s.getPassword().equals(DB.MD5(password)))
                return "student";


            TeacherDao td = new TeacherDaoIml();
            Teacher t = td.getTeacher(nickname);
            if(t != null && t.getPassword().equals(DB.MD5(password)))
                return "teacher";

            return "Користувач з даним логіном/паролем не знайдений";
        }
        catch (Exception ex)
        {
            return "Технічна помилка!";
        }

    }
}
