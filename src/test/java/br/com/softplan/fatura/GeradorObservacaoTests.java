package br.com.softplan.fatura;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.softplan.fatura.service.GeradorObservacao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeradorObservacaoTests {
	
	public GeradorObservacao geradorObservacao = new GeradorObservacao();

	@Test
	public void quandoTiverUmItem_entaoRetornaCodigo1() {
		Integer item1 = 1;
		String observacao = geradorObservacao.geraObservacao(Arrays.asList(item1));
		System.out.println(observacao);
		Assert.assertEquals("Fatura da nota fiscal de simples remessa: " + "1" + ".", observacao);
	}
	
	@Test
	public void quandoTiverDoisItens_entaoRetornaCodigo1e2() {
		Integer item1 = 1;
		Integer item2 = 2;
		String observacao = geradorObservacao.geraObservacao(Arrays.asList(item1, item2));
		System.out.println(observacao);
		Assert.assertEquals("Fatura das notas fiscais de simples remessa: " + "1 e 2" + ".", observacao);
	}
	
	@Test
	public void quandoTiverTresItens_entaoRetornaCodigo12e3() {
		Integer item1 = 1;
		Integer item2 = 2;
		Integer item3 = 3;
		String observacao = geradorObservacao.geraObservacao(Arrays.asList(item1, item2, item3));
		System.out.println(observacao);
		Assert.assertEquals("Fatura das notas fiscais de simples remessa: " + "1, 2 e 3" + ".", observacao);
	}
	
	@Test
	public void quandoTiverItensNegativos_entaoRetornaCodigo12e3() {
		Integer item1 = -1;
		Integer item2 = -2;
		Integer item3 = 3;
		String observacao = geradorObservacao.geraObservacao(Arrays.asList(item1, item2, item3));
		System.out.println(observacao);
		Assert.assertEquals("Fatura das notas fiscais de simples remessa: " + "-1, -2 e 3" + ".", observacao);
	}
	
	@Test
	public void quandoCodigoItemExcedeLimite_entaoRetornaCodigoNegativo() {
		Integer item1 = Integer.MAX_VALUE;
		Integer item2 = Integer.MAX_VALUE + 1;
		String observacao = geradorObservacao.geraObservacao(Arrays.asList(item1, item2));
		System.out.println(observacao);
		Assert.assertEquals("Fatura das notas fiscais de simples remessa: " + item1 + " e -2147483648" + ".", observacao);
	}
	
	@Test(expected = ClassCastException.class)
	public void quandoCodigoItemNaoInteger_entaoRetornaException() {
		String item1 = "7";
		String item2 = "3";
		String observacao = geradorObservacao.geraObservacao(Arrays.asList(item1, item2));
		System.out.println(observacao);
		Assert.assertEquals("Fatura das notas fiscais de simples remessa: " + item1 + " e " + item2 + ".", observacao);
	}
	
	@Test(expected = NullPointerException.class)
	public void quandoListaItemNull_entaoRetornaNullPointerException() {
		geradorObservacao.geraObservacao(null);
	}

}
