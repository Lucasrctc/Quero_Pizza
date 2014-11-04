package otello;

import jpl.Atom;
import jpl.Compound;
import jpl.Query;
import jpl.Term;
import jpl.Variable;



public class Prolog
{
    private Query q2;
    private Query q3;
    private Query q4;
    private Query q5;
    private Query q6;
    
    public void IniciarOtello()
    {
       
        q5 = new Query("consult", new Term[] {new Atom("C:/Users/Luan Cardoso/Documents/NetBeansProjects/Otello/eval.pl")});
        System.out.println( "consult2 " + (q5.query() ? "succeeded" : "failed"));
        
        q2 = new Query("consult", new Term[] {new Atom("C:/Users/Luan Cardoso/Documents/NetBeansProjects/Otello/game.pl")});
        System.out.println( "consult3 " + (q2.query() ? "succeeded" : "failed"));
        
        q3 = new Query("consult", new Term[] {new Atom("C:/Users/Luan Cardoso/Documents/NetBeansProjects/Otello/board.pl")});
        System.out.println( "consult4 " + (q3.query() ? "succeeded" : "failed"));
        
        q4= new Query("consult", new Term[] {new Atom("C:/Users/Luan Cardoso/Documents/NetBeansProjects/Otello/alpha_beta_pruning.pl")});
        System.out.println( "consult5 " + (q4.query() ? "succeeded" : "failed"));
          
    }
    
    public void InserirDificuldade(TelaDificuldade td)
    {
          
        q6= new Query("play",
                new Term[]{
                    new Atom (Integer.toString(td.getDificuldade()))});
        
        System.out.println(Integer.toString(td.getDificuldade()));
        System.out.println( "consult6 " + (q6.query() ? "succeeded" : "failed"));
       
                
                
    }
    
}