package calculadora;



import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Teclado extends JPanel implements ActionListener  {
	
	private final Color CINZA_ESCURO = new Color(68,68,68);
	private final Color CINZA_CLARO = new Color(99,99,99);
	private final Color LARANJA = new Color(242,163,60);
	
	public Teclado() {
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		setLayout(layout);
		
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		
		adicionarButton("AC",CINZA_ESCURO,c,0,0);
		adicionarButton("+/-",CINZA_ESCURO,c,1,0);
		adicionarButton("%",CINZA_ESCURO,c,2,0);
		adicionarButton("/",LARANJA,c,3,0);
		
		adicionarButton("7",CINZA_CLARO,c,0,1);
		adicionarButton("8",CINZA_CLARO,c,1,1);
		adicionarButton("9",CINZA_CLARO,c,2,1);
		adicionarButton("*",LARANJA,c,3,1);
		
		adicionarButton("4",CINZA_CLARO,c,0,2);
		adicionarButton("5",CINZA_CLARO,c,1,2);
		adicionarButton("6",CINZA_CLARO,c,2,2);
		adicionarButton("-",LARANJA,c,3,2);
		
		adicionarButton("1",CINZA_CLARO,c,0,3);
		adicionarButton("2",CINZA_CLARO,c,1,3);
		adicionarButton("3",CINZA_CLARO,c,2,3);
		adicionarButton("+",LARANJA,c,3,3);
		
		c.gridwidth = 2;
		adicionarButton("0",CINZA_CLARO,c,0,4);
		c.gridwidth = 1;
		adicionarButton(",",CINZA_CLARO,c,2,4);
		adicionarButton("=",LARANJA,c,3,4);
		
		
		
		
		
	}

	private void adicionarButton(String string, Color cor, GridBagConstraints c, int x, int y) {
		c.gridx = x;
		c.gridy = y;
		Botao botao = new Botao(string,cor);
		botao.addActionListener(this);
		add(botao,c);
		
		
	}
	public void actionPerformed(ActionEvent e ) {
		if(e.getSource() instanceof JButton) {
			JButton botao = (JButton) e.getSource();
			Memoria.getInstancia().processarComando(botao.getText());
		}
		
		
	}
	
}
