package br.com.lemao.environment.model.biker;

import java.util.List;

import br.com.lemao.environment.hibernate.HibernateUtil;

public final class BikerSupport {

	public void persist(Biker biker) {
		HibernateUtil.getCurrentSession().persist(biker);
	}

	@SuppressWarnings("unchecked")
	public List<Biker> findAll() {
		return HibernateUtil.getCurrentSession().createQuery("From Biker").list();
	}

}
