public class InvalidAssignementException extends Exception{
    String cause ;

    public InvalidAssignementException(String cause)
    {
        this.cause = cause  ;
    }

    public String getMessage()
    {
        System.out.println("Affectation non valide:' " + cause +"'");
        return null ;
    }

}
