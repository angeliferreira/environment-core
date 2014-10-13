package br.com.lemao.environment;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class MyRule implements TestRule {

	public Statement apply(Statement base, Description description) {
		return new MyStatement(base, description);
	}

}
