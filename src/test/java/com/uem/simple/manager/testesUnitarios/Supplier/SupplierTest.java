package com.uem.simple.manager.testesUnitarios.Supplier;

import org.junit.jupiter.api.Test;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CNPJValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.uem.simple.manager.model.Fornecedor;



public class SupplierTest {
	
	@Test
	public void testCNPJ() throws Exception {
		Fornecedor supplierTeste = new Fornecedor();
        String cnpj = "08030186000133"; //valido
        String cnpj2 = "08.030.186/0001-33"; //valido
        String cnpj3 = "8.03.186/0001-33"; //invalido(digits)
        String cnpj4 = "44444444444444"; //invalido (check digits)
		CNPJValidator validadorCNPJ = new CNPJValidator();
		supplierTeste.setCnpj(cnpj);
        List<ValidationMessage> validationMessages = validadorCNPJ.invalidMessagesFor(cnpj);
        List<ValidationMessage> validationMessages2 = validadorCNPJ.invalidMessagesFor(cnpj2);
        List<ValidationMessage> validationMessages3 = validadorCNPJ.invalidMessagesFor(cnpj3);
        List<ValidationMessage> validationMessages4 = validadorCNPJ.invalidMessagesFor(cnpj4);
        System.out.println(validationMessages);
        System.out.println(validationMessages2);
        System.out.println(validationMessages3);
        System.out.println(validationMessages4);
	}

    @Test
    public void testNomeFantasia() throws Exception {
		Fornecedor supplierTeste = new Fornecedor();
        List<String> names = new ArrayList<String>();
        names.add("123"); //invalido
        names.add("aaaaaa");
        names.add("123123123"); //invalido
        names.add("NomeValido");
        
        String regex = "^[A-Za-z]{1,20}$";
        Pattern pattern = Pattern.compile(regex);

        for (String name : names)
        {
            supplierTeste.setNomeFantasia(name);
            java.util.regex.Matcher matcher = pattern.matcher(supplierTeste.getNomeFantasia());
            System.out.println(matcher.matches());
        }
	}

}

