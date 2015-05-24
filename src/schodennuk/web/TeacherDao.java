package schodennuk.web;


public interface TeacherDao {


    public boolean addTeacher(Teacher teacher);
    public Teacher getTeacher(int id);
    public Teacher getTeacher(String nickname);
    public boolean findTeacher(String nickname);
    public boolean deleteTeacher(int id);

}
