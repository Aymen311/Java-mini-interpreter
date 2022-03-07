public  class Commande {

    protected int pos = -1, ch;
    protected String str ;

    public Commande(String ligne)
    {
        this.str = ligne ;
    }

    private void nextChar() {
        this.ch = (++this.pos < str.length()) ? str.charAt(this.pos) : -1 ;
    }

    public String extract_command() throws InvalideCommandException { // extraire le nom de la commande
        nextChar();
        while (this.ch >= 'a' && this.ch <= 'z' ) nextChar();
        String command  =  str.substring(0, this.pos); ;
        if(!command.equals("print") && !command.equals("let")) throw new InvalideCommandException(command) ;
        return command ;
    }

    public String extract_expression() throws InvalideCommandException, SpaceMissingException {//extraire l'expression d'une ligne de commande
        this.pos = -1 ;
        extract_command() ;
        if(this.ch != ' '){// si la commande n'est pas suivie d'un espace exmpl : print5
            throw new SpaceMissingException();
        }
        else return str.substring(this.pos, str.length());
    }
}
