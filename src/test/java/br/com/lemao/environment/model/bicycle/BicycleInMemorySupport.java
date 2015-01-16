package br.com.lemao.environment.model.bicycle;

import java.util.ArrayList;
import java.util.List;

public final class BicycleInMemorySupport {
	
	private static BicycleInMemorySupport bicycleInMemorySupport;
	private static List<Bicycle> bicycles = new ArrayList<Bicycle>();
	
	private BicycleInMemorySupport() {
	}
	
	public static BicycleInMemorySupport getInstance() {
		if (bicycleInMemorySupport == null)
			bicycleInMemorySupport = new BicycleInMemorySupport();
		return bicycleInMemorySupport;
	}
	
	public void persist(Bicycle bicycle) {
		bicycles.add(bicycle);
	}
	
	public List<Bicycle> findAll() {
		return bicycles;
	}
	
	public Bicycle findByModelName(String modelName) {
		for (Bicycle bicycle : findAll()) {
			if (bicycle.getModelName().equals(modelName))
				return bicycle;			
		}
		return null;
	}

	public void dropObjects() {
		bicycles.clear();
	}

}
