package calculadora;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

	private static final Memoria instancia = new Memoria();
	private final List<MemoriaObs> observadores = new ArrayList<>();

	private TipoComando ultimaOperacao = null;
	private boolean substituir = false;
	private String text = "";
	private String textBuffer = "";

	private Memoria() {

	}

	public static Memoria getInstancia() {
		return instancia;
	}

	public void adicionarObs(MemoriaObs o) {
		observadores.add(o);
	}

	public String getText() {
		return text.isEmpty() ? "0" : text;
	}

	public void processarComando(String comando) {

		TipoComando tipoComando = detectarTipocomando(comando);

		if (tipoComando == null) {
			return;
		} else if (tipoComando == TipoComando.ZERAR) {
			text = "";
			textBuffer = "";
			substituir = false;
			ultimaOperacao = null;
		} else if (tipoComando == TipoComando.NUMERO || tipoComando == TipoComando.VIRGULA) {
			text = substituir ? comando : text + comando;
			substituir = false;
		} else {
			substituir = true;
			text = obterResult();
			textBuffer = text;
			ultimaOperacao = tipoComando;
		}

		observadores.forEach(o -> o.valorAlterado(getText()));

	}

	private String obterResult() {

		if (ultimaOperacao == null || ultimaOperacao == TipoComando.IGUAL) {
			return text;
		}
		double numeroBuffer = Double.parseDouble(textBuffer.replace(",", "."));
		double numeroAtual = Double.parseDouble(text.replace(",", "."));

		double resultado = 0;

		if (ultimaOperacao == TipoComando.SOMA) {
			resultado = numeroBuffer + numeroAtual;
		} else if (ultimaOperacao == TipoComando.SUB) {
			resultado = numeroBuffer - numeroAtual;
		}else if (ultimaOperacao == TipoComando.MULTIPLICAO) {
			resultado = numeroBuffer * numeroAtual;
		}else if (ultimaOperacao == TipoComando.DIVISAO) {
			resultado = numeroBuffer / numeroAtual;
		}
		
		String resultadoString = Double.toString(resultado).replace(".", ",");
		boolean inteiro = resultadoString.endsWith(",0");
		return inteiro ? resultadoString.replace(",0","") : resultadoString;
	}

	private TipoComando detectarTipocomando(String comando) {
		if (text.isEmpty() && comando == "0") {
			return null;
		}
		try {
			Integer.parseInt(comando);
			return TipoComando.NUMERO;
		} catch (NumberFormatException e) {
			if ("AC".equals(comando)) {
				return TipoComando.ZERAR;
			} else if ("/".equals(comando)) {
				return TipoComando.DIVISAO;
			} else if ("*".equals(comando)) {
				return TipoComando.MULTIPLICAO;
			} else if ("+".equals(comando)) {
				return TipoComando.SOMA;
			} else if ("-".equals(comando)) {
				return TipoComando.SUB;
			} else if ("=".equals(comando)) {
				return TipoComando.IGUAL;
			} else if (",".equals(comando) && !text.contains(",")) {
				return TipoComando.VIRGULA;
			}

		}
		return null;
	}
}
