import java.util.Calendar;

public class Time{
    public static void main(String args[]){
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min  = cal.get(Calendar.MINUTE);
        int sec  = cal.get(Calendar.SECOND);

        System.out.println("NOW ->" + hour + ":" + min + ":" + sec);
    }
}