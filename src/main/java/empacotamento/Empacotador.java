package empacotamento;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Empacotador {

	public static List<String> empacotar(String ovos) {
		if (ovos.length() < 4)
			throw new RuntimeException("Deveria ter pelo menos 4 ovos, não apenas " + ovos.length() + ".");
		
		if (!ovos.matches("[0]*"))
			throw new RuntimeException(String.format("O conjunto de ovos \"%s\" contém ovos não suportados.", ovos));
		
		List<String> pacotes = montarPacotes(ovos);

		if (pacotes.isEmpty())
			throw new RuntimeException(String.format("%d ovos não dá empacotamento perfeito.", ovos.length()));
		
		return pacotes;
	}

	private static List<String> montarPacotes(String ovos) {
		int quantidadeOvos = ovos.length();
		
		List<String> pacotesMontados = new ArrayList<>();
		Set<String> pacotesCriados = new HashSet<>();
		
		for (int linhasDeOvos = 2; linhasDeOvos < quantidadeOvos; linhasDeOvos++) {
			int ovosPorLinha = quantidadeOvos / linhasDeOvos;
			int ovosRestantes = quantidadeOvos % linhasDeOvos;
			
			String dimensaoEmpacotamento = criarDimensaoEmpacotamento(linhasDeOvos, ovosPorLinha);
			if (ovosRestantes == 0 && !pacotesCriados.contains(dimensaoEmpacotamento)) {
				pacotesMontados.add(criarPacote(ovos, linhasDeOvos, ovosPorLinha));
				pacotesCriados.add(dimensaoEmpacotamento);
			}
			
			if (ovosRestantes >= 4) {
				Optional<String> multiplosPacotes = montarPacoteComMultiplosPacotes(ovos, linhasDeOvos, ovosPorLinha);
				if (multiplosPacotes.isPresent())
					pacotesMontados.add(multiplosPacotes.get());
			}
		}
		
		return pacotesMontados;
	}

	private static Optional<String> montarPacoteComMultiplosPacotes(String ovos, int linhasDeOvos, int ovosPorLinha) {
		int limiteOvosParaPrimeiraCaixa = ovosPorLinha * linhasDeOvos;
		
		String ovosPrimeiraCaixa = ovos.substring(0, limiteOvosParaPrimeiraCaixa);
		String ovosSegundaCaixa = ovos.substring(limiteOvosParaPrimeiraCaixa);
		
		List<String> pacotesPrimeiraCaixa = montarPacotes(ovosPrimeiraCaixa);
		List<String> pacotesSegundaCaixa = montarPacotes(ovosSegundaCaixa);
		
		if (!pacotesPrimeiraCaixa.isEmpty() && !pacotesSegundaCaixa.isEmpty()) {
			String empacotamentoMultiplo = pacotesPrimeiraCaixa.get(0) + "-----\n" + pacotesSegundaCaixa.get(0);
			return Optional.of(empacotamentoMultiplo);
		}
		
		return Optional.empty();
	}

	private static String criarDimensaoEmpacotamento(int linhasDeOvos, int ovosPorLinha) {
		return String.format("%dx%d", Math.min(linhasDeOvos, ovosPorLinha), Math.max(linhasDeOvos, ovosPorLinha));
	}

	private static String criarPacote(String ovos, int linhasDeOvos, int ovosPorLinha) {
		String[] ovosParaLinha = organizarOvosParaLinha(ovos, linhasDeOvos, ovosPorLinha);
		
		String pacote = "";
		
		for (String linha : ovosParaLinha)
			pacote += separarOvos(linha) + "\n";
		
		return pacote;
	}

	private static String separarOvos(String linha) {
		String ovosSeparados = "";
		
		for (int i = 0; i < linha.length(); i++)
			ovosSeparados += " " + linha.charAt(i);
		
		return ovosSeparados.trim();
	}

	private static String[] organizarOvosParaLinha(String ovos, int linhasDeOvos, int ovosPorLinha) {
		String[] ovosOrganizados = new String[linhasDeOvos];
		String ovosRestantes = ovos;
		
		for (int i = 0; i < linhasDeOvos; i++) {
			ovosOrganizados[i] = ovosRestantes.substring(0, ovosPorLinha);
			ovosRestantes = ovosRestantes.substring(ovosPorLinha);
		}
		
		return ovosOrganizados;
	}
	
	public static void main(String[] args) {
		System.out.println(separarOvos("0000"));
	}


}
