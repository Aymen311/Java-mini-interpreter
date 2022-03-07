public class UnexpectedCharException extends Exception {
    private int cause ;
    public UnexpectedCharException(int cause)
    {
        this.cause = cause ;
    }

    public String getMessage()
    {
        System.out.println("Caractère non attendu: " + (char)cause);
        return null ;
    }
}
