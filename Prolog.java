package otello;

import java.util.Hashtable;
import jpl.Atom;
import jpl.Compound;
import jpl.Query;
import jpl.Term;
import jpl.Variable;
import java.lang.String;
import javax.swing.JFrame;



public class Prolog
{
    private Query q;
    private Query q1;
    private Query q2;
    private Query q3;
    private Query q4;
    private Query q5;
    private Query queryscores;
    int scores[]=new int [2];
    Variable termino_jogo;
    String fim="";
    
    public String getFim()
    {
        return fim;
    }
    
    public int getScoreBlack()
    {
        return scores[0];
    }
    public int getScoreWhite()
    {
        return scores[1];
    }
    
    
    public void IniciarOtello()
    {
        String PATH = "C:/Users/Luan Cardoso/Documents/NetBeansProjects/Otello/src/otello/";                   
        q1 = new Query("consult", new Term[] {new Atom(PATH+"valor.pl")});
        System.out.println( "Valor.pl carregado " + (q1.query() ? "succeeded" : "failed"));
        q1 = new Query("consult", new Term[] {new Atom(PATH+"utilities.pl")});
        System.out.println( "Utilities.pl carregado " + (q1.query() ? "succeeded" : "failed"));
        q1 = new Query("consult", new Term[] {new Atom(PATH+"board.pl")});
        System.out.println( "Board.pl carregado " + (q1.query() ? "succeeded" : "failed"));
        q1 = new Query("consult", new Term[] {new Atom(PATH+"game.pl")});
        System.out.println( "Game.pl carregado " + (q1.query() ? "succeeded" : "failed"));
        q1 = new Query("consult", new Term[] {new Atom(PATH+"inteligencia.pl")});
        System.out.println( "Inteligencia.pl carregado " + (q1.query() ? "succeeded" : "failed"));
          
    }
    
    public void InserirDificuldade()
    {
        q2= new Query("play",
                new Term[]{
                    new jpl.Integer (3),new Atom("black")});
        
        
        System.out.println( "Dificuldade Configurada " + (q2.query() ? "succeeded" : "failed"));
        
        
       
        
        
    }
    
    public JFrame InserirDificuldade(TelaDificuldade td)
    {
        String k = Integer.toString(td.getDificuldade());
        q3= new Query("play",
                new Term[]{
                    new Atom (k)});
        
        System.out.println(k);
        System.out.println( "Dificuldade Configurada " + (q3.query() ? "succeeded" : "failed"));
        
        td.getContentPane().remove(td.getPanel1());
        td.getContentPane().validate();
        td.repaint();
        
        return td;      
                
    }
    
    public void CountPieces()
    {
        Variable Board = new Variable("Board");
        Variable preta_score= new Variable("X");
        Variable branca_score= new Variable("Y");
        queryscores = new Query(new Compound("java_count_pieces", new Term[] {new Atom("black"),preta_score,branca_score })); 
        
        if(queryscores.hasMoreElements())
            System.out.println("Count_pieces ok");
        
        Hashtable h;
        
        h=queryscores.nextSolution();
        String str1=h.get("X").toString();
        String str2=h.get("Y").toString();
        
        scores[0]=Integer.parseInt(str1);
        scores[1]=Integer.parseInt(str2);
        
    }
    
    public String LoopBoard()
    {
        Variable Board = new Variable("Board");
        Variable preta_score= new Variable("X");
        Variable branca_score= new Variable("Y");
        
        q4 = new Query(new Compound("loop_board", new Term[] { Board })); 
       // q5 = new Query(new Compound("count_pieces", new Term[] {new Atom("black"),Board,preta_score,branca_score })); 
        
        if(q4.hasMoreElements())
            System.out.println("Loop_Board ok");
        
        /*if(q5.hasMoreElements())
            System.out.println("Count_pieces ok");*/
        
        Hashtable h = new Hashtable(); 
        
        //h=q5.nextSolution();
        /*String str1=h.get("X").toString();
        String str2=h.get("Y").toString();
        
        scores[0]=Integer.parseInt(str1);
        scores[0]=Integer.parseInt(str2);*/
        
        String k=""; 
        q4.close();
        
        
        
        h = q4.oneSolution(); 
        k = h.get("Board").toString(); 
     
        
        return k;
    }
    
