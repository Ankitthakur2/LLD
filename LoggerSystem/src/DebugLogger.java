public class DebugLogger extends LogHandler{

    @Override

    public void log(LogLevel level, String message)
    {
       if(LogLevel.DEBUG == level)
       {
        System.out.println("[DEBUG] "+ message);
       }
       else
       {
        passToNext(level, message);
       }
    }
    
}
