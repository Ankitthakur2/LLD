public class App {
    public static void main(String[] args) throws Exception {
       Logger logger = new Logger();
       logger.log(LogLevel.INFO, "print in info");

       Logger logger2 = new Logger();
       logger2.log(LogLevel.DEBUG, "print in Debug");

    }
}
