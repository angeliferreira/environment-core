package br.com.lemao.environment.exception;

public class AfterEnvironmentException extends RuntimeException {

	private static final long serialVersionUID = -3711300800387484490L;
	
	public AfterEnvironmentException(Class<?> environmentClass, String environmentName, Exception e) {
		super(getMessage(environmentClass, environmentName), e);
	}

	private static String getMessage(Class<?> environmentClass,	String environmentName) {
		return "Error trying to run after environment >> " + environmentClass.getName() + "." + environmentName;
	}

}
