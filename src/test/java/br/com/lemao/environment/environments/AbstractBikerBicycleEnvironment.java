package br.com.lemao.environment.environments;

import br.com.lemao.environment.Environment;
import br.com.lemao.environment.model.bicycle.BicycleBuilder;
import br.com.lemao.environment.model.bicycle.support.BicycleSupport;
import br.com.lemao.environment.model.biker.BikerBuilder;
import br.com.lemao.environment.model.biker.support.BikerSupport;

public abstract class AbstractBikerBicycleEnvironment extends Environment {
	
	protected BikerSupport bikerSupport = BikerSupport.getInstance();
	protected BicycleSupport bicycleSupport = BicycleSupport.getInstance();
	
	public static BicycleBuilder oneBicycle() {
		return new BicycleBuilder();
	}

	public static BikerBuilder oneBiker() {
		return new BikerBuilder();
	}
}
