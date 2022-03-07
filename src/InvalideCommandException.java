public class InvalideCommandException extends Exception{

    String cause ;
    public InvalideCommandException(String cause)
    {
        this.cause = cause ;
    }
    public String getMessage()
    {
        System.out.println("Commande non valide: "+ this.cause);
        return null;
    }
}
