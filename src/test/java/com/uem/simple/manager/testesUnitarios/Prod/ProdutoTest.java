package com.uem.simple.manager.testesUnitarios.Prod;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import com.uem.simple.manager.model.Produto;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;


public class ProdutoTest {
	
	private void assertThat(String string, Matcher<String> matchesPattern) {
	}


	@Test
	public void testPrecoBruto() throws Exception {
		Produto produtoTeste = new Produto();
		BigDecimal num = new BigDecimal("450.33");
		produtoTeste.setPrecoBruto(num);

		assertThat(produtoTeste.getPrecoBruto().toString(), Matchers.matchesPattern("\\d.\\d{2}"));
	}
	


	@Test
	public void testPrecoLiquido() throws Exception {
		Produto produtoTeste = new Produto();
		BigDecimal num = new BigDecimal("45078.33");
		produtoTeste.setPrecoLiquido(num);

		assertThat(produtoTeste.getPrecoLiquido().toString(), Matchers.matchesPattern("\\d.\\d{2}"));
	}

	@Test
	public void testNome() throws Exception {
		Produto produtoTeste = new Produto();
		produtoTeste.setNome("Cadeira Gamer super");

		assertThat(produtoTeste.getNome(),Matchers.matchesPattern("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$"));
	}


}
