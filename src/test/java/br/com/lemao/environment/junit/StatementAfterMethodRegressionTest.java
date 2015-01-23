package br.com.lemao.environment.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class StatementAfterMethodRegressionTest {
	
	@Rule
	public LocalRule rule = new LocalRule();
	
	@Test(expected = RuntimeException.class)
	public void afterTestStatementWasExecuted() {
		assertFalse(LocalStatement.executed);
		throw new RuntimeException("Regression to #after that was not executed on test error/exception");
	}
	
	@AfterClass
	public static void tearDown(){
		assertTrue(LocalStatement.executed);
	}

	private static final class LocalStatement extends EnvironmentStatement {
		
		public static boolean executed = false;
		
		public LocalStatement(Statement statement, Description description) {
			super(statement, description);
		}
		
		@Override
		protected void after() {
			executed = true;
			super.after();
		}
	}
	
	private static final class LocalRule implements TestRule {

		public Statement apply(Statement base, Description description) {
			return new LocalStatement(base, description);
		}
	}
}
