public class InfoLogger extends LogHandler{

    
    @Override
    public void log(LogLevel level, String message)
    {
         if(LogLevel.INFO == level)
         {
            System.out.println("[INFO] " + message);
         }
         else
         {
          passToNext(level, message);
         }
    }
}
