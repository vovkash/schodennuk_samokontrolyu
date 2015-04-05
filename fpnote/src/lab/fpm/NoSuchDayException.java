package lab.fpm;


public class NoSuchDayException extends Exception {
    private String info;

    public NoSuchDayException()
    {
        this.info = "";
    }

    public NoSuchDayException(String i)
    {
        this.info = i;
    }


    public String toString()
    {
        String s = "NoSuchDayException";

        if(this.info != "")
            s+="\n Details:" + this.info;

        return s;

    }
}
