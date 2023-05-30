package calculadora;



import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Display extends JPanel  implements MemoriaObs{
	
	private JLabel label = new JLabel("12345,34");
	
	public Display() {
		Memoria.getInstancia().adicionarObs(this);
		setBackground(new Color(46,49,50));
		label = new JLabel(Memoria.getInstancia().getText());
		label.setForeground(Color.WHITE);
		label.setFont(new Font ("courier", Font.PLAIN,30));
		
		setLayout(new FlowLayout(FlowLayout.RIGHT, 11,22));
		add(label);
	}
	
	public void valorAlterado(String novoValor) {
		label.setText(novoValor);
	}
	
	
}
