import java.util.HashMap;

public class Print extends Commande{
    String expression  ;

    public Print(String ligne, String expression) {
        super(ligne);
        this.expression = expression ;
    }

    public void run(HashMap table)
    {
        try {
            System.out.println("La valeur est: " + Evaluation.eval(expression, table)); //appele a la methode evaluer
        }
        catch (UnknownVariableException | UnexpectedCharException | ParenthesesException |UnknownFunctionException e)
        {
            e.getMessage() ;
        }

    }
}
