package calculadora;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;


@SuppressWarnings("serial")
public class Botao  extends JButton{
	
	public Botao(String label, Color cor) {
		setText(label);
		setBackground(cor);
		setFont(new Font ("courier", Font.PLAIN,25));
		setOpaque(true);
		setForeground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

}
