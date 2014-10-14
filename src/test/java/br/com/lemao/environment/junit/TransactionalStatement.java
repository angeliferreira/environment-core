package br.com.lemao.environment.junit;

import org.hibernate.Transaction;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import br.com.lemao.environment.hibernate.HibernateUtil;
import br.com.lemao.environment.junit.EnvironmentStatement;

public class TransactionalStatement extends EnvironmentStatement {

	private Transaction transaction = HibernateUtil.getCurrentSession().getTransaction();
	
	public TransactionalStatement(Statement statement, Description description) {
		super(statement, description);
	}

	@Override
	protected void before() {
		transaction.begin();
		super.before();
	}
	
	@Override
	protected void after() {
		transaction.rollback();
		HibernateUtil.getCurrentSession().disconnect();
	}

}
