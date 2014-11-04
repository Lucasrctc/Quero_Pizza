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
        String PATH = "C:/Users/Luan Cardoso/Documents/NetBeansProjects/Otello/src/otello/";                   //Só p acabar as guerras de diretórios
        q5 = new Query("consult", new Term[] {new Atom(PATH+"eval")});
        System.out.println( "consult1 " + (q5.query() ? "succeeded" : "failed"));
        q2 = new Query("consult", new Term[] {new Atom(PATH+"game")});
        System.out.println( "consult2 " + (q2.query() ? "succeeded" : "failed"));
        q3 = new Query("consult", new Term[] {new Atom(PATH+"board")});
        System.out.println( "consult3 " + (q3.query() ? "succeeded" : "failed"));
        q4 = new Query("consult", new Term[] {new Atom(PATH+"alpha_beta_pruning")});
        System.out.println( "consult4 " + (q4.query() ? "succeeded" : "failed"));
          
    }
    
    public void InserirDificuldade(TelaDificuldade td)
    {
        String k = Integer.toString(td.getDificuldade());
        q6= new Query("play",
                new Term[]{
                    new Atom (k)});
        
        System.out.println(k);
        System.out.println( "consult6 " + (q6.query() ? "succeeded" : "failed"));
       
                
                
    }
    
}