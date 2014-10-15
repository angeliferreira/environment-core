package br.com.lemao.environment.environments;

import br.com.lemao.environment.model.bicycle.Bicycle;
import br.com.lemao.environment.model.biker.Biker;
import br.com.lemao.environment.model.gender.Gender;

public class OneMaleBikerAndOneBicycleForThisBiker extends BikerEnvironment {
	
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

}