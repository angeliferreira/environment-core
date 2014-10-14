package br.com.lemao.environment;

import br.com.lemao.environment.model.bicycle.Bicycle;
import br.com.lemao.environment.model.bicycle.BicycleBuilder;
import br.com.lemao.environment.model.bicycle.BicycleSupport;
import br.com.lemao.environment.model.biker.Biker;
import br.com.lemao.environment.model.biker.BikerBuilder;
import br.com.lemao.environment.model.biker.BikerSupport;
import br.com.lemao.environment.model.gender.Gender;

public class BikerEnvironmentWithOneBikerAndOneBicycle extends Environment {
	
	private BikerSupport bikerSupport = new BikerSupport();
	private BicycleSupport bicycleSupport = new BicycleSupport();
	
	@Override
	public void run() {
		Biker lemaoBiker = oneBiker()
				.withName("Lemão")
				.withGender(Gender.MALE)
				.gimme();
		bikerSupport.persist(lemaoBiker);
		
		Bicycle bicycle = oneBicycle()
				.forBiker(lemaoBiker)
				.withModelName("S-WORKS EPIC 29")
				.withSerialNumber(165487L)
				.gimme();
		bicycleSupport.persist(bicycle);
	}

	private BicycleBuilder oneBicycle() {
		return new BicycleBuilder();
	}

	private BikerBuilder oneBiker() {
		return new BikerBuilder();
	}

}
