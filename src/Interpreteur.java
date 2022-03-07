import java.util.HashMap;
import java.util.Scanner;

public class Interpreteur {
    private String ligne ; //ligne de commande
    HashMap<String, Double> table_symboles = new HashMap<String,Double>() ; //table de symbole en structure de HashMap<String,Double>

    public Interpreteur()  {
        Scanner sc= new Scanner(System.in);
        System.out.print(">>> ");
        this.ligne = sc.nextLine() ;
        while(!(this.ligne).equals("end")) //boucle afin d'interpreter les commandes, "end" pour quitter.
        {
                executer(); //methode executer de l'interpreteur
                System.out.print(">>> ");
                this.ligne = sc.nextLine();
        }
    }

    public void executer() {
        Commande commande = new Commande(this.ligne);
        try {
            String instruction = commande.extract_command(); //methode de la classe Commande afin d'extraire le nom de la commande
            String expression = commande.extract_expression() ; //extraire l'expression
            if (instruction.equals("print"))
            {
                Print print = new Print(this.ligne , expression) ;
                print.run(table_symboles); //executer la commande print
            }
            else if (instruction.equals("let"))
            {
                Let let = new Let(this.ligne , expression) ;
                let.run(table_symboles);//executer la commande let
            }
        }
        catch (InvalideCommandException | SpaceMissingException | InvalidAssignementException | UnexpectedCharException e)
        {
            e.getMessage() ;
        }
    }



}
