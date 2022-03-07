import java.util.HashMap;

public class Evaluation {
    public static double eval(final String str, HashMap variables) throws UnknownVariableException, UnexpectedCharException, ParenthesesException, UnknownFunctionException {
        return new Object() {

            int pos = -1, ch;
            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean check(int charToCheck) { //verifier si le ch correspond à un certain char
                while (ch == ' ') nextChar();
                if (ch == charToCheck) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double analyser() throws UnexpectedCharException, UnknownVariableException, ParenthesesException, UnknownFunctionException {
                nextChar();
                double x = AnalyserExpression(); //methode recursive
                if (pos < str.length()) throw new UnexpectedCharException(ch) ; //
                return x;
            }

            double AnalyserExpression() throws UnknownVariableException, UnexpectedCharException, ParenthesesException, UnknownFunctionException {
                double x = analyserTerm();
                for (;;) { // infinte loop until return 
                    if      (check('+')) x += analyserTerm(); // addition
                    else if (check('-')) x -= analyserTerm(); // soustraction
                    else return x;
                }
                
            }

            double analyserTerm() throws UnknownVariableException, UnexpectedCharException, ParenthesesException, UnknownFunctionException {
                double x = analyserFacteur();
                for (;;) {
                    if      (check('*')) x *= analyserFacteur(); // multiplication
                    else if (check('/')) {
                       try {
                           x /= analyserFacteur();
                       }
                       catch (ArithmeticException e)
                       {
                           System.out.println("Devision par zero !");
                       }
                    }// division
                    else return x ;
                }
            }

            double analyserFacteur() throws UnknownVariableException, ParenthesesException, UnknownFunctionException, UnexpectedCharException {
                if (check('+')) return analyserFacteur(); // plus unair
                if (check('-')) return -analyserFacteur(); // moins unair

                double x;
                int startPos = this.pos;
                if (check('(')) { // parentheses
                    x = AnalyserExpression();
                    if(!check(')')) throw new ParenthesesException() ;
                }  else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z'|| (ch >= 'A' && ch <= 'Z')) { // functions & variables
                    while (ch >= 'a' && ch <= 'z' || (ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'Z')) nextChar();
                    String func = str.substring(startPos, this.pos);
                    String variable = func;
                    if(!(variable.equals("sqrt") || variable.equals("sin") || variable.equals("cos") ||
                            variable.equals("tan")|| variable.equals("log")|| variable.equals("abs")|| variable.equals("exp"))){//variable
                        if (variables.containsKey(variable)) x = (double) variables.get(variable);
                        else throw new UnknownVariableException(variable);}
                    else {
                        x = analyserFacteur();
                        x = switch (func) {
                            case "sqrt" -> Math.sqrt(x);
                            case "sin" -> Math.sin(Math.toRadians(x));
                            case "cos" -> Math.cos(Math.toRadians(x));
                            case "tan" -> Math.tan(Math.toRadians(x));
                            case "log" -> Math.log(x);
                            case "abs" -> Math.abs(x);
                            case "exp" -> Math.exp(x);
                            default -> throw new UnknownFunctionException(func);
                        };
                    }
                }  else {
                    throw new UnexpectedCharException(ch);
                }

                if (check('^')) x = Math.pow(x, analyserFacteur()); //exp

                return x;
            }
        }.analyser();
    }
}
