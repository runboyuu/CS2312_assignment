public class ExInvalidNewDay extends Exception{
    public ExInvalidNewDay(SystemDate sysDate){
        super("Invalid new day.  The new day has to be later than the current date "+sysDate.toString()+".");
    }
}