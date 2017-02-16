package empacotamento;

import java.util.ArrayList;
import java.util.List;

public class Empacotador {

	public static List<String> empacotar(String ovos) {
		if (ovos.length() < 4)
			throw new RuntimeException(
					"Deveria ter pelo menos 4 ovos, não apenas "+ovos.length()+".");
		
		if(ovos.length() % 2 == 0 ){
			int qtdeOvosLinha = ovos.length() / 2;
			String conteudo = "0";
			for (int i = 0; i < qtdeOvosLinha - 1; i++) {
				conteudo += " 0";
			}
			 
			conteudo += "\n";
			conteudo = conteudo+conteudo;
			
			ArrayList<String> caixa = new ArrayList<>(); 
			caixa.add(conteudo);
			
			for(int i = 0; i < qtdeOvosLinha -1; i++){
				qtdeOvosLinha = ovos.length()/2;
				conteudo = "0"; 
				ArrayList<String> caixa02 = new ArrayList<>();
				
			}
			
			return caixa;
		}
			
		return null;
	}

}
