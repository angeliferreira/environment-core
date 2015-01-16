package br.com.lemao.environment.junit;

import java.util.ArrayList;
import java.util.List;

import org.junit.rules.TestRule;
import org.junit.runners.model.InitializationError;

public class EnvironmentInMemoryRunner extends EnvironmentRunner {

	public EnvironmentInMemoryRunner(Class<?> klass)throws InitializationError {
		super(klass);
	}
	
	@Override
	protected List<TestRule> getTestRules(Object target) {
		List<TestRule> testRules = new ArrayList<TestRule>();
		testRules.add(new InMemoryRule()); 
		return testRules;
	}

}
