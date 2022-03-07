public class UnknownVariableException extends Exception {

    private String cause ;

    public UnknownVariableException(String cause)
    {
        this.cause = cause ;
    }

    public String getMessage()
    {
        System.out.println("variable non reconnue:'" + cause +"'");
        return null ;
    }
}
