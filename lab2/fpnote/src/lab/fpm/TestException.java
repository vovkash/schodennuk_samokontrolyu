package lab.fpm;
import java.io.Console;
import java.util.Scanner;

public class TestException {

    public static void main(String[] args)
    {

        Student student1 = new Student("adsfg", "parol", "Pavel", "Vasin", 1, "FPM", "KM-31", new VisitedWeekDay(false,false,true,false,false,false,false));
        System.out.println(student1.Info());


        Student student2 = new Student("nick", "parol111", "Konstantin", "Panev", 2, "FPM", "KM-43");
        VisitedWeekDay vwd = new VisitedWeekDay();
        student2.setVisitationDays(vwd);

        System.out.println("\nNow, enter visitation week days for Konstantin as numbers from 1 to 7. To stop enter '-1'");


        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        while(!s.equalsIgnoreCase("-1"))
        {

            try
            {
                vwd.AddVisitedDay(Integer.parseInt(s));
            }
            catch(NumberFormatException e)
            {
                System.out.println("Error! Invalid number. \n" + e.toString());

            }
            catch (NoSuchDayException e)
            {
                System.out.println("Error! Invalid weekday. Number must be 1..7 \n" + e.toString());
            }
            catch (Exception e)
            {
                System.out.println("Another exception. Please, enter valid data. \n" + e.toString());
            }

            s = scanner.nextLine();

        }


        System.out.println(student2.Info());




    }
}
