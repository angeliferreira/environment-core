package br.com.lemao.environment.junit;

import java.util.List;

import org.junit.rules.TestRule;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class EnvironmentRunner extends BlockJUnit4ClassRunner {

	public EnvironmentRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	protected List<TestRule> getTestRules(Object target) {
		List<TestRule> testRules = super.getTestRules(target);
		testRules.add(new EnvironmentRule());
		return testRules;
	}
}
