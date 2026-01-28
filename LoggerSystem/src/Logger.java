public class Logger {

    private LogHandler firsHandler;

    public Logger()
    {
      LogHandler debug = new DebugLogger();
      LogHandler info = new InfoLogger();
      LogHandler error = new ErrorLogger();

    debug.setNext(info);
    info.setNext(error);
    this.firsHandler = debug;
    }


  public void log(LogLevel level, String message)
  {
     firsHandler.log(level, message);
  }

    
}
