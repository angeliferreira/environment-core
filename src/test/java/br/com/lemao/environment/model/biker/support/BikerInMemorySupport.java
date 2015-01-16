package br.com.lemao.environment.model.biker.support;

import java.util.ArrayList;
import java.util.List;

import br.com.lemao.environment.model.biker.Biker;

public final class BikerInMemorySupport {
	
	private static BikerInMemorySupport bikerInMemorySupport;
	private static List<Biker> bikers = new ArrayList<Biker>();
	
	private BikerInMemorySupport() {
	}
	
	public static BikerInMemorySupport getInstance() {
		if (bikerInMemorySupport == null)
			bikerInMemorySupport = new BikerInMemorySupport();
		return bikerInMemorySupport;
	}
	
	public void persist(Biker bicycle) {
		bikers.add(bicycle);
	}
	
	public List<Biker> findAll() {
		return bikers;
	}
	
	public Biker findByName(String name) {
		for (Biker biker : findAll()) {
			if (biker.getName().equals(name))
				return biker;			
		}
		return null;
	}

	public void dropObjects() {
		bikers.clear();
	}

}
