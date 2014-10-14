package br.com.lemao.environment;

import br.com.lemao.environment.model.bicycle.BicycleBuilder;
import br.com.lemao.environment.model.bicycle.BicycleSupport;
import br.com.lemao.environment.model.biker.BikerBuilder;
import br.com.lemao.environment.model.biker.BikerSupport;

public class BikerEnvironment extends Environment {
	
	protected BikerSupport bikerSupport = new BikerSupport();
	protected BicycleSupport bicycleSupport = new BicycleSupport();
	
	protected BicycleBuilder oneBicycle() {
		return new BicycleBuilder();
	}

	protected BikerBuilder oneBiker() {
		return new BikerBuilder();
	}

}
