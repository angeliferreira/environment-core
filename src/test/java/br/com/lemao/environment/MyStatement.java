package br.com.lemao.environment;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import br.com.lemao.environment.junit.EnvironmentStatement;

public class MyStatement extends EnvironmentStatement {

	public MyStatement(Statement statement, Description description) {
		super(statement, description);
	}

	@Override
	protected void before() {
		System.out.println("br.com.lemao.environment.MyStatement.before()");
		super.before();
	}
	
	@Override
	protected void after() {
		System.out.println("br.com.lemao.environment.MyStatement.after()");
		super.after();
	}

}
