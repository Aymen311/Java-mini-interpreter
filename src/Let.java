import java.util.HashMap;

public class Let extends Commande{
    String expression ;


    public Let(String ligne , String expression) {
        super(ligne);
        this.expression = expression.substring(1, expression.length()) ; // (1,~) afin d'eviter l'espace après la commande "print 5"
    }

    private void nextChar(String str) {
        this.ch = (++this.pos < str.length()) ? str.charAt(this.pos) : -1 ; //recuperer le caractère suivant
    }

    public void run(HashMap table) throws InvalidAssignementException, UnexpectedCharException {
        int lastPos = 0 ;
        this.pos = -1 ;
        nextChar(expression);
        if (ch >= 'a' && ch <= 'z' || (ch >= 'A' && ch <= 'Z')) { // verifier si la variable commence par un char valide
            while (ch >= 'a' && ch <= 'z' || (ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'Z')) nextChar(expression);
            String variable = expression.substring(0, this.pos); // recuperer le nom de la variabe
            lastPos = this.pos ;
            while( ch == ' ' || ch == '=' ) nextChar(expression);
            if (expression.substring(lastPos, this.pos).equals(" =") || expression.substring(lastPos, this.pos).equals("= ") || expression.substring(lastPos, this.pos).equals("=")
            || expression.substring(lastPos, this.pos).equals(" = ") ) // verifier que si bien une affectation valide
            {
                try {
                    String str_valeur = expression.substring(this.pos, expression.length()); // recuperer le reste de l'expression afin
                    Double valeur = Evaluation.eval(str_valeur, table); // de l'evaluer
                    table.put(variable, valeur); // ajouter le couple( variable, valeur ) à la table de symbole
                    System.out.println("Ok");
                }
                catch (UnknownVariableException | UnexpectedCharException | ParenthesesException |UnknownFunctionException e)
                {
                    e.getMessage() ;
                }
            }
            else throw new InvalidAssignementException(expression.substring(lastPos, this.pos));
        }
        else throw new UnexpectedCharException(ch);
    }
}
