package otello;

import jpl.Atom;
import jpl.Query;
import jpl.Term;


public class Prolog
{
    public void IniciarOtello()
    {
        Query q1 = new Query("consult", new Term[] {new Atom("C:/Users/Luan Cardoso/Documents/NetBeansProjects/Otello/othello.pl")});
        System.out.println( "consult " + (q1.query() ? "succeeded" : "failed"));
        
        Query q2 = new Query("consult", new Term[] {new Atom("C:/Users/Luan Cardoso/Documents/NetBeansProjects/Otello/game.pl")});
        System.out.println( "consult " + (q2.query() ? "succeeded" : "failed"));
        
        Query q3 = new Query("consult", new Term[] {new Atom("C:/Users/Luan Cardoso/Documents/NetBeansProjects/Otello/board.pl")});
        System.out.println( "consult " + (q3.query() ? "succeeded" : "failed"));
        
        Query q4 = new Query("consult", new Term[] {new Atom("C:/Users/Luan Cardoso/Documents/NetBeansProjects/Otello/alpha_beta_pruning.pl")});
        System.out.println( "consult " + (q4.query() ? "succeeded" : "failed"));
        
        Query q5 = new Query("consult", new Term[] {new Atom("C:/Users/Luan Cardoso/Documents/NetBeansProjects/Otello/eval.pl")});
        System.out.println( "consult " + (q5.query() ? "succeeded" : "failed"));
        
        
        
    }
}