public class ErrorLogger extends LogHandler{

    @Override

    public void log(LogLevel level, String message)
    {
        if(LogLevel.INFO == level)
        {
            System.out.println("[Error] "+ message);
        }
        else
        {
            passToNext(level, message);
        }

    }
    
}
