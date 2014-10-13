package br.com.lemao.environment.exception;


public class EnvironmentException extends RuntimeException {

	private static final long serialVersionUID = -3711300800387484490L;
	
	public EnvironmentException(Class<?> environmentClass, String environmentName, Exception e) {
		super(getMessage(environmentClass, environmentName), e);
	}

	private static String getMessage(Class<?> environmentClass,	String environmentName) {
		return "Error trying to run environment >> " + environmentClass.getName() + "." + environmentName;
	}

}
