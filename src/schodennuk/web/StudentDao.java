package schodennuk.web;


public interface StudentDao
{
    public boolean addStudent(Student student);
    public Student getStudent(int id);
    public Student getStudent(String nickname);
    public boolean findStudent(String nickname);
    public void updateStudent(Student student);

}
