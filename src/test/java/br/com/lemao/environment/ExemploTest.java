package br.com.lemao.environment;

import org.junit.Rule;
import org.junit.Test;

import br.com.lemao.environment.annotation.GivenEnvironment;

public class ExemploTest {

	@Rule
	public MyRule myRule = new MyRule();

	@Test
	@GivenEnvironment(EnvironmentMaroto.class)
	public void testeMaroto() {
		System.out.println("br.com.lemao.environment.ExemploTest.testeMaroto()");
	}

}
