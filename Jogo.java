package otello;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

public class Jogo
{
    private JFrame frame;
    private Prolog p;
    private Tabuleiro tb;
    private String k;
    private static Boolean confirmaJogador;
    Timer timer;
    
    
    public Boolean GetConfirmaJogador()
    {
        return confirmaJogador;
    }
    
    public static void TrocaConfirmaJogador()
    {
        if(confirmaJogador==true)
            confirmaJogador=false;
        else
            confirmaJogador=true;
    }
    
    public Jogo()
    {
       frame=new JFrame("Othello");
       p=new Prolog();
       tb=new Tabuleiro(frame);
       
       confirmaJogador=false;
    }
    
    public void ConfiguraJogo()
    {        
        p.IniciarOtello();
        p.InserirDificuldade();
        
    }
    public void TelaInicial()
    {
        k=p.LoopBoard();
        k=p.RetiraSimbolos(k);
        p.ConstroiTabuleiro(tb, k);    
        tb.MostrarTab();
    }
    
    public void JogadorJogar()
    {
        int i=0,j=0;
        
        
        do
        {
            System.out.print("");
        }
        while(confirmaJogador==false);
        
        i=tb.getX();
        j=tb.getY();
        
        p.GameLoop(i, j);
        k=p.LoopBoard();
        k=p.RetiraSimbolos(k);
        
        p.ConstroiTabuleiro(tb, k);
        
        Jogo.TrocaConfirmaJogador();
        
    }
    
    class TimerInteligenciaJogar extends TimerTask
    {
        public void run()
        {        
            p.GameLoop(1,1);
            k=p.LoopBoard();
            k=p.RetiraSimbolos(k);
            p.ConstroiTabuleiro(tb, k);
        }
        
    }
    
    public void ConfiguraTimer(int seconds)
    {
        timer=new Timer();
        timer.schedule(new TimerInteligenciaJogar(), seconds*1000);
    }
    
    public void InteligenciaJogar()
    {
        ConfiguraTimer(1);
        
    }
    
    public void Jogar()
    {
        
        while(true)
        {
            
            
            JogadorJogar();
            InteligenciaJogar();
            
        }
        
        
    }
    
}