package otello;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class OthelloMain
{
	public static void main(String []args)
	{
		Tabuleiro tb= new Tabuleiro();
		tb.InicializaGui();
		tb.ConfigFrame();
		tb.MostrarTab();
                
                Prolog p=new Prolog();
                p.IniciarOtello();
                
		
	}
	
	
	
}