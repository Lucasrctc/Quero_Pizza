package otello;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class OthelloMain
{
	public static void main(String []args)
	{
		/*Tabuleiro tb= new Tabuleiro();
		tb.InicializaGui();
		tb.ConfigFrame();
		tb.MostrarTab();*/
                
                Prolog p=new Prolog();
                p.IniciarOtello();
               
            
            TelaDificuldade td=new TelaDificuldade();
            td.ConfigFrame();
            
            do
            {
            System.out.println("Luan");
            }
            while(td.getDificuldade()==0);
            
            p.InserirDificuldade(td);        
		
	}
	
	
	
}