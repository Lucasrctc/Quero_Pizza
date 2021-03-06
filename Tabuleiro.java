package otello;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

public class Tabuleiro
{
	private final JPanel gui=new JPanel(new BorderLayout(3,3));
	private JButton[][] pecas=new JButton[8][8];
	private JPanel GridPanel;
	private final JLabel message=new JLabel("Othello está pronto para jogar!");
        private JLabel BrancaScore= new JLabel();
        private JLabel PretaScore=new JLabel();
	private static final String COLS="ABCDEFGH";
	private JFrame janela;
        private JToolBar tools;
        private int posicao[]=new int [2];
        
        
        
        
        public int getX()
        {
            return posicao[0];
        }
        
        public int getY()
        {
            return posicao[1];
        }
        
	Tabuleiro(JFrame td)
	{
		InicializaGui();
		ConfigFrame(td);
	}
	
	public void ConfigFrame(JFrame td)
	{
		janela=td;
		janela.add(gui);	
	}
	
	public final void InicializaGui()
	{
		gui.setBorder(new EmptyBorder(5,5,5,5));
		
                
                tools=new JToolBar();
		tools.setFloatable(false);
		gui.add(tools,BorderLayout.PAGE_START);
                
                
                AddButtonNew();
		AddToolSeparator();
		AddToolMessage();
                AddToolSeparator();
                ConfigurePretaScore();
                AddToolPretaScore();
                AddToolSeparator();
                ConfigureBrancaScore();
                AddToolBrancaScore();
		
		GridPanel=new JPanel(new GridLayout(0,9));
		GridPanel.setBorder(new LineBorder(Color.BLACK));
		gui.add(GridPanel);
		
		//Cria os quadrados do Tabuleiro
		CriaQuadrados();
                
                ConstroiTabuleiro();
		
	}
		
	public final JComponent getGridPanel()
	{
		return GridPanel;
	}
	
	public final JComponent getgui()
	{
		return gui;	
	}
	
	public void MostrarTab()
	{
                
                janela.setPreferredSize(new Dimension(525,429));
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//janela.setLocationByPlatform(true);
		janela.pack();
		janela.setMinimumSize(janela.getSize());
		janela.setVisible(true);
                
                
              
	}
	
	
	public JButton CriaButton()
        {
            Insets BotaoMargem=new Insets(0,0,0,0);
            JButton b=new JButton();
            b.setMargin(BotaoMargem);
            b.setBackground(new Color(230, 230, 250));
            
            
            
            return b;
            
        }
        
        public void PreenchePrimeiraLinha()
        {
            for(int ii=0;ii<8;ii++)
		{
			JLabel label=new JLabel(COLS.substring(ii,ii+1),SwingConstants.CENTER);
			GridPanel.add(label);
			
		}
        }
        
        public void PreencheLinhas()
        {
            for(int ii=0;ii<8;ii++)
		{
			for(int jj=0;jj<8;jj++)
			{
				switch(jj)
				{
				case 0: 
					PreencheColunaLetras(ii);
				default:
					AdicionaPecas(jj,ii);
				}
			}
		}
        }
        
        public void CriaQuadrados()
        {
            
            for(int ii=0; ii<pecas.length;ii++)
		{
			for(int jj=0;jj<pecas[ii].length;jj++)
			{   
                                   pecas[ii][jj]=CriaButton();     
                                   AddComportamentoPosicao(pecas[ii][jj],ii,jj);
                                   
                                   
                                   			
			}
		}
        }
        
        public void AddComportamentoPosicao(JButton button, int ii, int jj)
        {
            
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                            posicao[0]=jj;
                            posicao[1]=ii;
                            Jogo.TrocaConfirmaJogador();
 
                            
                        }
			});
        }
        
        public void PreencheColunaLetras(int ii)
        {
            GridPanel.add(new JLabel(""+(ii+1),SwingConstants.CENTER));
        }
        
        public void AdicionaPecas(int jj, int ii)
        {
            GridPanel.add(pecas[jj][ii]);
        }
        
        public JButton PecaPreta(JButton b)
        {
            ImageIcon icon = new ImageIcon(
                                new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB));
            b.setIcon(icon);
            b.setBackground(Color.BLACK);
            return b;
        }
        
        public JButton PecaBranca(JButton b)
        {
            ImageIcon icon = new ImageIcon(
                                new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB));
            b.setIcon(icon);
            b.setBackground(Color.WHITE);
            return b;
        }
        
        public void AddButtonNew()
        {
            
            tools.add(new JButton("Novo Jogo"));
        }
        
        public void ConfigureBrancaScore()
        {
            //int i= p.getScoreWhite();
            //String str=String.valueOf(i);
            BrancaScore.setText("BRANCA");
        }
        
        public void ConfigurePretaScore()
        {
            //int i= p.getScoreBlack();
            //String str=String.valueOf(i);
            //PretaScore.setText(str);
            PretaScore.setText("PRETA");
            
        }
        
        public void AddToolBrancaScore()
        {
            
            tools.add(BrancaScore);
        }
        
        public void AddToolPretaScore()
        {
            
            tools.add(PretaScore);
        }
        
        public void AddToolSeparator()
        {
            tools.addSeparator();
        }
        
        public void AddToolMessage()
        {
            tools.add(message);
        }
        
        public void AddPecaPreta(int i, int j)
        {
            pecas[i][j]=PecaPreta(CriaButton());
            AddComportamentoPosicao(pecas[i][j],i,j);
        }
        
         public void AddPecaBranca(int i, int j)
        {
            pecas[i][j]=PecaBranca(CriaButton());
            AddComportamentoPosicao(pecas[i][j],i,j);
            	
        }
         
        public void ConstroiTabuleiro()
        {
            GridPanel.removeAll();
            
            //1º Quadrado onde não há nada escrito
		GridPanel.add(new JLabel(""));
                
            //Preenche a primeira linha do GridPanel
                PreenchePrimeiraLinha();
                
            //Preenche o restante das linhas
                PreencheLinhas();
                
            GridPanel.repaint();
            GridPanel.validate();
            
        }
        
}