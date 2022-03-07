public class UnknownFunctionException extends Exception {
    private String cause ;

    public UnknownFunctionException(String cause)
    {
        this.cause = cause ;
    }

    public String getMessage()
    {
        System.out.println("fonction non reconnu: " + this.cause);
        return null ;
    }
}
