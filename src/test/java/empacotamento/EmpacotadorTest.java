package empacotamento;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;

import java.util.List;

import org.junit.Test;

public class EmpacotadorTest {

	@Test
	public void naoEmpacotaUmOvo() {
		try {
			Empacotador.empacotar("0");
			fail("Deveria ter reclamado");
		} catch (RuntimeException e) {
			assertThat(e.getMessage()).isEqualTo(
					"Deveria ter pelo menos 4 ovos, não apenas 1.");
		}
	}

	@Test
	public void naoEmpacotaDoisOvos() {
		try {
			Empacotador.empacotar("00");
			fail("Deveria ter reclamado");
		} catch (RuntimeException e) {
			assertThat(e.getMessage()).isEqualTo(
					"Deveria ter pelo menos 4 ovos, não apenas 2.");
		}
	}

	@Test
	public void naoEmpacotaTresOvos() {
		try {
			Empacotador.empacotar("000");
			fail("Deveria ter reclamado");
		} catch (RuntimeException e) {
			assertThat(e.getMessage()).isEqualTo(
					"Deveria ter pelo menos 4 ovos, não apenas 3.");
		}
	}

	@Test
	public void empacotarCaixaQuatroOvos() {
		List<String> empacotar = Empacotador.empacotar("0000");
		assertThat(empacotar).hasSize(1);

		assertThat(empacotar.get(0)).isEqualTo(
				"0 0\n" + 
				"0 0\n");
	}
		
	@Test
	public void empacotarCaixaSeisOvos() {
		List<String> empacotar = Empacotador.empacotar("000000");
		assertThat(empacotar).hasSize(1);

		assertThat(empacotar.get(0)).isEqualTo(
				"0 0 0\n" + 
				"0 0 0\n");
	}
	
	@Test
	public void empacotarCaixaOitoOvos() {
		List<String> empacotar = Empacotador.empacotar("00000000");
		assertThat(empacotar).hasSize(1);

		assertThat(empacotar.get(0)).isEqualTo(
				"0 0 0 0\n" + 
				"0 0 0 0\n");
	}
	
	@Test
	public void empacotarCaixaDezOvos() {
		List<String> empacotar = Empacotador.empacotar("0000000000");
		assertThat(empacotar).hasSize(2);

		assertThat(empacotar.get(0)).isEqualTo(
				"0 0 0 0 0\n" + 
				"0 0 0 0 0\n");
		
		assertThat(empacotar.get(1)).isEqualTo(
				"0 0 0\n" + 
				"0 0 0\n" +
				"-----\n" +
				"0 0\n" + 
				"0 0\n");
	}
	
	@Test
	public void naoEmpacotarQuantidadeInsuficienteParaEmpacotamentoPerfeito() {
		try {
			Empacotador.empacotar("00000");
			fail("Deveria ter reclamado");
		} catch (RuntimeException e) {
			assertThat(e.getMessage()).isEqualTo(
					"5 ovos não dá empacotamento perfeito.");
		}
	}
	
	@Test
	public void empacotarCaixaComDozeOvos() {
		List<String> empacotar = Empacotador.empacotar("000000000000");
		assertThat(empacotar).hasSize(3);

		assertThat(empacotar.get(0)).isEqualTo(
				"0 0 0 0 0 0\n" + 
				"0 0 0 0 0 0\n");

		assertThat(empacotar.get(1)).isEqualTo(
				"0 0 0 0\n" + 
				"0 0 0 0\n" + 
				"0 0 0 0\n");

		assertThat(empacotar.get(2)).isEqualTo(
				"0 0 0 0\n" + 
				"0 0 0 0\n" + 
				"-----\n" +
				"0 0\n" +
				"0 0\n");
	}
	
	@Test
	public void aceitarApenasOvosDeTipoCorreto() {
		try {
			Empacotador.empacotar("ooo0O");
			fail("Deveria ter reclamado");
		} catch (RuntimeException e) {
			assertThat(e.getMessage()).isEqualTo(
					"O conjunto de ovos \"ooo0O\" contém ovos não suportados.");
		}
	}
		

}
