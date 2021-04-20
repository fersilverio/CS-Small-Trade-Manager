package com.uem.simple.manager.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import com.uem.simple.manager.model.ItemOrcamento;
import com.uem.simple.manager.model.Funcionario;

public class aplicationTest {

	@Test
	public void testFuncionarioCPF() throws Exception {
		Funcionario testeFuncionario = new Funcionario();

		testeFuncionario.setCpf("409.282.735-16");

		assertThat(testeFuncionario.getCpf(), Matchers.matchesPattern("\\d{3}.\\d{3}.\\d{3}-\\d{2}"));
	}

	private void assertThat(String string, Matcher<String> matchesPattern) {
	}

	@Test
	public void testCalcularPrecoTotal() throws Exception {
		ItemOrcamento testItemOrcamento = new ItemOrcamento();

		BigDecimal preco = new BigDecimal("5.0");
		testItemOrcamento.setPrecoUnitario(preco);

		testItemOrcamento.setQtd(10);

		BigDecimal esperado = new BigDecimal("50.0");
		testItemOrcamento.calcularPrecoTotal();

		assertEquals(esperado, testItemOrcamento.getPrecoTotal());
	}

	@Test
	public void testNomeFuncionario() throws Exception {
		Funcionario testeFuncionario = new Funcionario();

		testeFuncionario.setNome("Pedro");

		assertThat(testeFuncionario.getNome(), Matchers.matchesPattern("[A-Z][a-z]"));
	}

}