    public void GameLoop(int i, int j)
    {
        termino_jogo=new Variable("X");
        jpl.Integer a= new jpl.Integer(i);
        jpl.Integer b= new jpl.Integer(j);
        
        q = new Query(new Compound("game_loop", new Term[]{a,b,termino_jogo}));
        
        System.out.print(q.oneSolution());
        
    }
        
    
    public String RetiraSimbolos(String k)
    {
        k = k.replace('(', ' ');
        k = k.replace(')', ' ');
        k = k.replace('[', ' ');
        k = k.replace(']', ' ');
        k = k.replace('.', ' ');
        k = k.replace(',', ' ');
        k = k.replaceAll("\'", "");
        k = k.replaceAll(" ", "");
        
        return k;
    }
    
    public void ConstroiTabuleiro(Tabuleiro tb, String k)
    {
        
        for(int i=0;i<320;i+=5)
        {
            int j=(i/5)%8;
            
            if(k.substring(i, i+5).equals("black"))
            {
                if(i>=0 && i<40)
                {
                    
                    switch(j)
                    {
                        case 0:
                            tb.AddPecaPreta(0, 0); break;
                        case 1:
                            tb.AddPecaPreta(1, 0); break;
                        case 2:
                            tb.AddPecaPreta(2, 0); break;
                        case 3:
                            tb.AddPecaPreta(3, 0); break;
                        case 4:
                            tb.AddPecaPreta(4, 0); break;
                        case 5:
                            tb.AddPecaPreta(5, 0); break;
                        case 6:
                            tb.AddPecaPreta(6, 0); break;
                        case 7:
                            tb.AddPecaPreta(7, 0); break;
                         
                    }
                }
                    
                if(i>=40 && i<80)
                {
                    
                    switch(j)
                    {
                        case 0:
                            tb.AddPecaPreta(0, 1); break;
                        case 1:
                            tb.AddPecaPreta(1, 1); break;
                        case 2:
                            tb.AddPecaPreta(2, 1); break;
                        case 3:
                            tb.AddPecaPreta(3, 1); break;
                        case 4:
                            tb.AddPecaPreta(4, 1); break;
                        case 5:
                            tb.AddPecaPreta(5, 1); break;
                        case 6:
                            tb.AddPecaPreta(6, 1); break;
                        case 7:
                            tb.AddPecaPreta(7, 1); break;
                         
                    }
                    
                            
                }
                
                if(i>=80 && i<120)
                {
                    
                    switch(j)
                    {
                        case 0:
                            tb.AddPecaPreta(0, 2); break;
                        case 1:
                            tb.AddPecaPreta(1, 2); break;
                        case 2:
                            tb.AddPecaPreta(2, 2); break;
                        case 3:
                            tb.AddPecaPreta(3, 2); break;
                        case 4:
                            tb.AddPecaPreta(4, 2); break;
                        case 5:
                            tb.AddPecaPreta(5, 2); break;
                        case 6:
                            tb.AddPecaPreta(6, 2); break;
                        case 7:
                            tb.AddPecaPreta(7, 2); break;
                         
                    }
                    
                            
                }
                
                if(i>=120 && i<160)
                {
                    
                    switch(j)
                    {
                        case 0:
                            tb.AddPecaPreta(0, 3); break;
                        case 1:
                            tb.AddPecaPreta(1, 3); break;
                        case 2:
                            tb.AddPecaPreta(2, 3); break;
                        case 3:
                            tb.AddPecaPreta(3, 3); break;
                        case 4:
                            tb.AddPecaPreta(4, 3); break;
                        case 5:
                            tb.AddPecaPreta(5, 3); break;
                        case 6:
                            tb.AddPecaPreta(6, 3); break;
                        case 7:
                            tb.AddPecaPreta(7, 3); break;
                         
                    }
                    
                            
                }
                
                if(i>=160 && i<200)
                {
                    
                    switch(j)
                    {
                        case 0:
                            tb.AddPecaPreta(0, 4); break;
                        case 1:
                            tb.AddPecaPreta(1, 4); break;
                        case 2:
                            tb.AddPecaPreta(2, 4); break;
                        case 3:
                            tb.AddPecaPreta(3, 4); break;
                        case 4:
                            tb.AddPecaPreta(4, 4); break;
                        case 5:
                            tb.AddPecaPreta(5, 4); break;
                        case 6:
                            tb.AddPecaPreta(6, 4); break;
                        case 7:
                            tb.AddPecaPreta(7, 4); break;
                         
                    }
                    
                            
                }
                
                if(i>=200 && i<240)
                {
                    
                    switch(j)
                    {
                        case 0:
                            tb.AddPecaPreta(0, 5); break;
                        case 1:
                            tb.AddPecaPreta(1, 5); break;
                        case 2:
                            tb.AddPecaPreta(2, 5); break;
                        case 3:
                            tb.AddPecaPreta(3, 5); break;
                        case 4:
                            tb.AddPecaPreta(4, 5); break;
                        case 5:
                            tb.AddPecaPreta(5, 5); break;
                        case 6:
                            tb.AddPecaPreta(6, 5); break;
                        case 7:
                            tb.AddPecaPreta(7, 5); break;
                         
                    }
                    
                            
                }
                
                if(i>=240 && i<280)
                {
                    
                    switch(j)
                    {
                        case 0:
                            tb.AddPecaPreta(0, 6); break;
                        case 1:
                            tb.AddPecaPreta(1, 6); break;
                        case 2:
                            tb.AddPecaPreta(2, 6); break;
                        case 3:
                            tb.AddPecaPreta(3, 6); break;
                        case 4:
                            tb.AddPecaPreta(4, 6); break;
                        case 5:
                            tb.AddPecaPreta(5, 6); break;
                        case 6:
                            tb.AddPecaPreta(6, 6); break;
                        case 7:
                            tb.AddPecaPreta(7, 6); break;
                         
                    }
                    
                            
                }
                
                if(i>=280 && i<320)
                {
                    
                    switch(j)
                    {
                        case 0:
                            tb.AddPecaPreta(0, 7); break;
                        case 1:
                            tb.AddPecaPreta(1, 7); break;
                        case 2:
                            tb.AddPecaPreta(2, 7); break;
                        case 3:
                            tb.AddPecaPreta(3, 7); break;
                        case 4:
                            tb.AddPecaPreta(4, 7); break;
                        case 5:
                            tb.AddPecaPreta(5, 7); break;
                        case 6:
                            tb.AddPecaPreta(6, 7); break;
                        case 7:
                            tb.AddPecaPreta(7, 7); break;
                         
                    }
                    
                            
                }
                
            }
            
            if(k.substring(i, i+5).equals("white"))
            {
                if(i>=0 && i<40)
                {
                    
                    switch(j)
                    {
                        case 0:
                            tb.AddPecaBranca(0, 0); break;
                        case 1:
                            tb.AddPecaBranca(1, 0); break;
                        case 2:
                            tb.AddPecaBranca(2, 0); break;
                        case 3:
                            tb.AddPecaBranca(3, 0); break;
                        case 4:
                            tb.AddPecaBranca(4, 0); break;
                        case 5:
                            tb.AddPecaBranca(5, 0); break;
                        case 6:
                            tb.AddPecaBranca(6, 0); break;
                        case 7:
                            tb.AddPecaBranca(7, 0); break;
                         
                    }
                }
                    
                if(i>=40 && i<80)
                {
                    
                    switch(j)
                    {
                        case 0:
                            tb.AddPecaBranca(0, 1); break;
                        case 1:
                            tb.AddPecaBranca(1, 1); break;
                        case 2:
                            tb.AddPecaBranca(2, 1); break;
                        case 3:
                            tb.AddPecaBranca(3, 1); break;
                        case 4:
                            tb.AddPecaBranca(4, 1); break;
                        case 5:
                            tb.AddPecaBranca(5, 1); break;
                        case 6:
                            tb.AddPecaBranca(6, 1); break;
                        case 7:
                            tb.AddPecaBranca(7, 1); break;
                         
                    }
                    
                            
                }
                
                if(i>=80 && i<120)
                {
                    
                    switch(j)
                    {
                        case 0:
                            tb.AddPecaBranca(0, 2); break;
                        case 1:
                            tb.AddPecaBranca(1, 2); break;
                        case 2:
                            tb.AddPecaBranca(2, 2); break;
                        case 3:
                            tb.AddPecaBranca(3, 2); break;
                        case 4:
                            tb.AddPecaBranca(4, 2); break;
                        case 5:
                            tb.AddPecaBranca(5, 2); break;
                        case 6:
                            tb.AddPecaBranca(6, 2); break;
                        case 7:
                            tb.AddPecaBranca(7, 2); break;
                         
                    }
                    
                            
                }
                
                if(i>=120 && i<160)
                {
                    
                    switch(j)
                    {
                        case 0:
                            tb.AddPecaBranca(0, 3); break;
                        case 1:
                            tb.AddPecaBranca(1, 3); break;
                        case 2:
                            tb.AddPecaBranca(2, 3); break;
                        case 3:
                            tb.AddPecaBranca(3, 3); break;
                        case 4:
                            tb.AddPecaBranca(4, 3); break;
                        case 5:
                            tb.AddPecaBranca(5, 3); break;
                        case 6:
                            tb.AddPecaBranca(6, 3); break;
                        case 7:
                            tb.AddPecaBranca(7, 3); break;
                         
                    }
                    
                            
                }
                
                if(i>=160 && i<200)
                {
                    
                    switch(j)
                    {
                        case 0:
                            tb.AddPecaBranca(0, 4); break;
                        case 1:
                            tb.AddPecaBranca(1, 4); break;
                        case 2:
                            tb.AddPecaBranca(2, 4); break;
                        case 3:
                            tb.AddPecaBranca(3, 4); break;
                        case 4:
                            tb.AddPecaBranca(4, 4); break;
                        case 5:
                            tb.AddPecaBranca(5, 4); break;
                        case 6:
                            tb.AddPecaBranca(6, 4); break;
                        case 7:
                            tb.AddPecaBranca(7, 4); break;
                         
                    }
                    
                            
                }
                
                if(i>=200 && i<240)
                {
                    
                    switch(j)
                    {
                        case 0:
                            tb.AddPecaBranca(0, 5); break;
                        case 1:
                            tb.AddPecaBranca(1, 5); break;
                        case 2:
                            tb.AddPecaBranca(2, 5); break;
                        case 3:
                            tb.AddPecaBranca(3, 5); break;
                        case 4:
                            tb.AddPecaBranca(4, 5); break;
                        case 5:
                            tb.AddPecaBranca(5, 5); break;
                        case 6:
                            tb.AddPecaBranca(6, 5); break;
                        case 7:
                            tb.AddPecaBranca(7, 5); break;
                         
                    }
                    
                            
                }
                
                if(i>=240 && i<280)
                {
                    
                    switch(j)
                    {
                        case 0:
                            tb.AddPecaBranca(0, 6); break;
                        case 1:
                            tb.AddPecaBranca(1, 6); break;
                        case 2:
                            tb.AddPecaBranca(2, 6); break;
                        case 3:
                            tb.AddPecaBranca(3, 6); break;
                        case 4:
                            tb.AddPecaBranca(4, 6); break;
                        case 5:
                            tb.AddPecaBranca(5, 6); break;
                        case 6:
                            tb.AddPecaBranca(6, 6); break;
                        case 7:
                            tb.AddPecaBranca(7, 6); break;
                         
                    }
                    
                            
                }
                
                if(i>=280 && i<320)
                {
                    
                    switch(j)
                    {
                        case 0:
                            tb.AddPecaBranca(0, 7); break;
                        case 1:
                            tb.AddPecaBranca(1, 7); break;
                        case 2:
                            tb.AddPecaBranca(2, 7); break;
                        case 3:
                            tb.AddPecaBranca(3, 7); break;
                        case 4:
                            tb.AddPecaBranca(4, 7); break;
                        case 5:
                            tb.AddPecaBranca(5, 7); break;
                        case 6:
                            tb.AddPecaBranca(6, 7); break;
                        case 7:
                            tb.AddPecaBranca(7, 7); break;
                         
                    }
                    
                            
                }
                
            }
            
            tb.ConstroiTabuleiro();
            
        }
    }
   
}

   
    
    