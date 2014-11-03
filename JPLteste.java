package otello;


import java.util.Hashtable;
import jpl.Atom;
import jpl.Compound;
import jpl.Query;
import jpl.Term;
import jpl.Variable;



public class JPLteste
{
    public static void main(String []args)
    {
        
        Query q1 = new Query("consult", new Term[] {new Atom("C:/Users/Luan Cardoso/Documents/NetBeansProjects/Otello/input.pl")});
        q1.query();
      
        Variable X=new Variable();
    Query q= new Query(new Compound("ensina",new Term[]{new Atom("veloso"),new Variable("X")}));
    
        while(q.hasMoreElements())
        {
            Hashtable binding=(Hashtable) q.nextElement();
            Term t=(Term) binding.get("X");
            System.out.println(t);
        }
       
        
    
        
    }
    
    
    
}


