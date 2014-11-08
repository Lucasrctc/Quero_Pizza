package otello;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class OthelloMain
{
	public static void main(String []args)
	{
	
            /*Prolog p=new Prolog();
            p.IniciarOtello();
                
         
            TelaDificuldade td=new TelaDificuldade();
            td.ConfigFrame();
            
            do
            {System.out.print("");}
            while(td.getDificuldade()==0);
         
            JFrame j=p.InserirDificuldade(td);
            
            String k=p.LoopBoard();
            System.out.println(p.RetiraSimbolos(k));*/
   
            
            String inicio="emptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyblackwhiteemptyemptyemptyemptyemptyemptywhiteblackemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyemptyempty";
            JFrame frame= new JFrame("Teste");
            Tabuleiro tb = new Tabuleiro(frame);
		tb.InicializaGui();
                Prolog.ConstroiTabuleiro(tb, inicio);
                
                
		
                
		tb.MostrarTab();
		
	}
	
	
	
}