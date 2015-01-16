package br.com.lemao.environment.junit;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class InMemoryRule implements TestRule {

	public Statement apply(Statement base, Description description) {
		return new InMemoryStatement(base, description);
	}

}
