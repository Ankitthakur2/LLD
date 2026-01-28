public abstract class LogHandler {

    protected LogHandler next;

    public void setNext(LogHandler next) {
        this.next = next;
    }

    
    public abstract void log(LogLevel level, String message);


    protected void passToNext(LogLevel level, String message)
    {
        if(next != null)
        {
          next.log(level, message);
        }

    }


}
