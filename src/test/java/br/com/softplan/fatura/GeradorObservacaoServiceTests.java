package br.com.softplan.fatura;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.softplan.fatura.domain.NotaFiscal;
import br.com.softplan.fatura.service.GeradorObservacaoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeradorObservacaoServiceTests {
	
	private GeradorObservacaoService geradorObservacaoService = new GeradorObservacaoService();

	@Test
	public void quandoTiverUmItem_entaoRetornaRelatorioSemSeparador() {
		NotaFiscal notaFiscal1 = new NotaFiscal();
		notaFiscal1.setCodigo(1l);
		notaFiscal1.setValor(new BigDecimal("10.00"));
		
		String observacao = geradorObservacaoService.gerarObservacao(Arrays.asList(notaFiscal1));
		System.out.println(observacao);
		Assert.assertEquals("Fatura da nota fiscal de simples remessa: " + "1 cujo valor é R$ 10,00. Total = 10,00.", observacao);
	}
	
	@Test
	public void quandoTiver2Itens_entaoRetornaRelatorioComSeparadorE() {
		NotaFiscal notaFiscal1 = new NotaFiscal();
		notaFiscal1.setCodigo(1l);
		notaFiscal1.setValor(new BigDecimal("10.00"));
		
		NotaFiscal notaFiscal2 = new NotaFiscal();
		notaFiscal2.setCodigo(2l);
		notaFiscal2.setValor(new BigDecimal("35.00"));
		
		String observacao = geradorObservacaoService.gerarObservacao(Arrays.asList(notaFiscal1, notaFiscal2));
		System.out.println(observacao);
		Assert.assertEquals("Fatura das notas fiscais de simples remessa: " + 
				"1 cujo valor é R$ 10,00 e 2 cujo valor é R$ 35,00. Total = 45,00.", observacao);
	}
	
	@Test
	public void quandoTiver3ItensOuMais_entaoRetornaRelatorioComSeparadorVirgulaAndE() {
		NotaFiscal notaFiscal1 = new NotaFiscal();
		notaFiscal1.setCodigo(1l);
		notaFiscal1.setValor(new BigDecimal("10.00"));
		
		NotaFiscal notaFiscal2 = new NotaFiscal();
		notaFiscal2.setCodigo(2l);
		notaFiscal2.setValor(new BigDecimal("35.00"));
		
		NotaFiscal notaFiscal3 = new NotaFiscal();
		notaFiscal3.setCodigo(3l);
		notaFiscal3.setValor(new BigDecimal("5.00"));
		
		NotaFiscal notaFiscal4 = new NotaFiscal();
		notaFiscal4.setCodigo(4l);
		notaFiscal4.setValor(new BigDecimal("1500.00"));
		
		NotaFiscal notaFiscal5 = new NotaFiscal();
		notaFiscal5.setCodigo(5l);
		notaFiscal5.setValor(new BigDecimal("0.30"));
		
		String observacao = geradorObservacaoService.gerarObservacao(
				Arrays.asList(notaFiscal1, notaFiscal2, notaFiscal3, notaFiscal4, notaFiscal5));
		System.out.println(observacao);
		Assert.assertEquals("Fatura das notas fiscais de simples remessa: " + 
				"1 cujo valor é R$ 10,00, "
				+ "2 cujo valor é R$ 35,00, "
				+ "3 cujo valor é R$ 5,00, "
				+ "4 cujo valor é R$ 1.500,00 e "
				+ "5 cujo valor é R$ 0,30. "
				+ "Total = 1.550,30.", observacao);
	}

}
