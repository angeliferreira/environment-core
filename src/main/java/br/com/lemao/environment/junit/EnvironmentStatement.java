package br.com.lemao.environment.junit;

import java.lang.reflect.Method;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import br.com.lemao.environment.Environment;
import br.com.lemao.environment.annotation.GivenEnvironment;
import br.com.lemao.environment.annotation.IgnoreEnvironment;
import br.com.lemao.environment.exception.EnvironmentException;
import br.com.lemao.environment.exception.EnvironmentNotImplementedException;

public class EnvironmentStatement extends Statement {

	private Description description;
	private Statement statement;

	public EnvironmentStatement(Statement statement, Description description) {
		this.statement = statement;
		this.description = description;
	}

	protected void before() {
		GivenEnvironment givenEnvironment = getGivenEnvironmentAnnotation();

		if (givenEnvironment == null || getIgnoreEnvironmentAnnotation() != null) return;

		runEnvironment(givenEnvironment);
	}

	protected void after() {
		// Nothing to do here
	}

	private void runEnvironment(GivenEnvironment givenEnvironment) {
		Class<? extends Environment> environmentClass = givenEnvironment.value();

		try {
			Method environmentMethod = environmentClass.getMethod(givenEnvironment.environmentName());

			GivenEnvironment environmentFather = environmentMethod.getAnnotation(GivenEnvironment.class);
			if (environmentFather != null) runEnvironment(environmentFather);

			environmentMethod.invoke(getEnvironmentInstance(environmentClass));
		} catch (NoSuchMethodException e) {
			throw new EnvironmentNotImplementedException(environmentClass, givenEnvironment.environmentName(), e);
		} catch (Exception e) {
			throw new EnvironmentException(environmentClass, givenEnvironment.environmentName(), e);
		}
	}

	private Environment getEnvironmentInstance(Class<? extends Environment> environmentClass) throws InstantiationException, IllegalAccessException {
		return (Environment) environmentClass.newInstance();
	}

	private IgnoreEnvironment getIgnoreEnvironmentAnnotation() {
		return description.getAnnotation(IgnoreEnvironment.class);
	}

	private GivenEnvironment getGivenEnvironmentAnnotation() {
		GivenEnvironment givenEnvironmentMethodAnnotation = description.getAnnotation(GivenEnvironment.class);

		if (givenEnvironmentMethodAnnotation != null) return givenEnvironmentMethodAnnotation;

		return description.getTestClass().getAnnotation(GivenEnvironment.class);
	}

	@Override
	public void evaluate() throws Throwable {
		try {
			before();
			statement.evaluate();
			after();
		} catch (Exception e) {
			throw new EnvironmentException(getGivenEnvironmentAnnotation().getClass(), getGivenEnvironmentAnnotation().environmentName(), e);
		}
	}

}
