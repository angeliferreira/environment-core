package br.com.lemao.environment.model.bicycle;

import java.util.List;

import br.com.lemao.environment.hibernate.HibernateUtil;

public final class BicycleSupport {

	private static BicycleSupport bicycleSupport;
	
	private BicycleSupport() {
	}
	
	public static BicycleSupport getInstance() {
		if (bicycleSupport == null)
			bicycleSupport = new BicycleSupport();
		return bicycleSupport;
	}
	
	public void persist(Bicycle bicycle) {
		HibernateUtil.getCurrentSession().persist(bicycle);
	}

	@SuppressWarnings("unchecked")
	public List<Bicycle> findAll() {
		return HibernateUtil.getCurrentSession().createQuery("From Bicycle").list();
	}

	public Bicycle findByModelName(String modelName) {
		for (Bicycle bicycle : findAll()) {
			if (bicycle.getModelName().equals(modelName))
				return bicycle;			
		}
		return null;
	}

}
