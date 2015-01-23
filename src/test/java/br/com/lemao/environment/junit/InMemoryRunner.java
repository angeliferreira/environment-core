package br.com.lemao.environment.junit;

import java.util.ArrayList;
import java.util.List;

import org.junit.rules.TestRule;
import org.junit.runners.model.InitializationError;

public class InMemoryRunner extends EnvironmentRunner {

	public InMemoryRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}
	
	@Override
	protected List<TestRule> getTestRules(Object target) {
		List<TestRule> rules = new ArrayList<TestRule>();
		rules.add(new InMemoryRule());
		return rules;
	}
}
