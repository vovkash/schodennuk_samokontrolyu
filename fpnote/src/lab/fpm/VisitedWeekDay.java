package lab.fpm;
import java.time.DayOfWeek;


public class VisitedWeekDay {
    private boolean WeekDays[] = new boolean[7];

    public VisitedWeekDay()
    {
        WeekDays = new boolean[] {false, false, false, false, false, false, false};
    }

    public VisitedWeekDay(boolean mon, boolean tues, boolean wedn, boolean thur, boolean fri, boolean sat, boolean sun  )
    {
        this.WeekDays[0] = mon;
        this.WeekDays[1] = tues;
        this.WeekDays[2] = wedn;
        this.WeekDays[3] = thur;
        this.WeekDays[4] = fri;
        this.WeekDays[5] = sat;
        this.WeekDays[6] = sun;

    }

    /* 1 - Mon ; 7-Sun */
    public void AddVisitedDay(int day) throws NoSuchDayException
    {
        if((day > 7) || (day<1))
            throw new NoSuchDayException("Function AddVisitedDays");

        WeekDays[day-1] = true;
    }

    public void DelVisitedDay(int day) throws NoSuchDayException
    {
        if((day > 7) || (day<1))
            throw new NoSuchDayException("Function DelVisitedDays");

        WeekDays[day-1] = false;
    }

    public String ToString()
    {
        String s = "Day Visits: ";
        for(int i=0; i<7; i++)
        {
            if(this.WeekDays[i])
                s+= DayOfWeek.of(i+1) + " ";
        }

        return s;
    }

}
