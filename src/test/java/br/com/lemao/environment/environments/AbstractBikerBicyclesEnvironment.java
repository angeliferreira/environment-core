package br.com.lemao.environment.environments;

import br.com.lemao.environment.Environment;
import br.com.lemao.environment.model.bicycle.BicycleBuilder;
import br.com.lemao.environment.model.bicycle.BicycleSupport;
import br.com.lemao.environment.model.biker.BikerBuilder;
import br.com.lemao.environment.model.biker.BikerSupport;

public abstract class AbstractBikerBicyclesEnvironment extends Environment {
	
	protected BikerSupport bikerSupport = new BikerSupport();
	protected BicycleSupport bicycleSupport = new BicycleSupport();
	
	public static BicycleBuilder oneBicycle() {
		return new BicycleBuilder();
	}

	public static BikerBuilder oneBiker() {
		return new BikerBuilder();
	}
}
