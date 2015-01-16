package br.com.lemao.environment.junit;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import br.com.lemao.environment.model.bicycle.BicycleInMemorySupport;
import br.com.lemao.environment.model.biker.BikerInMemorySupport;

public class InMemoryStatement extends EnvironmentStatement {

	public InMemoryStatement(Statement statement, Description description) {
		super(statement, description);
	}

	@Override
	protected void after() {
		BikerInMemorySupport.getInstance().dropObjects();
		BicycleInMemorySupport.getInstance().dropObjects();
	}

}
