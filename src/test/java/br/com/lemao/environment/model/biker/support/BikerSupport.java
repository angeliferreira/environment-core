package br.com.lemao.environment.model.biker.support;

import java.util.List;

import br.com.lemao.environment.hibernate.HibernateUtil;
import br.com.lemao.environment.model.biker.Biker;

public final class BikerSupport {

	private static BikerSupport bikerSupport = new BikerSupport();
	
	private BikerSupport() {}
	
	public static BikerSupport getInstance() {
		return bikerSupport;
	}
	
	public void persist(Biker biker) {
		HibernateUtil.getCurrentSession().persist(biker);
	}

	@SuppressWarnings("unchecked")
	public List<Biker> findAll() {
		return HibernateUtil.getCurrentSession().createQuery("From Biker").list();
	}

	public Biker findByName(String name) {
		for (Biker biker : findAll()) {
			if (biker.getName().equals(name))
				return biker;			
		}
		return null;
	}
}
